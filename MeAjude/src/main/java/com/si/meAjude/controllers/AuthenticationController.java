package com.si.meAjude.controllers;

import com.si.meAjude.models.User;
import com.si.meAjude.repositories.UserRepository;
import com.si.meAjude.service.dtos.user.LoginUserDTO;
import com.si.meAjude.service.impl.JwtTokenServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenServiceImpl jwtTokenServiceImpl;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginUserDTO data){
        String token = "";
        try{
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());        var auth = this.authenticationManager.authenticate(usernamePassword);
            token = jwtTokenServiceImpl.generateToken((User) auth.getPrincipal());
        }catch (Exception e){
            throw new EntityNotFoundException("invalid email or password");
        }
        return new ResponseEntity("{\"token\":\""  + token+"\"}", org.springframework.http.HttpStatus.OK);
    }
}
