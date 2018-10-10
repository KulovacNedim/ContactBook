package main.java.controller;

import main.java.entities.User;
import main.java.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/showContact")
public class ShowContactServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = new UserService();

        if (req.getSession().getAttribute("loggedInUser") == null) {
            RequestDispatcher success = req.getRequestDispatcher("view/index.jsp");
            success.forward(req, resp);
        } else {
            Long contactId = Long.valueOf(req.getParameter("contactId"));

            User contactToShow = null;

            try {
                contactToShow = userService.getUserById(contactId);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            List<User> myContacts = null;

            try {
                myContacts =userService.getAllUsers();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            req.setAttribute("myContacts", myContacts);

            req.getSession().setAttribute("contactToShow", contactToShow);

            RequestDispatcher success = req.getRequestDispatcher("view/profile.jsp");
            success.forward(req, resp);
        }
    }
}
