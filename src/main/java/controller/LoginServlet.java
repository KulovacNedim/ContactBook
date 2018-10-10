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


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("loggedInUser") == null) {
            RequestDispatcher success = req.getRequestDispatcher("view/index.jsp");
            success.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = new UserService();

        List<User> myContacts = null;

        try {
            myContacts =userService.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("myContacts", myContacts);
        req.getSession().setAttribute("contactToShow", (User)req.getSession().getAttribute("loggedInUser"));

        RequestDispatcher success = req.getRequestDispatcher("view/profile.jsp");
        success.forward(req, resp);
    }
}
