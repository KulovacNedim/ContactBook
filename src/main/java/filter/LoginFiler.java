package main.java.filter;

import main.java.entities.User;
import main.java.service.UserService;
import main.java.validation.PasswordHash;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter("/login")
public class LoginFiler implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        UserService userService = new UserService();

        String nickname = (String) request.getParameter("nickname");
        String password = (String) request.getParameter("password");

        User loggedInUser = null;

        try {
            loggedInUser = userService.getUserByNickname(nickname);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (loggedInUser.getNickName() != null && loggedInUser.getNickName().equals(nickname) && loggedInUser.getPassword().equals(PasswordHash.getHash(password))) {
            //right credentials

            if (loggedInUser.isActive()) {
                request.getSession().setAttribute("loggedInUser", loggedInUser);
                filterChain.doFilter(request, servletResponse);
            } else {
                // inactive user
                String inactiveUser = "Your account is set to be inactive. Ask administrator for futher steps.";
                request.setAttribute("inactiveUser", inactiveUser);
                RequestDispatcher redirectToIndexPage = request.getRequestDispatcher("view/index.jsp");
                redirectToIndexPage.forward(request, servletResponse);
            }
        } else {
            // wrong credentials
            String wrongCredentials = "Wrong credentials.";
            request.setAttribute("wrongCredentials", wrongCredentials);
            RequestDispatcher redirectToIndexPage = request.getRequestDispatcher("view/index.jsp");
            redirectToIndexPage.forward(request, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
