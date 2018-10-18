package main.java.controller;

import main.java.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editProfile")
public class EditProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("id") == null) {
            if (req.getParameter("flag").equals("addNewContact")) {
                // adding new contact
            } else {
                // editin registered contact - flag is "editRegisteredContact
            }
        } else {
            // edit selected contact
        }

        RequestDispatcher success = req.getRequestDispatcher("view/editprofile.jsp");
        success.forward(req, resp);
    }
}
