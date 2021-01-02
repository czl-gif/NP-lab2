package servlet;

import net.sf.json.JSONObject;
import service.PersonServiceImpl;
import pojo.Person;

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

@WebServlet(name = "WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try(PrintWriter out = response.getWriter()){
            String username = request.getParameter("username").trim();
            String name = "";
            Integer age = 0;
            String telenum = "";
            PersonServiceImpl personService = new PersonServiceImpl();
            List<Person> personList = personService.getPersonList();
            for(Person person:personList){
                if(username.equals(person.getUsername())){
                    name = person.getName();
                    age = person.getAge();
                    telenum = person.getTelenum();
                    break;
                }
            }
            Map<String,String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();
            params.put("username",username);
            params.put("name",name);
            params.put("age", String.valueOf(age));
            params.put("telenum",telenum);
            jsonObject.put("params",params);
            out.write(jsonObject.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
