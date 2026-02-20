package com.example.tempchat;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Base64;

public class CryptoUtil {

    private static final String SECRET_KEY = "TheOnlyMasterKey";

    public static String encrypt(String stringToEncrypt){
        try{
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(stringToEncrypt.getBytes()));
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public static String decrypt(String stringToDecrypt){
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(stringToDecrypt)));
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
