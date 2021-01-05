package servlet;

import pojo.*;
import service.*;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "RegistServlet")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try(PrintWriter out = response.getWriter()){
            String username = request.getParameter("username").trim();
            String name = request.getParameter("name").trim();
            String password = request.getParameter("password").trim();
            Integer age = Integer.valueOf(request.getParameter("age").trim());
            String telenum = request.getParameter("telenum").trim();

            PersonServiceImpl personService = new PersonServiceImpl();
            UserService usersService = new UserServiceImpl();

            List<Person> personList = personService.getPersonList();
            List<User> userList = usersService.getUserList();
            Map<String,String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();

            boolean hasUserName = false;

            //username是否被注册
            for(User user:userList){
                if(username.equals(user.getUsername())){
                    hasUserName = true;
                    break;
                }
            }

            if(hasUserName){
                params.put("Result","TheUsernameAlreadyExists");
            }
            else{
                personService.addPerson(new Person(username, name, age, telenum));
                usersService.addUser(new User(username,password));
                params.put("Result","SignUpSucceed");
            }

            jsonObject.put("params",params);
            out.write(jsonObject.toString());


        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
