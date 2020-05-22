package com.github.dzieniu.libsysbe.dto;

import com.github.dzieniu.libsysbe.security.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserToken {

    private String email;

    private Role role;

    private String firstName;

    private String lastName;

    private Integer numBorrowed;

    private Integer cashPenalty;

    private String token;
}
