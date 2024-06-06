package kpi.hlibsukhoruchkin.kursova.api;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kpi.hlibsukhoruchkin.kursova.User;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "userRegisterAPI", value = "/api/register")
public class Register extends HttpServlet {
    @Override
    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        User user = new User();
        JSONObject result = user.validateRequest(request);
        if (!result.getBoolean("success")) {
            response.setStatus(result.getInt("errorCode"));
            out.println(result);
            return;
        }
        result = user.register();
        if (!result.getBoolean("success")) {
            response.setStatus(result.getInt("errorCode"));
            out.println(result);
            return;
        }
        response.addCookie(user.generateCookie());
        out.println(result);
    }

    public void destroy() {
    }
}