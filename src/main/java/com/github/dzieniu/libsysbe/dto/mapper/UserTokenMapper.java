package com.github.dzieniu.libsysbe.dto.mapper;

import com.github.dzieniu.libsysbe.dto.UserToken;
import com.github.dzieniu.libsysbe.entity.User;

public class UserTokenMapper {

    public static UserToken toDto(User user, String token){
        UserToken userToken = new UserToken();
        userToken.setEmail(user.getEmail());
        if (user.getReader()!=null) {
            userToken.setNumBorrowed(user.getReader().getNumBorrowed());
            userToken.setCashPenalty(user.getReader().getCashPenalty());
        }
        userToken.setFirstName(user.getFirstName());
        userToken.setLastName(user.getLastName());
        userToken.setRole(user.getRole());
        userToken.setToken(token);
        return userToken;
    }
}