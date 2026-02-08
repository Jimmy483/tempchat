package com.example.tempchat.enums;

import java.util.ArrayList;
import java.util.List;

public enum DPImages {

    faceOne, faceTwo, faceThree, faceFour, faceFive, faceSix, faceSeven, faceEight, faceNine, faceTen,
    faceEleven, faceTwelve, faceThirteen, faceFourteen, faceFifteen;

    public static List<String> getDisplayImages(){
        List<String> displayImages = new ArrayList<>();
        for(DPImages images:DPImages.values()){
            displayImages.add(images.name());
        }
        System.out.println("dp images = " + displayImages);
        return displayImages;
    }
}
