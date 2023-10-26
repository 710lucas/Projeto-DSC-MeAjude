package com.si.meAjude.config.interceptores.campaign;

import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

public class CampaignInterceptorFilter implements HandlerInterceptor {


    // preHandle: Executed before actual handler is executed
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User userFromRequest = getUserFromRequest(request);
        if(userFromRequest.getRole() == UserRole.ADMIN) return true;
        if(isPostRequest(request)) return true;
        if(isDeleteRequest(request) || isPutRequest(request)) if(hasOnlyIdInURL(request)) if(userFromRequest.getId().equals(getIdFromUrl(request))) return true;
        if(isGetRequest(request) && hasOnlyIdInURL(request)) return true;
        if(isGetRequest(request) && hasUserIdInQuery(request)) if(userFromRequest.getId().equals(getUserIdInQuery(request))) return true;
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

        private boolean isGetRequest(HttpServletRequest request){
        return request.getMethod().equals("GET");
    }

    private boolean isDeleteRequest(HttpServletRequest request){
        return request.getMethod().equals("DELETE");
    }

    private boolean isPutRequest(HttpServletRequest request){
        return request.getMethod().equals("PUT");
    }

    private boolean isPostRequest(HttpServletRequest request){
        return request.getMethod().equals("POST");
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
