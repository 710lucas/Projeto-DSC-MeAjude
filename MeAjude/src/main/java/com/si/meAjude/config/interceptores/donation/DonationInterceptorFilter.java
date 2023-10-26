package com.si.meAjude.config.interceptores.donation;

import com.si.meAjude.models.Donation;
import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.UserRole;
import com.si.meAjude.repositories.DonationRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;


public class DonationInterceptorFilter implements HandlerInterceptor {

    // preHandle: Executed before actual handler is executed
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User userFromRequest = getUserFromRequest(request);
        if(userFromRequest.getRole() == UserRole.ADMIN) return true;
        if(hasUserIdInQuery(request)) if(userFromRequest.getId().equals(getUserIdInQuery(request))) return true;
        return sendForbiddenResponseAndReturnFalse(response);
    }

    // postHandle: Executed after handler is executed, before view is resolved
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    // afterCompletion: Executed after complete request is finished
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
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
