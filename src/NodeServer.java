import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class NodeServer{
        private Cipher cipher;
        private Keys keys;

        public NodeServer(Keys keys) throws NoSuchAlgorithmException, NoSuchPaddingException {
                this.cipher = Cipher.getInstance("RSA");
                this.keys = keys;
        }
        public

        public PrivateKey getPrivate(String filename) throws Exception {
                byte[] keyByte = keys.getPrivateKey().getEncoded();
                byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
                PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
                KeyFactory kf = KeyFactory.getInstance("RSA");
                return kf.generatePrivate(spec);
        }

        // https://docs.oracle.com/javase/8/docs/api/java/security/spec/X509EncodedKeySpec.html
        public PublicKey getPublic(String filename) throws Exception {
                byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
                X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
                KeyFactory kf = KeyFactory.getInstance("RSA");
                return kf.generatePublic(spec);
        }
}
