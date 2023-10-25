package com.si.meAjude.config.interceptores;

import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

public class DonationInterceptorFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User userFromRequest = getUserFromRequest(request);
        if(userFromRequest.getRole() == UserRole.ADMIN) return true;
        if(hasUserIdInQuery(request)) if(userFromRequest.getId().equals(getUserIdInQuery(request))) return true;
        return sendForbiddenResponseAndReturnFalse(response);
    }


    private boolean sendForbiddenResponseAndReturnFalse(HttpServletResponse response) throws Exception {
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
        return false;
    }

    private User getUserFromRequest(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    private boolean hasUserIdInQuery(HttpServletRequest request){
        String requestURI = request.getQueryString();
        String[] requestURISplit = requestURI.split("&");
        return Arrays.stream(requestURISplit).anyMatch(s -> s.contains("userId"));
    }

    private Long getUserIdInQuery (HttpServletRequest request){
        String requestURI = request.getQueryString();
        String[] requestURISplit = requestURI.split("&");
        for(String s: requestURISplit) if(s.contains("userId")) return Long.parseLong(s.split("=")[1]);
        return null;
    }
}
