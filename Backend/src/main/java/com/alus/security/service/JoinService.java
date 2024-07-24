package com.alus.security.service;

import com.alus.security.dto.JoinDTO;
import com.alus.security.entity.UserEntity;
import com.alus.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO)
    {
        //db에 이미 동일한 유저 있나 검증
        boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());
        if(isUser){
            return;
        }

        UserEntity data = new UserEntity();
        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setRole("ROLE_USER");

        userRepository.save(data);
    }
}
