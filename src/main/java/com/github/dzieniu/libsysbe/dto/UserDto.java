package com.github.dzieniu.libsysbe.dto;

import com.github.dzieniu.libsysbe.security.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String email;

    private Role role;

    private String firstName;

    private String lastName;

    private Integer numBorrowed;

    private Integer cashPenalty;
}
