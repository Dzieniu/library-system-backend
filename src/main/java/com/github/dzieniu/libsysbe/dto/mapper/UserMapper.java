package com.github.dzieniu.libsysbe.dto.mapper;

import com.github.dzieniu.libsysbe.dto.UserDto;
import com.github.dzieniu.libsysbe.entity.User;

public class UserMapper {

    public static UserDto toDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRole(user.getRole());
        if (user.getReader()!=null) {
            userDto.setNumBorrowed(user.getReader().getNumBorrowed());
            userDto.setCashPenalty(user.getReader().getCashPenalty());
        }
        return userDto;
    }
}