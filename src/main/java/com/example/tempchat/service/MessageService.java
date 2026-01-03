package com.example.tempchat.service;

import com.example.tempchat.domain.Message;
import com.example.tempchat.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MessageService {

    private MessageRepository messageRepository;

    public List<Message> getMessageContent(Long id){
        return messageRepository.findAllMessageById(id);
    }
}
