package com.example.tempchat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private long id;

    private String message;

    private long groupId;

    private long sentBy;
}
