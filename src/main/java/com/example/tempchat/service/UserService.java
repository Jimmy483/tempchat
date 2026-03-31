package com.example.tempchat.service;

import com.example.tempchat.SessionController;
import com.example.tempchat.SessionUtility;
import com.example.tempchat.domain.User;
import com.example.tempchat.dto.UserCreateDto;
import com.example.tempchat.mapper.UserMapper;
import com.example.tempchat.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User getCurrentUser(long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User findUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public void createUser(UserCreateDto userCreateDto){
        String password = bCryptPasswordEncoder.encode(userCreateDto.getPassword());
        userCreateDto.setPassword(password);
        User user = userMapper.toUserCreateEntity(userCreateDto);
        userRepository.save(user);
    }

    public String checkIfUserExists(String username){
        User user=userRepository.findByUsername(username).orElse(null);
        System.out.println("user = " + user);
        return user!=null?"true":"false";
    }
}
