// package src;
// Import necessary Jakarta Servlet classes
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;

// Servlet annotation maps this servlet to the URL path "/login"
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // Handle GET requests 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        // Redirect the user to the login form (login.html)
        response.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // Get submitted form values from the login page
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        System.out.println("📥 Received username: " + username);
        System.out.println("📥 Received password: " + password);

        // Debugging: Print the received username and password
        UserAuth.AuthResult auth = UserAuth.login(username, password);

        if (auth != null) {
            // If authentication is successful, create a session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", auth.role);

            
            response.sendRedirect("dashboard");
        } else {
            // If login fails, display an error message on the same page
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body style='font-family:sans-serif;'>");
            out.println("<h3 style='color:red;'>Login failed. Invalid username or password.</h3>");
            out.println("<a href='login.html'>Back to Login</a>");
            out.println("</body></html>");
        }
    }
}
