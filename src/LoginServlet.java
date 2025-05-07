package src;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.html");  // redirects GET to login form
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

                
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("Username received: " + username);
        System.out.println("Password received: " + password);

        UserAuth.AuthResult auth = UserAuth.login(username, password);

        if (auth != null) {
            response.sendRedirect("dashboard.html");
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h1>Login failed. Invalid username or password.</h1>");
        }
    }
}
