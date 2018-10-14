package main.java.filter;

import main.java.entities.User;
import main.java.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter("/register")
public class RegisterFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;

        UserService userService = new UserService();

        String newNickname = (String) servletRequest.getParameter("newNickname");
        String password = (String) servletRequest.getParameter("password");

        User existingUser = null;

        try {
            existingUser = userService.getUserByNickname(newNickname);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (existingUser.getNickName() != null) {

            // user exist, ask for another nickname
            try {
                existingUser = userService.getUserByNickname(newNickname);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (!existingUser.isActive()) {
                String inactiveUserReg = "Account with nickname " + newNickname + " already exist. Ask administrator for further steps.";
                request.setAttribute("inactiveUserReg", inactiveUserReg);
            } else {
                String existingNickname = "User with nickname you entered already exist.";
                request.setAttribute("existingNickname", existingNickname);
            }

            RequestDispatcher redirectToIndexPage = request.getRequestDispatcher("view/index.jsp");
            redirectToIndexPage.forward(request, servletResponse);
        } else {

            // user does not exist
            request.getSession().setAttribute("newNickname", newNickname);
            request.getSession().setAttribute("password", password);

            filterChain.doFilter(request, servletResponse);
        }
    }

    public void destroy() {

    }
}
