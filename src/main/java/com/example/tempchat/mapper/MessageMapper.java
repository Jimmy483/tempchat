package com.example.tempchat.mapper;

import com.example.tempchat.domain.Message;
import com.example.tempchat.dto.MessageDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface MessageMapper {

    MessageDto toMessageDto(Message message);
}
