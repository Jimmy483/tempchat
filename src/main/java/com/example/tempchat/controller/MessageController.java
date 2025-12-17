package com.example.tempchat.controller;

import com.example.tempchat.SessionUtility;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path="/")
@Controller
public class MessageController {

    @GetMapping
    public String renderMessageList(HttpSession httpSession){
        System.out.println("session username" + SessionUtility.getSessionAttribute(httpSession, "username"));
        return "message";
    }
}
