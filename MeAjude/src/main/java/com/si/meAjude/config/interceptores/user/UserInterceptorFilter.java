package com.si.meAjude.config.interceptores.user;

import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserInterceptorFilter implements HandlerInterceptor {


    // preHandle: Executed before actual handler is executed
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User userFromRequest = getUserFromRequest(request);
        if(userFromRequest.getRole() == UserRole.ADMIN) return true;
        if(hasIdInURL(request) && userFromRequest.getId().equals(getIdFromUrl(request))) return true;
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

    private User getUserFromRequest(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    private boolean hasIdInURL(HttpServletRequest request){
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
