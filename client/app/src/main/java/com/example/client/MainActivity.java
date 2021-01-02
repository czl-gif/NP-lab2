package com.example.client;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {
    private Button signInButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private ToggleButton tbPasswordVisibility;

    private TextView err;
    private String user;
    private String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();

        this.passwordEditText = (EditText) findViewById(R.id.et2);
        this.tbPasswordVisibility = (ToggleButton) findViewById(R.id.tb_password_visibility);

        this.tbPasswordVisibility.setOnCheckedChangeListener(new ToggleButtonClick());
    }

    public void initview(){
        signInButton = findViewById(R.id.button1);
        usernameEditText = findViewById(R.id.et1);
        passwordEditText = findViewById(R.id.et2);
    }





    public void Login(View view){
        user = usernameEditText.getText().toString();
        pass = passwordEditText.getText().toString();
        if(user.isEmpty()){
            Toast.makeText(MainActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
        }else if(pass.isEmpty()){
            Toast.makeText(MainActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"正在登录",Toast.LENGTH_SHORT).show();
            new SignInProcess().execute(user, pass);
        }
    }

    private class SignInProcess extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String...params) {
            String username = params[0];
            String password = params[1];
            String result = "";
            String strurl = "http://192.168.43.46:8080/server/LoginServlet?username="
                    +username+"&password="+password;
            //String strurl = "http://42.194.181.52:8080/lab4/Sign??username="
              //      +username+"&password="+password;
            try {
                URL url = new URL(strurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setUseCaches(false);
                conn.connect();
                InputStream is = conn.getInputStream();
                InputStreamReader reader = new InputStreamReader(is, "UTF-8");
                int temp;
                while((temp=reader.read()) != -1) {
                    result += (char)temp;
                }

            } catch(Exception e) {
                Toast.makeText(MainActivity.this,"lianjieshibai",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            //Toast.makeText(MainActivity.this, result+"hh" ,Toast.LENGTH_SHORT).show();
            Log.e("mei1ianjie:", result);
            System.out.println(result);
            return result;
        }
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
            try {
                JSONObject result_json = new JSONObject(result);
                String answer = (String) result_json.get("Result");
                Toast.makeText(MainActivity.this,answer,Toast.LENGTH_SHORT).show();
                if(answer.equals("ssh")) {
                    Toast.makeText(MainActivity.this,"用户不存在",Toast.LENGTH_SHORT).show();
                } else if(answer.equals("2")){
                    Toast.makeText(MainActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                }
                else if (answer.equals("3")){
                    Toast.makeText(MainActivity.this,"lianjiechenggong",Toast.LENGTH_SHORT).show();
                    SignInSuccess(result_json);
                }
            } catch (Exception e) {
                Toast.makeText(MainActivity.this,"cuowu",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
    private void SignInSuccess(JSONObject info) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
        /*try {
            intent.putExtra("username", info.getString("username"));
            intent.putExtra("name", info.getString("name"));
            intent.putExtra("age", info.getInt("age"));
            intent.putExtra("teleno", info.getString("teleno"));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        startActivity(intent);
    }

    private class ToggleButtonClick implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked){
            if (isChecked){
                passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else{
                passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            passwordEditText.setSelection(passwordEditText.length());
        }
    }

    public void SignUp(View view){
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

}