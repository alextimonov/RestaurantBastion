package ua.goit.timonov.enterprise.module_8;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Servlet for update ToDoList form
 */
public class UpdateServlet extends HttpServlet {

    public static final String ENABLED_TASK = "Enabled Task=";
    public static final String DELETED_TASK = "Deleted Task=";

    // adds to session new attribute from request, removes from session attributes marked in request to delete
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String parameter = request.getParameter("taskName");
        String attributeObject = request.getParameter("categoryName");
        if (isValid(parameter) & isValid(attributeObject)) {
            String attributeName = ENABLED_TASK + parameter;
            session.setAttribute(attributeName, attributeObject);
        }

        for (Enumeration<String> parameterNames = request.getParameterNames(); parameterNames.hasMoreElements(); ) {
            String taskNameWithPrefix = parameterNames.nextElement();
            if (taskNameWithPrefix.startsWith(DELETED_TASK)) {
                String taskName = taskNameWithPrefix.substring(DELETED_TASK.length(), taskNameWithPrefix.length());
                session.removeAttribute(taskName);
            }
        }
//        while (parameterNames.hasMoreElements()) {}
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    // checks if string parameter is not blank
    private boolean isValid(String parameter) {
        return StringUtils.isNoneBlank(parameter);
    }
}
