package com.manning.aes.utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * @author zhangyoubao
 * @version 2019/12/25
 */
public class CipherUtils {

    public static SecretKey newSecretKey(String algorithm, PBEKeySpec keySpec) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
            return factory.generateSecret(keySpec);
        } catch (NoSuchAlgorithmException var3) {
            throw new IllegalArgumentException("Not a valid encryption algorithm", var3);
        } catch (InvalidKeySpecException var4) {
            throw new IllegalArgumentException("Not a valid secret key", var4);
        }
    }

    public static Cipher newCipher(String algorithm) {
        try {
            return Cipher.getInstance(algorithm);
        } catch (NoSuchAlgorithmException var2) {
            throw new IllegalArgumentException("Not a valid encryption algorithm", var2);
        } catch (NoSuchPaddingException var3) {
            throw new IllegalStateException("Should not happen", var3);
        }
    }

    public static void initCipher(Cipher cipher, int mode, SecretKey secretKey, AlgorithmParameterSpec parameterSpec) {
        try {
            if (parameterSpec != null) {
                cipher.init(mode, secretKey, parameterSpec);
            } else {
                cipher.init(mode, secretKey);
            }

        } catch (InvalidKeyException var5) {
            throw new IllegalArgumentException("Unable to initialize due to invalid secret key", var5);
        } catch (InvalidAlgorithmParameterException var6) {
            throw new IllegalStateException("Unable to initialize due to invalid decryption parameter spec", var6);
        }
    }

    public static byte[] doFinal(Cipher cipher, byte[] input) {
        try {
            return cipher.doFinal(input);
        } catch (IllegalBlockSizeException var3) {
            throw new IllegalStateException("Unable to invoke Cipher due to illegal block size", var3);
        } catch (BadPaddingException var4) {
            throw new IllegalStateException("Unable to invoke Cipher due to bad padding", var4);
        }
    }

    private CipherUtils() {
    }
}
