package main.java.controller;

import main.java.service.RegisterService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            RequestDispatcher success = request.getRequestDispatcher("view/index.jsp");
            success.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RegisterService registerService = new RegisterService();

        String newNickname = (String) request.getSession().getAttribute("newNickname");
        String password = (String) request.getSession().getAttribute("password");

        registerService.registerUser(newNickname, password, request);

        RequestDispatcher success = request.getRequestDispatcher("view/profilesetup.jsp");
        success.forward(request, response);

    }
}
