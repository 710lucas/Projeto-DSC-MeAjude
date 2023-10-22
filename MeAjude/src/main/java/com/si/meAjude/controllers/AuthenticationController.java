package com.si.meAjude.controllers;

import com.si.meAjude.models.Role;
import com.si.meAjude.models.User;
import com.si.meAjude.repositories.UserRepository;
import com.si.meAjude.service.dtos.user.CreateUserDTO;
import com.si.meAjude.service.dtos.user.LoginUserDTO;
import com.si.meAjude.service.impl.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginUserDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new ResponseEntity("Token: " + token, org.springframework.http.HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CreateUserDTO createUserDTO){
        if(this.repository.findByEmail(createUserDTO.email()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(createUserDTO.password());
        User newUser = new User(createUserDTO.email(), encryptedPassword, createUserDTO.role());
        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
