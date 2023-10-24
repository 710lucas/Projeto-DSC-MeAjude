package com.si.meAjude.config;

import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.UserRole;
import com.si.meAjude.service.dtos.user.UserUpdateDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(isGetRequest(request)) return filterGetRequest(request, response);
        if(isPutRequest(request)) return filterPutRequest(request, response);
        if(isDeleteRequest(request)) return filterDeleteRequest(request, response);
        return true;
    }

    private boolean filterDeleteRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User userFromRequest = getUserFromRequest(request);
        if(userFromRequest.getRole() == UserRole.ADMIN) return true;
        if(userFromRequest.getId().equals(getIdFromUrl(request))) return true;
        return sendForbiddenResponseAndReturnFalse(response);
    }

    private boolean filterGetRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User userFromRequest = getUserFromRequest(request);
        if(userFromRequest.getRole() == UserRole.ADMIN) return true;
        if(hasIdInRequest(request) && userFromRequest.getId().equals(getIdFromUrl(request))) return true;
        return sendForbiddenResponseAndReturnFalse(response);
    }

    private boolean filterPutRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User userFromRequest = getUserFromRequest(request);
        UserUpdateDTO userUpdateDTO = (UserUpdateDTO) request.getAttribute("userUpdateDTO");
        if(userFromRequest.getRole() == UserRole.ADMIN) return true;
        if(userFromRequest.getId().equals(userUpdateDTO.id())) return true;
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

    private boolean hasIdInRequest(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        String[] requestURISplit = requestURI.split("/");
        return requestURISplit.length > 2;
    }

    private Long getIdFromUrl(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        String[] requestURISplit = requestURI.split("/");
        return Long.parseLong(requestURISplit[2]);
    }

    private boolean isGetRequest(HttpServletRequest request){
        return request.getMethod().equals(HttpMethod.GET.name());
    }

    private boolean isPutRequest(HttpServletRequest request){
        return request.getMethod().equals(HttpMethod.PUT.name());
    }

    private boolean isDeleteRequest(HttpServletRequest request){
        return request.getMethod().equals(HttpMethod.DELETE.name());
    }
}
