package com.si.meAjude.service;

import com.si.meAjude.models.User;
import org.springframework.stereotype.Service;

@Service
public interface JwtTokenService {

    String generateToken(User user);

    String getSubjectFromToken(String token);
}
