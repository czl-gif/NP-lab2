package servlet;

import pojo.User;
import service.UserServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="+")
public class LoginServlet extends HttpServlet {


    /*protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()){
            String username = request.getParameter("username").trim();
            String password = request.getParameter("password").trim();
            UserServiceImpl userservice = new UserServiceImpl();
            int verifyResult = userservice.verifyLogin(new User(username, password));
            Map<String, String> params = new HashMap<String, String>();
            JSONObject jsonObject = new JSONObject();

            if (verifyResult == -1){
                params.put("Result", "3");
            }else if (verifyResult == 0){
                params.put("Result", "2");
            }else if(verifyResult == 1){
                params.put("Result", "1");
            }else{
                params.put("Result", "4");
            }
            jsonObject.put("params", params);
            out.write(jsonObject.toString());
        }
    }*/
    /*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            UserServiceImpl userService = new UserServiceImpl();
            int verifyResult = userService.verifyLogin(new User(username, password));
            //int verifyResult = 0;
            PrintWriter out = response.getWriter();
            JSONObject result = new JSONObject();
            if (verifyResult == -1){
                result.put("Result", "ssh");
            }else if (verifyResult == 0){
                result.put("Result", "2");
            }else if(verifyResult == 1){
                result.put("Result", "3");
            }else{
                result.put("Result", "4");
            }
            response.setContentType("application/json;charset=UTF-8");
            System.out.println(request);

            out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doGet(request, response);
    }
}
