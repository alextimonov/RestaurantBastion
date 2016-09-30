package ua.goit.timonov.enterprise.web;

import ua.goit.timonov.enterprise.controllers.DbController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 30.09.2016.
 */
public class InitRedirect extends HttpServlet {

    private DbController dbController;

    public void setDbController(DbController dbController) {
        this.dbController = dbController;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        dbController.restoreDatabase();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/jsp/main.jsp");
        requestDispatcher.forward(request, response);
    }
}
