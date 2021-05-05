package com.project.issuetracker.auth.handler;

import com.project.issuetracker.auth.dto.SessionUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class FormAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final RedirectStrategy strategy = super.getRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser)  authentication.getPrincipal();
        session.setAttribute("username", sessionUser.getName());
        session.setAttribute("email", sessionUser.getEmail());
        session.setAttribute("team", sessionUser.getTeam());

        strategy.sendRedirect(request, response, "/posts");
    }
}
