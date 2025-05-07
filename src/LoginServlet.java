package src;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")  // IMPORTANT
public class LoginServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    // Dummy check or call UserAuth.login() here
    if ("admin".equals(username) && "admin123".equals(password)) {
      response.sendRedirect("dashboard.html");
    } else {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      out.println("<h1>Login Failed</h1>");
    }
  }
}

