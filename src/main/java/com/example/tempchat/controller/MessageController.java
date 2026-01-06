package com.example.tempchat.controller;

import com.example.tempchat.SessionUtility;
import com.example.tempchat.domain.Message;
import com.example.tempchat.service.MessageService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path="/message")
@Controller
@AllArgsConstructor
public class MessageController {

    private MessageService messageService;

    @GetMapping(path = "/{id}")
    public String renderMessageList(HttpSession httpSession, Model model, @PathVariable Long id){
        List<Message> messageList = messageService.getMessageContent(id);
        model.addAttribute("groupId", id);
        System.out.println("message list = " + messageList);
        System.out.println("session username" + SessionUtility.getSessionAttribute(httpSession, "username"));
        System.out.println("session username" + SessionUtility.getSessionAttribute(httpSession, "userId"));
        return "message";
    }


    @ResponseBody
    @PostMapping(path="/sendMessage")
    public Boolean saveMessage(HttpSession httpSession, @RequestParam("message") String message, @RequestParam("groupId") Long groupId){
        return messageService.saveMessage(httpSession, message, groupId);
    }

}
