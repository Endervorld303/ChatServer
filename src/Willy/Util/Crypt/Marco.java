package Willy.Util.Crypt;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

public class Marco {
    public static void main(String[] args) {
        try {
            String algorithm = "RSA";
            KeyPair pair = AsymmetricCipher.genKey(algorithm);
            String msg = AsymmetricCipher.crypt("Marco B porterai minecraft? ", pair.getPublic(),algorithm);
            System.out.println(msg);
            System.out.println(AsymmetricCipher.decrypt(msg,pair.getPrivate(),algorithm));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }

    }
}
