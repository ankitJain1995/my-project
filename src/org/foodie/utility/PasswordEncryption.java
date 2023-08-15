package org.foodie.utility;

import java.util.Base64;

/**
 *
 * @author AFROZ
 */
public class PasswordEncryption {

    public static String getEncryptedPassword(String password) {
        Base64.Encoder encoder = Base64.getEncoder();

        return encoder.encodeToString(password.getBytes());
    }

    public static String getDecryptedPassword(String password) {
        Base64.Decoder decoder = Base64.getDecoder();

        return new String(decoder.decode(password.getBytes()));
    }
}
