package com.si.meAjude.config.interceptores;

import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

public class DonationInterceptorFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User userFromRequest = getUserFromRequest(request);
        if(userFromRequest.getRole() == UserRole.ADMIN) return true;
        if(hasOnlyIdInURL(request) && userFromRequest.getId().equals(getIdFromUrl(request))) return true;
        if(hasUserIdInQuery(request)) if(userFromRequest.getId().equals(getUserIdInQuery(request))) return true;
        return sendForbiddenResponseAndReturnFalse(response);
    }

    private boolean sendForbiddenResponseAndReturnFalse(HttpServletResponse response) throws Exception {
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
        return false;
    }

    private boolean hasOnlyIdInURL(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        String[] requestURISplit = requestURI.split("/");
        if(requestURISplit.length != 3) return false;
        if(isStringLong(requestURISplit[2])) return true;
        return false;
    }

    private Long getIdFromUrl(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        String[] requestURISplit = requestURI.split("/");
        return Long.parseLong(requestURISplit[2]);
    }

    private boolean isStringLong(String s){
        try{
            Long.parseLong(s);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    private User getUserFromRequest(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    private boolean hasUserIdInQuery(HttpServletRequest request){
        String requestURI = request.getQueryString();
        if(requestURI == null) return false;
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
