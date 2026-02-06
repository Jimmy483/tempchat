package com.example.tempchat.service;

import com.example.tempchat.domain.GroupList;
import com.example.tempchat.domain.Message;
import com.example.tempchat.domain.User;
import com.example.tempchat.dto.MessageSent;
import com.example.tempchat.repository.GroupListRepository;
import com.example.tempchat.repository.MessageRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MessageService {

    private MessageRepository messageRepository;

    private GroupListRepository groupListRepository;

    private UserService userService;

    public List<Object> getMessageContent(Long id){
        return messageRepository.findAllByGroupList_Id(id).stream().map(message -> {
            Map<String, Object> returnMap = new HashMap<>();
            User user= userService.findUserById(message.getUser().getId());
            returnMap.put("id", message.getId());
            returnMap.put("content", message.getMessage());
            returnMap.put("groupId", message.getGroupList().getId());
            returnMap.put("senderId", message.getUser().getId());
            returnMap.put("senderName", user.getUsername());
            return returnMap;
        }).collect(Collectors.toList());
    }

    public Boolean saveMessage(long userId, String message, long groupId){
        GroupList group = groupListRepository.findById(groupId).orElse(null);
        User user = userService.getCurrentUser(userId);
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

    public Map<String, Object> toMessageMapWithUsername(MessageSent messageSent){
        Map<String, Object> toReturnMap = new HashMap<>();
        User user =userService.getCurrentUser(messageSent.getSentBy());
        toReturnMap.put("senderId", messageSent.getSentBy());
        toReturnMap.put("message", messageSent.getMessage());
        toReturnMap.put("senderUsername",user.getUsername());
        toReturnMap.put("groupdId", messageSent.getGroupId()); // for future use in case there are multiple groups
        return toReturnMap;
    }
}
