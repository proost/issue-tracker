package com.project.issuetracker.auth.handler;

import com.project.issuetracker.auth.dto.AccountDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component("remember-me")
public class RememberMeAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        final AccountDetail accountDetail = (AccountDetail) authentication.getPrincipal();

        HttpSession session = request.getSession();
        session.setAttribute("username", accountDetail.getName());
        session.setAttribute("email", accountDetail.getEmail());
        session.setAttribute("team", accountDetail.getTeam());
        session.setAttribute("accountId", accountDetail.getAccountId());

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
