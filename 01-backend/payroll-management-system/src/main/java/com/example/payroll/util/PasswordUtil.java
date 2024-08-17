package com.example.payroll.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    public static BCryptPasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = getEncoder();
        return encoder.encode(password);
    }

    public static boolean checkPassword(String password, String encryptedPassword) {
        BCryptPasswordEncoder encoder = getEncoder();
        return encoder.matches(password, encryptedPassword);
    }
}
