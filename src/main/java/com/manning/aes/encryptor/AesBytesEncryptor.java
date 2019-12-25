package com.manning.aes.encryptor;

import com.manning.aes.utils.CipherUtils;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.util.EncodingUtils;

/**
 * @author zhangyoubao
 * @version 2019/12/25
 */
final class AesBytesEncryptor implements BytesEncryptor {

    private static final String SECRET_KEY_FACTORY_ALGORITHM_PBKDF2 = "PBKDF2WithHmacSHA1";

    private final SecretKey secretKey;
    private final Cipher encryptor;
    private final Cipher decryptor;
    private final BytesKeyGenerator ivGenerator;

    private static final BytesKeyGenerator NULL_IV_GENERATOR = new BytesKeyGenerator() {
        private final byte[] VALUE = new byte[16];

        public int getKeyLength() {
            return this.VALUE.length;
        }

        public byte[] generateKey() {
            return this.VALUE;
        }
    };

    AesBytesEncryptor(String password, String salt, String aesMode, int keyLength) {
        this(password, salt, null, aesMode, keyLength);
    }

    AesBytesEncryptor(String password, String salt, BytesKeyGenerator ivGenerator, String aesMode,
            int keyLength) {
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray(), Hex.decode(salt), 1024, keyLength);
        SecretKey secretKey = CipherUtils.newSecretKey(SECRET_KEY_FACTORY_ALGORITHM_PBKDF2, keySpec);
        this.secretKey = new SecretKeySpec(secretKey.getEncoded(), "AES");
        this.encryptor = CipherUtils.newCipher(aesMode);
        this.decryptor = CipherUtils.newCipher(aesMode);
        this.ivGenerator = ivGenerator != null ? ivGenerator : NULL_IV_GENERATOR;
    }

    @Override
    public byte[] encrypt(byte[] bytes) {
        synchronized (this.encryptor) {
            byte[] iv = this.ivGenerator.generateKey();
            CipherUtils.initCipher(this.encryptor, 1, this.secretKey,
                    this.ivGenerator != NULL_IV_GENERATOR ? new IvParameterSpec(iv) : null);
            byte[] encrypted = CipherUtils.doFinal(this.encryptor, bytes);
            return this.ivGenerator != NULL_IV_GENERATOR ? EncodingUtils.concatenate(iv, encrypted)
                    : encrypted;
        }
    }

    @Override
    public byte[] decrypt(byte[] encryptedBytes) {
        synchronized (this.decryptor) {
            byte[] iv = this.iv(encryptedBytes);
            CipherUtils.initCipher(this.decryptor, 2, this.secretKey,
                    this.ivGenerator != NULL_IV_GENERATOR ? new IvParameterSpec(iv) : null);
            return CipherUtils.doFinal(this.decryptor,
                    this.ivGenerator != NULL_IV_GENERATOR ? this.encrypted(encryptedBytes, iv.length) : encryptedBytes);
        }
    }

    private byte[] iv(byte[] encrypted) {
        return this.ivGenerator != NULL_IV_GENERATOR ? EncodingUtils
                .subArray(encrypted, 0, this.ivGenerator.getKeyLength()) : NULL_IV_GENERATOR.generateKey();
    }

    private byte[] encrypted(byte[] encryptedBytes, int ivLength) {
        return EncodingUtils.subArray(encryptedBytes, ivLength, encryptedBytes.length);
    }
}
