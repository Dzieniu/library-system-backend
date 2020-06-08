package com.github.dzieniu.libsysbe.service;

import com.github.dzieniu.libsysbe.dto.UserReaderDto;
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
    public void signUp(UserReaderDto userReaderDto){
        User user = User.builder()
                .email(userReaderDto.getEmail())
                .password(bCryptPasswordEncoder.encode(userReaderDto.getPassword()))
                .role(Role.ROLE_READER)
                .firstName(userReaderDto.getFirstName())
                .lastName(userReaderDto.getLastName())
                .build();
        userRepository.save(user);
        Reader reader = Reader.builder()
                .numBorrowed(0)
                .cashPenalty(0)
                .user(user)
                .build();
        readerRepository.save(reader);
    }
}
