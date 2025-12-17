package com.example.tempchat;

import jakarta.servlet.http.HttpSession;

public class SessionUtility {


    public static void setSessionAttribute(HttpSession httpSession, String key, Object value){
        httpSession.setAttribute(key, value);
    }

    public static Object getSessionAttribute(HttpSession httpSession, String key){
        return httpSession.getAttribute(key);
    }
}