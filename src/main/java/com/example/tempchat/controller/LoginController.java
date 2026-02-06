package com.example.tempchat.controller;

import com.example.tempchat.domain.User;
import com.example.tempchat.dto.UserDto;
import com.example.tempchat.repository.UserRepository;
import com.example.tempchat.service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@AllArgsConstructor
@Controller
public class LoginController {

    private LoginService loginService;

    private UserRepository userRepository;

    @GetMapping(path="/login")
    public String goToLogin(){
        return "login";
    }

    @ResponseBody
    @GetMapping(path="/checkCredentials")
    public Boolean checkCredentials(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession httpSession){
        return loginService.isUserExists(httpSession, username, password);
    }
}
