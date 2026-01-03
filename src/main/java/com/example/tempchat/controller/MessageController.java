package com.example.tempchat.controller;

import com.example.tempchat.SessionUtility;
import com.example.tempchat.domain.Message;
import com.example.tempchat.service.MessageService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(path="/message")
@Controller
@AllArgsConstructor
public class MessageController {

    private MessageService messageService;

    @GetMapping(path = "/{id}")
    public String renderMessageList(HttpSession httpSession, @PathVariable Long id){
        List<Message> messageList = messageService.getMessageContent(id);
        System.out.println("message list = " + messageList);
        System.out.println("session username" + SessionUtility.getSessionAttribute(httpSession, "username"));
        return "message";
    }

}
