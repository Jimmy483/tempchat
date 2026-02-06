package com.example.tempchat.dto;

import lombok.Data;

@Data
public class MessageSent {

    private String message;

    private long groupId;

    private long sentBy;
}
