package main.java.controller;

import main.java.entities.ContactGroup;
import main.java.entities.User;
import main.java.service.ContactGroupService;
import main.java.service.ProfilePageFilteringService;
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
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProfilePageFilteringService ppfs = new ProfilePageFilteringService();

        Long newGroupId = req.getParameter("newGroupId") == null ? -1 : Long.valueOf(req.getParameter("newGroupId")); // check if call is came from change-group dropdown
        Long contactId = req.getParameter("contactId") == null ? -1 : Long.valueOf(req.getParameter("contactId")); // check if call is came from contact list
        String search = req.getParameter("search") == null ? "" : req.getParameter("search"); // check if call came from search box
        Long groupId = req.getParameter("groupId") == null ? -1 : Long.valueOf((req.getParameter("groupId"))); // check if call came from contact group dropdown
        String groupName = (req.getParameter("groupName") == null || req.getParameter("groupName").equals("Add to contact group") || req.getParameter("groupName").equals("Filter contact group")) ? "Filter contact group" : req.getParameter("groupName");
        String active = (req.getParameter("activity") == null ||  req.getParameter("activity").equals("active")) ? "active" : "inactive";

        User loggedInUser = (User) req.getSession().getAttribute("loggedInUser");

        ppfs.filterCases(loggedInUser, newGroupId, contactId, groupId, groupName, active, search, req);

        RequestDispatcher success = req.getRequestDispatcher("view/profile.jsp");
        success.forward(req, resp);
    }
}
