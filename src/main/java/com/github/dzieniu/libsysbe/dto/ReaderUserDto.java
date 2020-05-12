package com.github.dzieniu.libsysbe.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReaderUserDto {

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private int num_borrowed;

    private int cash_penalty;
}
