package com.github.dzieniu.libsysbe.service;

import com.github.dzieniu.libsysbe.dto.ReaderDto;
import com.github.dzieniu.libsysbe.entity.Reader;
import com.github.dzieniu.libsysbe.entity.User;
import com.github.dzieniu.libsysbe.repository.ReaderRepository;
import com.github.dzieniu.libsysbe.repository.UserRepository;
import com.github.dzieniu.libsysbe.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Rejestracja czytelnika w systemie
    @Transactional
    public void signUp(ReaderDto readerDto){
        User user = User.builder()
                .email(readerDto.getEmail())
                .password(bCryptPasswordEncoder.encode(readerDto.getPassword()))
                .role(Role.ROLE_READER)
                .firstName(readerDto.getFirstName())
                .lastName(readerDto.getLastName())
                .build();
        userRepository.save(user);
        Reader reader = Reader.builder()
                .numBorrowed(0)
                .cashPenalty(0)
                .build();
        readerRepository.save(reader);
    }
}
