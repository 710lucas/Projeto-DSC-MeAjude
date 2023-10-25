package com.si.meAjude.controllers;

import com.si.meAjude.models.User;
import com.si.meAjude.repositories.UserRepository;
import com.si.meAjude.service.dtos.user.LoginUserDTO;
import com.si.meAjude.service.impl.JwtTokenServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "Authentication management APIs")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenServiceImpl jwtTokenServiceImpl;


    @Operation(
            summary = "User Login",
            description = "Authenticate a user by providing valid login credentials in the request body. Returns a JWT token upon successful authentication."
            )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Authentication successful. Returns a JWT token."),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "401", description = "Authentication failed. Invalid credentials."),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginUserDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = jwtTokenServiceImpl.generateToken((User) auth.getPrincipal());

        return new ResponseEntity("Token: " + token, org.springframework.http.HttpStatus.OK);
    }
}
