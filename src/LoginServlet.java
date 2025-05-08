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
        // Gửi người dùng về trang login.html nếu họ vào /login bằng GET
        response.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // Lấy username và password từ form, loại bỏ khoảng trắng dư thừa
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        // Gọi hàm kiểm tra xác thực
        UserAuth.AuthResult auth = UserAuth.login(username, password);

        if (auth != null) {
            // Nếu đúng, tạo session và lưu thông tin người dùng
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", auth.role);

            // Chuyển hướng đến servlet dashboard
            response.sendRedirect("dashboard");
        } else {
            // Nếu sai thông tin đăng nhập, hiện thông báo
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body style='font-family:sans-serif;'>");
            out.println("<h3 style='color:red;'>Login failed. Invalid username or password.</h3>");
            out.println("<a href='login.html'>Back to Login</a>");
            out.println("</body></html>");
        }
    }
}
