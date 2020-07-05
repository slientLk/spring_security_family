package com.heima;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptPasswordEncoderTest {

    //$2a$10$LPbj8QSzpS0Z5U5B5.0BiOgR2dHIZXaqMeq8u.fLbaSlh7WmKVuoi
    //$2a$10$G.mwCmcM6KrIKluILvtpbOJOwDplHOjxz8qvu/EbRFah7Z9xe5eGm
    //$2a$10$Adl/f9uUF5dMnj0qiPA27eY6/MvttuHDodaj0/tAN7QTe02YIfID.
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456").length());
    }
}
