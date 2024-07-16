package com.websystem.libspace.service;

import com.websystem.libspace.domain.users.User;
import com.websystem.libspace.domain.users.UserRequestDTO;
import com.websystem.libspace.domain.users.UserResponseDTO;
import com.websystem.libspace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO create(UserRequestDTO body){

        String passwordEncripty = passwordEncoder.encode(body.password());

        User user = new User(body, passwordEncripty);

        userRepository.save(user);

        return new UserResponseDTO(user);

    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<UserResponseDTO> findAll(){

        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::new)
                .toList();

    }




}
