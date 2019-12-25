package com.manning.aes.encryptor;

import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

/**
 * @author zhangyoubao
 * @version 2019/12/25
 */
public class Encryptors {

    public static final String AES_CBC_PKCS5PADDING = "AES/CBC/PKCS5Padding";

    public static final String AES_CBC_NOPADDING = "AES/CBC/NoPadding";

    public static final String AES_ECB_PKCS5PADDING = "AES/ECB/PKCS5Padding";

    public static final String AES_ECB_NOPADDING = "AES/ECB/NoPadding";

    private static final TextEncryptor NO_OP_TEXT_INSTANCE = new Encryptors.NoOpTextEncryptor();

    public static TextEncryptor textWithIv(String password, String salt, String aesMode, int keyLength) {
        return new HexEncodingTextEncryptor(standardWithIv(password, salt, aesMode, keyLength));
    }

    public static TextEncryptor textNoIv(String password, String salt, String aesMode, int keyLength) {
        return new HexEncodingTextEncryptor(standardNoIv(password, salt, aesMode, keyLength));
    }

    private static BytesEncryptor standardWithIv(String password, String salt, String aesMode, int keyLength) {
        return new AesBytesEncryptor(password, salt, KeyGenerators.secureRandom(16), aesMode, keyLength);
    }

    private static BytesEncryptor standardNoIv(String password, String salt, String aesMode, int keyLength) {
        return new AesBytesEncryptor(password, salt, aesMode, keyLength);
    }

    public static TextEncryptor noOpText() {
        return NO_OP_TEXT_INSTANCE;
    }

    private Encryptors() {
    }

    private static final class NoOpTextEncryptor implements TextEncryptor {

        private NoOpTextEncryptor() {
        }

        public String encrypt(String text) {
            return text;
        }

        public String decrypt(String encryptedText) {
            return encryptedText;
        }
    }
}
