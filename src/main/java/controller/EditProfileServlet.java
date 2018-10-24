package main.java.controller;

import main.java.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/editProfile")
@MultipartConfig
public class EditProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EditProfileService eps= new EditProfileService();

        try {
            eps.processRequest(req);
        } catch (SQLException | CloneNotSupportedException e) {
            e.printStackTrace();
        }

        RequestDispatcher success = req.getRequestDispatcher("view/editprofile.jsp");
        success.forward(req, resp);
    }
}
