package main.java.controller;

import main.java.entities.ContactGroup;
import main.java.entities.User;
import main.java.service.ContactGroupService;
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

        UserService userService = new UserService();
        ContactGroupService contactGroupService = new ContactGroupService();

        Long contactId = req.getParameter("contactId") == null ? -1 : Long.valueOf(req.getParameter("contactId")); // check if call is came from contact list
        String search = req.getParameter("search") == null ? "" : req.getParameter("search"); // check if call came from search box
        Long groupId = req.getParameter("groupId") == null ? -1 : Long.valueOf((req.getParameter("groupId"))); // check if call came from contact group dropdown
        String groupName = (req.getParameter("groupName") == null || req.getParameter("groupName").equals("Filter contact group")) ? "Filter contact group" : req.getParameter("groupName");

        User loggedInUser = (User) req.getSession().getAttribute("loggedInUser");

        List<ContactGroup> contactGroupList = null;
        try {
            contactGroupList = contactGroupService.getAllContactGroups();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User contactToShow = null;
        List<User> myContacts = null;

        if (contactId > 0) { // call came from contact list
            try {
                if (groupId > 0) { // keep parameters from dropdown menu
                    myContacts =userService.getMyContactGroupList(loggedInUser, contactGroupService.getContactGroupById(groupId));

                } else if (search.equals("")){ // keep parameters from search box
                    myContacts =userService.getAllUsers();

                }  else { // search box and dropdown menu parameters are defaults
                    myContacts =userService.getUserListMatchingSearch(search);
                }

                contactToShow = userService.getUserById(contactId);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (groupId > 0) { // call came from dropdown menu
            try {
                myContacts =userService.getMyContactGroupList(loggedInUser, contactGroupService.getContactGroupById(groupId));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (myContacts.size() == 0){
                contactToShow = (User)req.getSession().getAttribute("loggedInUser");
            } else {
                contactToShow = myContacts.get(0);
            }

        } else if (!search.equals("")) { // call came from search box
            try {
                myContacts =userService.getUserListMatchingSearch(search);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (myContacts.size() == 0){
                contactToShow = (User)req.getSession().getAttribute("loggedInUser");
            } else {
                contactToShow = myContacts.get(0);
            }

        } else {
            try {
                myContacts =userService.getAllUsers();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            contactToShow = (User)req.getSession().getAttribute("loggedInUser");
        }

        req.setAttribute("groupId", groupId);
        req.setAttribute("searchPlaceholder", search);
        req.setAttribute("contactGroupList", contactGroupList);
        req.setAttribute("contactGroupName", groupName);
        req.setAttribute("myContacts", myContacts);
        req.setAttribute("contactToShow", contactToShow);


        RequestDispatcher success = req.getRequestDispatcher("view/profile.jsp");
        success.forward(req, resp);
    }
}
