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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class LoginService {

    private final UserMapper userMapper;

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    private SessionController sessionController;
    public Boolean isUserExists(HttpSession httpSession, String username, String password){
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            if(passwordEncoder.matches(password,user.getPassword())){
                var userDto = userMapper.toDto(user);
                ifLoginSuccessFull(httpSession, userDto);
                return true;
            }
            return false;
        }
        return false;
    }

    public void ifLoginSuccessFull(HttpSession httpSession, UserDto userDto){
        sessionController.setAllSessionValue(httpSession, userDto);
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        Authentication auth = new UsernamePasswordAuthenticationToken(userDto, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
