package Willy.Util.Crypt;


import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class AsymmetricCipher {

    public static String crypt(String plainText, PublicKey key, String algorithm) throws IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE,key);

        byte[] messageBytes = plainText.getBytes(StandardCharsets.UTF_8);
        messageBytes = cipher.doFinal(messageBytes);
        return Base64.getEncoder().encodeToString(messageBytes);
    }

    public static String decrypt(String cipherText, PrivateKey key, String algorithm) throws IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] messageBytes = Base64.getDecoder().decode(cipherText);
        messageBytes = cipher.doFinal(messageBytes);
        return new String(messageBytes, StandardCharsets.UTF_8);
    }

    public static KeyPair genKey(String algorithm) throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(algorithm);
        generator.initialize(1024);

        return generator.generateKeyPair();
    }
}
