import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserAuth.AuthResult auth = UserAuth.login(username, password);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (auth != null) {
            out.println("<h2>Login Successful!</h2>");
            out.println("<p>Welcome, " + username + " (role: " + auth.role + ")</p>");
        } else {
            out.println("<h2>Login Failed</h2>");
            out.println("<p>Invalid username or password.</p>");
            out.println("<a href='login.html'>Try again</a>");
        }
    }
}
