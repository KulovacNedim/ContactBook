package main.java.controller;

import main.java.service.EditProfileService;

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

        EditProfileService eps= new EditProfileService();

        Long id = req.getParameter("id") == null ? null : Long.valueOf(req.getParameter("id"));
        String flag = (String) req.getAttribute("flag");

        eps.setParameters(id, flag, req);

        RequestDispatcher success = req.getRequestDispatcher("view/editprofile.jsp");
        success.forward(req, resp);
    }
}
