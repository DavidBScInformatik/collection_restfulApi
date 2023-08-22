package de.davidjahnbscinformatik.ITSonixExample.function;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Alternative zum Passwort verschlüsseln ohne Bean
 */
public class PasswordEncoder {

    public static String encodePassword(String plainPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(plainPassword);
    }
}
