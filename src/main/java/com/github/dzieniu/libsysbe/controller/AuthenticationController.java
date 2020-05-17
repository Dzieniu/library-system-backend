package com.github.dzieniu.libsysbe.controller;

import com.github.dzieniu.libsysbe.entity.User;
import com.github.dzieniu.libsysbe.repository.UserRepository;
import com.github.dzieniu.libsysbe.security.token.AuthToken;
import com.github.dzieniu.libsysbe.security.token.JwtTokenUtil;
import com.github.dzieniu.libsysbe.security.user.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/*
    http://localhost:8080/authenticate
*/

@RestController
@RequestMapping("authenticate")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    // Logowanie się
    @PostMapping
    public ResponseEntity<?> getAuthenticationToken(@RequestBody UserCredentials userCredentials) {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userCredentials.getUsername(),
                        userCredentials.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User user = userRepository.findByEmail(userCredentials.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new AuthToken(token));
    }
}