package com.example.tempchat;

import com.example.tempchat.enums.DPImages;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class UtilityController {

    public static String getDisplayPicture(){
        List<String> images = DPImages.getDisplayImages();
        Random rn = new Random();
        return images.get(rn.nextInt(15));
    }

    public static Map<Long, String> getUserDPInfoFromSession(HttpSession httpSession){
        return  (Map<Long, String>) SessionUtility.getSessionAttribute(httpSession, "dPInfoMap");
    }
}
