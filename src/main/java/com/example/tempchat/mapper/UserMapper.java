package com.example.tempchat.mapper;

import com.example.tempchat.domain.User;
import com.example.tempchat.dto.UserCreateDto;
import com.example.tempchat.dto.UserDto;
import lombok.Data;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {

//    adding disableBuilder = false solved the null issue. better practice than using mapping

    UserDto toDto(User user);

    UserCreateDto toUserCreateDto(User user);
}
