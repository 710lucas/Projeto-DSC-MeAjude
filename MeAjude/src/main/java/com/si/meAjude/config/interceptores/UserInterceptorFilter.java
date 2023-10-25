package com.si.meAjude.config.interceptores;

import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserInterceptorFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User userFromRequest = getUserFromRequest(request);
        if(userFromRequest.getRole() == UserRole.ADMIN) return true;
        if(hasIdInRequest(request) && userFromRequest.getId().equals(getIdFromUrl(request))) return true;
        return sendForbiddenResponseAndReturnFalse(response);
    }

    private User getUserFromRequest(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    private boolean hasIdInRequest(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        String[] requestURISplit = requestURI.split("/");
        if(requestURISplit.length != 3) return false;
        if(isStringLong(requestURISplit[2])) return true;
        return false;
    }

    private boolean isStringLong(String s){
        try{
            Long.parseLong(s);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    private Long getIdFromUrl(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        String[] requestURISplit = requestURI.split("/");
        return Long.parseLong(requestURISplit[2]);
    }

    private boolean sendForbiddenResponseAndReturnFalse(HttpServletResponse response) throws Exception {
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
        return false;
    }
}
