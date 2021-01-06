package com.example.client;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class cancellationActivity extends Activity {
    private Button backToHomeButton;
    private Button cancellationButton;
    private TextView usname;

    private String username;
    private String pass1;
    private String yes;

    private EditText p1;
    private EditText p2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancellation);
        initview();
        username = getIntent().getStringExtra("username");
        usname.setText(username);
    }

    public void initview(){
        cancellationButton = findViewById(R.id.button9);
        backToHomeButton = findViewById(R.id.button10);
        usname = findViewById(R.id.tv3);
        p1=findViewById(R.id.pw);
        p2=findViewById(R.id.yes);
    }

    public void cancellationRequest(final String username, final String password){
        //请求地址
        String url = "http://81.68.74.65:8080/server/CancellationServlet";
        String tag = "Cancellation";
        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);
        //创建StringRequest，定义字符串请求的请求方式为POST(省略第一个参数会默认为GET方式)
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");
                            String result = jsonObject.getString("Result");
                            if (result.equals("CancellationSucceed")) {
                                toast("注销成功");
                                Intent intent = new Intent(cancellationActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else if (result.equals("CancellationFail")) {
                                toast("发生错误!");
                            } else {
                                toast("nani");
                            }
                        } catch (JSONException e) {
                            //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                            Log.e("TAG", e.getMessage(), e);
                            toast("无网络连接");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //做自己的响应错误操作，如Toast提示（“请稍后重试”等）
                toast("请稍后重试");
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };


        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }

    public void cancellation (View view){
        pass1 = p1.getText().toString();
        yes = p2.getText().toString();
        if (pass1.isEmpty()){
            toast("请输入密码!");
        }
        if (!yes.equals("yes")){
            toast("请输入yes确认注销用户!");
        }
        cancellationRequest(username, pass1);
    }

    public void BackToHome (View view){
        Intent intent = new Intent(cancellationActivity.this, WelcomeActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }

    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast toast=Toast.makeText(cancellationActivity.this,null,Toast.LENGTH_SHORT);
                toast.setText(str);
                toast.show();
            }
        });
    }
}