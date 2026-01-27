package com.example.tempchat.service;

import com.example.tempchat.SessionController;
import com.example.tempchat.SessionUtility;
import com.example.tempchat.domain.User;
import com.example.tempchat.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User getCurrentUser(HttpSession httpSession) {
        return userRepository.findById(Long.parseLong(SessionUtility.getSessionAttribute(httpSession,"userId").toString())).orElse(null);
    }

    public User findUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }
}
