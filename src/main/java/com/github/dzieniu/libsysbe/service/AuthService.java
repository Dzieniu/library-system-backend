package com.github.dzieniu.libsysbe.service;

import com.github.dzieniu.libsysbe.entity.User;
import com.github.dzieniu.libsysbe.exception.AuthenticationException;
import com.github.dzieniu.libsysbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(
                SecurityContextHolder.getContext().getAuthentication() != null &&
                        SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                        !(SecurityContextHolder.getContext().getAuthentication()
                                instanceof AnonymousAuthenticationToken) ){
            User user = userRepository.findByEmail(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
            return user;
        } else throw new AuthenticationException("User is not authenticated!");
    }
}
