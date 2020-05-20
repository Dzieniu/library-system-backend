package com.github.dzieniu.libsysbe.controller;

import com.github.dzieniu.libsysbe.dto.UserDto;
import com.github.dzieniu.libsysbe.dto.mapper.UserMapper;
import com.github.dzieniu.libsysbe.entity.User;
import com.github.dzieniu.libsysbe.exception.AuthenticationException;
import com.github.dzieniu.libsysbe.repository.UserRepository;
import com.github.dzieniu.libsysbe.security.token.AuthToken;
import com.github.dzieniu.libsysbe.security.token.JwtTokenUtil;
import com.github.dzieniu.libsysbe.security.user.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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

    /*
        logowanie sie (obecnie nie jest wymagane) - zwraca token jwt
        przyklad: http://localhost:8080/authenticate
        {
	        "username": "jacekp@gmail.com",
	        "password": "jacekp"
        }
     */
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

    /*
        zwrócenie zalogowanego użytkownika na podstawie tokenu
        przyklad: http://localhost:8080/authenticate
     */
    @GetMapping
    public UserDto getAuthenticatedUserFromToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(
                SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken) ){
            User user = userRepository.findByEmail(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
            return UserMapper.toDto(user);
        } else throw new AuthenticationException("User is not authenticated!");
    }
}