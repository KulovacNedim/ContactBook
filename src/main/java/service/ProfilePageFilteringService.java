package main.java.service;

import main.java.entities.ContactGroup;
import main.java.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class ProfilePageFilteringService {

    UserService userService = new UserService();
    ContactGroupService contactGroupService = new ContactGroupService();

    public void filterCases(User loggedInUser, Long newGroupId, Long contactId, Long groupId, String groupName, String active, String search, HttpServletRequest req) {

        boolean activeFlag = active.equals("active") ? true : false;

        List<ContactGroup> contactGroupList = null;
        try {
            contactGroupList = contactGroupService.getAllContactGroups();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User contactToShow = null;
        List<User> myContacts = null;
        ContactGroup refContactGroup = null;

        if (newGroupId > 0) { // call came from change contact group dropdown menu
            try {
                contactGroupService.setContactGroupForUser(loggedInUser, userService.getUserById(contactId), newGroupId);

                groupName = contactGroupService.getContactGroupById(groupId).getContactGroup();

                contactToShow = userService.getUserById(contactId);

                if (groupId > 0) { // keep parameters from dropdown menu
                    myContacts = userService.getMyContactGroupList(loggedInUser, contactGroupService.getContactGroupById(groupId), activeFlag);

                } else
                if (search.equals("")){ // keep parameters from search box
                    myContacts = userService.getAllUsersByActivity(activeFlag);

                }  else { // search box and dropdown menu parameters are defaults
                    myContacts = userService.getUserListMatchingSearch(search, activeFlag);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (contactId > 0) { // call came from contact list
            try {
                if (groupId > 0) { // keep parameters from dropdown menu
                    myContacts = userService.getMyContactGroupList(loggedInUser, contactGroupService.getContactGroupById(groupId), activeFlag);

                } else if (search.equals("")){ // keep parameters from search box
                    myContacts = userService.getAllUsersByActivity(activeFlag);

                }  else { // search box and dropdown menu parameters are defaults
                    myContacts = userService.getUserListMatchingSearch(search, activeFlag);
                }

                contactToShow = userService.getUserById(contactId);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (groupId > 0) { // call came from dropdown menu
            try {
                myContacts = userService.getMyContactGroupList(loggedInUser, contactGroupService.getContactGroupById(groupId), activeFlag);
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
                myContacts = userService.getUserListMatchingSearch(search, activeFlag);
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
                myContacts = userService.getAllUsersByActivity(activeFlag);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            contactToShow = (User)req.getSession().getAttribute("loggedInUser");
        }

        try {
            refContactGroup = contactGroupService.getContactGroupByUser(loggedInUser, contactToShow);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("groupId", groupId);
        req.setAttribute("searchPlaceholder", search);
        req.setAttribute("contactGroupList", contactGroupList);
        req.setAttribute("contactGroupName", groupName);
        req.setAttribute("myContacts", myContacts);
        req.setAttribute("contactToShow", contactToShow);
        req.setAttribute("refContactGroup", refContactGroup);
        req.setAttribute("active", active);
    }
}
