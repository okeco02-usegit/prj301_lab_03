package UserServlet;

import DBUtils.User;
import DBUtils.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author SwordLake
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String searchPage = "Search.html";
    private final String invalidPage = "Invalid.html";

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String url = invalidPage;
        response.setContentType("text/html;charset=UTF-8");
        String userName = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        try {
            UserDAO userDao = new UserDAO();
            User user = userDao.login(userName, password);
            if (user != null) {
                url = searchPage;
            }
            response.sendRedirect(url);
        } catch (Exception ex) {
            // Thay dòng cũ:
            // System.out.println(ex.getMessage());

            // Bằng dòng này để thấy lỗi trên trình duyệt:
            response.getWriter().println("ERROR: " + ex.getMessage());
        }
    }//end processRequest

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
