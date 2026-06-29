package UserServlet;

import DBUtils.User;
import DBUtils.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author SwordLake
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
    private final String searchPage = "Search.html";
    private final String showSearchResultServlet = "ShowSearchResultServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url, searchValue;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        url = searchPage;
        try {
            searchValue = request.getParameter("txtSearchValue");
            if (!searchValue.isEmpty()) {
                UserDAO userDao = new UserDAO();
                List<User> userList = userDao.searchUserByLastName(searchValue);
                request.setAttribute("SearchResult", userList);
                url = showSearchResultServlet;
            }
        } catch (Exception ex) {
            out.println("Error:" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

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
