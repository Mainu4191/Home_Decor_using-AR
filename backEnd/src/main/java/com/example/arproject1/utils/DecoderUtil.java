package com.example.arproject1.utils;

public class DecoderUtil {

    public static byte[] Base64Decoder(String text) {
        byte[] decodedBytes = java.util.Base64.getDecoder().decode(text);
        return decodedBytes;
    }
}
