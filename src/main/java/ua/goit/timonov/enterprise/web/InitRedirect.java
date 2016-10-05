package ua.goit.timonov.enterprise.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for mapping requests to root URL
 */
public class InitRedirect extends HttpServlet {

    public static final String PATH_TO_MAIN = "WEB-INF/view/jsp/main.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_TO_MAIN);
        requestDispatcher.forward(request, response);
    }
}
