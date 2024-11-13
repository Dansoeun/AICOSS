package com.alus.security.service;

import com.alus.security.dto.JoinDTO;
import com.alus.security.entity.UserEntity;
import com.alus.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String joinProcess(JoinDTO joinDTO)
    {
        //db에 이미 동일한 유저 있나 검증
        boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());
        if(isUser){
            return "이미 존재하는 사용자명입니다.";
        }

        boolean isEmail = userRepository.existsByEmail(joinDTO.getEmail());
        if(isEmail){
            return "이미 존재하는 이메일 입니다.";
        }

        UserEntity data = new UserEntity();
        data.setUsername(joinDTO.getUsername());
        data.setEmail(joinDTO.getEmail());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setRole("ROLE_USER");

        userRepository.save(data);
        return "회원가입이 성공하였습니다.";
    }
}
