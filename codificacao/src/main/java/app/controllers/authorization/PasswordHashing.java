package app.controllers.authorization;

import app.models.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class PasswordHashing {

    private byte salt[] = new byte[40];
    private Base64.Encoder encoder = Base64.getEncoder().withoutPadding();
    private static Base64.Decoder decoder = Base64.getDecoder();

    public PasswordHashing(){
        setSalt();
    }

    private void setSalt() {
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
    }

    public String getSalt() {
        return encoder.encodeToString(salt);
    }

    public String hashPassword(String password) throws Exception{

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        return encoder.encodeToString(keyFactory.generateSecret(spec).getEncoded());
    }

    public static PasswordCheck checkPasswordHash(String password, User user) throws Exception{
        Base64.Encoder encoder = Base64.getEncoder().withoutPadding();

        byte[] userSalt = decoder.decode((String) user.get("extra"));
        KeySpec spec = new PBEKeySpec(password.toCharArray(), userSalt, 65536, 256);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hash = keyFactory.generateSecret(spec).getEncoded();
        //return Arrays.equals(user.getBytes("password"), hash);
        PasswordCheck passwordCheck = new PasswordCheck();
        //passwordCheck.salt = Base64.getUrlEncoder().withoutPadding().encodeToString(userSalt);
        //passwordCheck.equals = Arrays.equals(user.getBytes("password"), hash);
        passwordCheck.equals = Arrays.equals(decoder.decode(user.get("password").toString()), hash);
        passwordCheck.salt = encoder.encodeToString(hash);
        return passwordCheck;
    }

    public static class PasswordCheck {
        public String salt;
        public boolean equals;
    }
}
