package com.example.tempchat.controller;

import com.example.tempchat.SessionUtility;
import com.example.tempchat.domain.Message;
import com.example.tempchat.dto.MessageDto;
import com.example.tempchat.dto.MessageSent;
import com.example.tempchat.service.MessageService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path="/message")
@Controller
@AllArgsConstructor
public class MessageController {

    private MessageService messageService;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Map<String, Object> send(MessageSent message, SimpMessageHeaderAccessor headerAccessor){
        messageService.saveMessage(message.getSentBy(), message.getMessage(), message.getGroupId());
        return messageService.toMessageMapWithUsername(message, headerAccessor);
    }
    @GetMapping(path = "/{id}")
    public String renderMessageList(HttpSession httpSession, Model model, @PathVariable Long id){
        List<Object> messageList = messageService.getMessageContent(id, httpSession);
        model.addAttribute("groupId", id);
        model.addAttribute("message", messageList);
        return "message";
    }


    @ResponseBody
    @PostMapping(path="/sendMessage")
    public Boolean saveMessage(@RequestBody MessageSent message){
        return messageService.saveMessage(message.getSentBy(), message.getMessage(), message.getGroupId());
    }

}
