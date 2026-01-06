package com.example.tempchat.service;

import com.example.tempchat.domain.GroupList;
import com.example.tempchat.domain.Message;
import com.example.tempchat.domain.User;
import com.example.tempchat.repository.GroupListRepository;
import com.example.tempchat.repository.MessageRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MessageService {

    private MessageRepository messageRepository;

    private GroupListRepository groupListRepository;

    private UserService userService;

    public List<Message> getMessageContent(Long id){
        return messageRepository.findAllMessageById(id);
    }

    public Boolean saveMessage(HttpSession httpSession, String message, long groupId){
        GroupList group = groupListRepository.findById(groupId).orElse(null);
        User user = userService.getCurrentUser(httpSession);
        if(group!=null){
            Message msg = new Message();
            msg.setMessage(message);
            msg.setGroupList(group);
            msg.setUser(user);
            messageRepository.save(msg);
            return true;
        }
        return false;

    }
}
