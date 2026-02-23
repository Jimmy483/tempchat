package com.example.tempchat.service;

import com.example.tempchat.SessionController;
import com.example.tempchat.SessionUtility;
import com.example.tempchat.UtilityController;
import com.example.tempchat.enums.DPImages;
import com.example.tempchat.domain.GroupList;
import com.example.tempchat.domain.Message;
import com.example.tempchat.domain.User;
import com.example.tempchat.dto.MessageSent;
import com.example.tempchat.repository.GroupListRepository;
import com.example.tempchat.repository.MessageRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MessageService {

    private MessageRepository messageRepository;

    private GroupListRepository groupListRepository;

    private UserService userService;


    public List<Object> getMessageContent(Long id, HttpSession httpSession){
        Map<Long, String> dPInfoMap = new HashMap<>(UtilityController.getUserDPInfoFromSession(httpSession));
        List<Object> mainList = messageRepository.findAllByGroupList_Id(id).stream().map(message -> {
            Map<String, Object> returnMap = new HashMap<>();
            User user= userService.findUserById(message.getUser().getId());
            returnMap.put("id", message.getId());
            returnMap.put("content", message.getMessage());
            returnMap.put("groupId", message.getGroupList().getId());
            returnMap.put("senderId", message.getUser().getId());
            returnMap.put("senderName", user.getUsername());
            if(dPInfoMap.get(message.getUser().getId())!=null){
                returnMap.put("displayPicture", dPInfoMap.get(message.getUser().getId()));
            }else {
                String displayPicture = "/images/" + UtilityController.getDisplayPicture() + ".jpg";
                returnMap.put("displayPicture", displayPicture);
                dPInfoMap.put(message.getUser().getId(), displayPicture);
            }
            return returnMap;
        }).collect(Collectors.toList());

        SessionController.setSessionValue(httpSession,"dPInfoMap", dPInfoMap);
        return mainList;
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

    public Map<String, Object> toMessageMapWithUsername(MessageSent messageSent, SimpMessageHeaderAccessor headerAccessor){
        Map<String, Object> toReturnMap = new HashMap<>();
        Map<Long, String> dpInfoMap = (Map<Long, String>) headerAccessor.getSessionAttributes().get("dPInfoMap");
        Map<String, Object> session = headerAccessor.getSessionAttributes();
        User user =userService.getCurrentUser(messageSent.getSentBy());
        toReturnMap.put("senderId", messageSent.getSentBy());
        toReturnMap.put("message", messageSent.getMessage());
        toReturnMap.put("senderUsername",user.getUsername());
        if(dpInfoMap.get(messageSent.getSentBy())!=null){
            toReturnMap.put("displayPicture",dpInfoMap.get(messageSent.getSentBy()));
        }else {
            String displayPic = "/images/" + UtilityController.getDisplayPicture() + ".jpg";
            toReturnMap.put("displayPicture",displayPic);
            dpInfoMap.put(messageSent.getSentBy(),displayPic);
            session.put("dPInfoMap", dpInfoMap);
        }
        toReturnMap.put("groupId", messageSent.getGroupId()); // for future use in case there are multiple groups
        return toReturnMap;
    }
}
