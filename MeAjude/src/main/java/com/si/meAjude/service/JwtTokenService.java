package com.si.meAjude.service;

import com.si.meAjude.models.User;
import com.si.meAjude.service.impl.UserDetailsImpl;
import org.springframework.stereotype.Service;

@Service
public interface JwtTokenService {

    String generateToken(UserDetailsImpl user);

    String getSubjectFromToken(String token);
}
