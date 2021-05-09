package com.project.issuetracker.auth.handler;

import com.project.issuetracker.auth.dto.AccountDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component("form")
public class FormAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final RedirectStrategy strategy = super.getRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        final AccountDetail accountDetail = (AccountDetail) authentication.getPrincipal();

        HttpSession session = request.getSession();
        session.setAttribute("username", accountDetail.getName());
        session.setAttribute("email", accountDetail.getEmail());
        session.setAttribute("team", accountDetail.getTeam());
        session.setAttribute("accountId", accountDetail.getAccountId());

        strategy.sendRedirect(request, response, "/posts");

        super.clearAuthenticationAttributes(request);
    }
}
