package com.example.arproject1.utils;

public class EncoderUtil {


    //Encoder base64
    public static String Base64Encoder(String text) {
        byte[] encodedBytes = java.util.Base64.getEncoder().encode(text.getBytes());
        return new String(encodedBytes);
    }
    
}
