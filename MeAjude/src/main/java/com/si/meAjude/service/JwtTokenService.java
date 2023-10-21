package com.si.meAjude.service;

import com.si.meAjude.models.UserDetailsImpl;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public interface JwtTokenService {

    String generateToken(UserDetailsImpl user);

    String getSubjectFromToken(String token);
}
