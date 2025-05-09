// package src;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        response.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        System.out.println("📥 Received username: " + username);
        System.out.println("📥 Received password: " + password);

        // Debugging: Print the received username and password
        UserAuth.AuthResult auth = UserAuth.login(username, password);

        if (auth != null) {
            
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", auth.role);

            
            response.sendRedirect("dashboard");
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body style='font-family:sans-serif;'>");
            out.println("<h3 style='color:red;'>Login failed. Invalid username or password.</h3>");
            out.println("<a href='login.html'>Back to Login</a>");
            out.println("</body></html>");
        }
    }
}
