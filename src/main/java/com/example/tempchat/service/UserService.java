package com.example.tempchat.service;

import com.example.tempchat.SessionController;
import com.example.tempchat.SessionUtility;
import com.example.tempchat.domain.User;
import com.example.tempchat.dto.UserCreateDto;
import com.example.tempchat.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User getCurrentUser(long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User findUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public void createUser(UserCreateDto userCreateDto){
        User user = new User();
        user.setFirstName(userCreateDto.getFName());
        user.setLastName(userCreateDto.getLName());
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());
        userRepository.save(user);
    }
}
