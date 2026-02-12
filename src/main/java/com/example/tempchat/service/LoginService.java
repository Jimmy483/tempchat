package com.example.tempchat.service;

import com.example.tempchat.SessionController;
import com.example.tempchat.SessionUtility;
import com.example.tempchat.domain.User;
import com.example.tempchat.dto.UserDto;
import com.example.tempchat.mapper.UserMapper;
import com.example.tempchat.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class LoginService {

    private final UserMapper userMapper;

    private UserRepository userRepository;

    private SessionController sessionController;
    public Boolean isUserExists(HttpSession httpSession, String username, String password){
        User user = userRepository.findByUsernameAndPassword(username, password).orElse(null);
        System.out.println("user = " + user);

        if (user != null) {
            var userDto = userMapper.toDto(user);
            ifLoginSuccessFull(httpSession, userDto);
            return true;
        }
        return false;
    }

    public void ifLoginSuccessFull(HttpSession httpSession, UserDto userDto){
        sessionController.setAllSessionValue(httpSession, userDto);
    }
}
