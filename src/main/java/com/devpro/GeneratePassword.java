package com.devpro;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratePassword {
    public static String GenerPass(String passwd) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);
        passwd = encoder.encode(passwd);
        return passwd;
    }

    public static void main(String[] args) {
        GenerPass("$2a$08$qTou0fASa7m3eabLwLv4WeOY1/1U2Xgg4Qcr2u9tJyp5HB5qosWue");
    }
}
