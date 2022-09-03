package com.example.final_task_epam.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {

    public static String hash(String password)  {

        MessageDigest md= null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } finally {
        }
        md.update(password.getBytes());

        byte[] digest=md.digest();
        StringBuffer sb=new StringBuffer();
        for(byte b:digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}
