package com.example.tfgfontanet.common.utiles.mail;

import java.security.SecureRandom;
import java.util.Base64;

public class RandomBytes {
    private RandomBytes() {}
    public static String randomBytes()
    {
        SecureRandom sr = new SecureRandom();
        byte[] bits = new byte[32];
        sr.nextBytes(bits);
        return Base64.getUrlEncoder().encodeToString(bits);
    }
}
