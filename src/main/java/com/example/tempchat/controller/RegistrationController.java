package com.example.tempchat.controller;

import com.example.tempchat.domain.User;
import com.example.tempchat.dto.UserCreateDto;
import com.example.tempchat.dto.UserDto;
import com.example.tempchat.mapper.UserMapper;
import com.example.tempchat.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final UserMapper userMapper;

    private UserService userService;

    @GetMapping(path = "/register")
    public String loadRegistrationForm(Model model){
        model.addAttribute("user", userMapper.toUserCreateDto(new User()));
        return "registration";
    }

    @PostMapping(path="/registerUser")
    public String registerUser(@ModelAttribute("user") UserCreateDto userCreateDto){
        userService.createUser(userCreateDto);
        return "redirect:/login";
    }

    @ResponseBody
    @GetMapping(path="checkUser")
    public String checkIfUserExists(@RequestParam("username") String username){
        return userService.checkIfUserExists(username);
    }
}
