package com.example.tempchat;

import com.example.tempchat.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

@Controller
public class SessionController {

    public void setSessionValue(HttpSession httpSession, UserDto userDto){
        SessionUtility.setSessionAttribute(httpSession, "username", userDto.getUsername());
        SessionUtility.setSessionAttribute(httpSession, "firstName", userDto.getFirstName());
        SessionUtility.setSessionAttribute(httpSession, "lastName", userDto.getLastName());
        SessionUtility.setSessionAttribute(httpSession, "userId", userDto.getId());
        SessionUtility.setSessionAttribute(httpSession, "displayImage","/images/" + UtilityController.getDisplayPicture() + ".jpg");
        System.out.println("user id from session is " + userDto.getId());
    }

    public Object getSessionValue(HttpSession httpSession, String key){
        return SessionUtility.getSessionAttribute(httpSession, key);
    }

}