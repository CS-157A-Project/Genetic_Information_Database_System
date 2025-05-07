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
            out.println("<h2>Login successful!</h2>");
            out.println("<p>Welcome, " + username + " (" + auth.role + ")</p>");
        } else {
            out.println("<h2>Login failed</h2>");
            out.println("<p>Invalid username or password.</p>");
        }
    }
}
