// package src;
// Import necessary Jakarta Servlet classes
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;
// Map this servlet to the "/dashboard" URL path
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            
        // Try to retrieve an existing session
        HttpSession session = request.getSession(false); // false: don't create if not exists

        // Check if the session exists and contains a logged-in user
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (session != null && session.getAttribute("username") != null) {
            String username = (String) session.getAttribute("username");
            String role = (String) session.getAttribute("role");

            // Display a simple personalized dashboard
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Dashboard</title></head><body>");
            out.println("<h1>Welcome, " + username + "!</h1>");
            out.println("<p>Your role is: <strong>" + role + "</strong></p>");
            out.println("<a href='logout'>Logout</a>");
            out.println("</body></html>");
        } else {
            response.sendRedirect("login.html");  // not logged in
        }
    }
}
