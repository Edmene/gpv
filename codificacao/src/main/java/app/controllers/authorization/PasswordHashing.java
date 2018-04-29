package app.controllers.authorization;

import app.models.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class PasswordHashing {

    private byte salt[] = new byte[40];

    public PasswordHashing(){
        setSalt();
    }

    private void setSalt() {
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
    }

    public byte[] getSalt() {
        return salt;
    }

    public byte[] hashPassword(String password) throws Exception{
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        return keyFactory.generateSecret(spec).getEncoded();
    }

    public static boolean checkPasswordHash(String password, User user) throws Exception{
        byte[] userSalt = user.getBytes("extra");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), userSalt, 65536, 256);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hash = keyFactory.generateSecret(spec).getEncoded();
        return Arrays.equals(user.getBytes("password"), hash);
    }
}
