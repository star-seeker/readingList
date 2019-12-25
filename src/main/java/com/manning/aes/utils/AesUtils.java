package com.manning.aes.utils;

import com.manning.aes.encryptor.Encryptors;

/**
 * @author zhangyoubao
 * @version 2019/12/25
 */
public class AesUtils {

    private static String password = "97090f197326fcbb";

    private static String salt = "870f8bb2ec0b2ffd";

    public static String aesEcb128EncWithPadding(String data) {
        return Encryptors.textNoIv(password, salt, Encryptors.AES_ECB_PKCS5PADDING, 128).encrypt(data);
    }

    public static String aesEcb256EncWithPadding(String data) {
        return Encryptors.textNoIv(password, salt, Encryptors.AES_ECB_PKCS5PADDING, 256).encrypt(data);
    }

    public static String aesEcb128EncNoPadding(String data) {
        return Encryptors.textNoIv(password, salt, Encryptors.AES_ECB_NOPADDING, 128).encrypt(data);
    }

    public static String aesEcb256EncNoPadding(String data) {
        return Encryptors.textNoIv(password, salt, Encryptors.AES_ECB_NOPADDING, 256).encrypt(data);
    }

    public static String aesEcb128DecWithPadding(String encryptedData) {
        return Encryptors.textNoIv(password, salt, Encryptors.AES_ECB_PKCS5PADDING, 128).decrypt(encryptedData);
    }

    public static String aesEcb256DecWithPadding(String encryptedData) {
        return Encryptors.textNoIv(password, salt, Encryptors.AES_ECB_PKCS5PADDING, 256).decrypt(encryptedData);
    }

    public static String aesEcb128DecNoPadding(String encryptedData) {
        return Encryptors.textNoIv(password, salt, Encryptors.AES_ECB_NOPADDING, 128).decrypt(encryptedData);
    }

    public static String aesEcb256DecNoPadding(String encryptedData) {
        return Encryptors.textNoIv(password, salt, Encryptors.AES_ECB_NOPADDING, 256).decrypt(encryptedData);
    }

    public static String aesCbc128EncWithPadding(String data) {
        return Encryptors.textWithIv(password, salt, Encryptors.AES_CBC_PKCS5PADDING, 128).encrypt(data);
    }

    public static String aesCbc256EncWithPadding(String data) {
        return Encryptors.textWithIv(password, salt, Encryptors.AES_CBC_PKCS5PADDING, 256).encrypt(data);
    }

    public static String aesCbc128EncNoPadding(String data) {
        return Encryptors.textWithIv(password, salt, Encryptors.AES_CBC_NOPADDING, 128).encrypt(data);
    }

    public static String aesCbc256EncNoPadding(String data) {
        return Encryptors.textWithIv(password, salt, Encryptors.AES_CBC_NOPADDING, 256).encrypt(data);
    }

    public static String aesCbc128DecWithPadding(String encryptedData) {
        return Encryptors.textWithIv(password, salt, Encryptors.AES_CBC_PKCS5PADDING, 128).decrypt(encryptedData);
    }

    public static String aesCbc256DecWithPadding(String encryptedData) {
        return Encryptors.textWithIv(password, salt, Encryptors.AES_CBC_PKCS5PADDING, 256).decrypt(encryptedData);
    }

    public static String aesCbc128DecNoPadding(String encryptedData) {
        return Encryptors.textWithIv(password, salt, Encryptors.AES_CBC_NOPADDING, 128).decrypt(encryptedData);
    }

    public static String aesCbc256DecNoPadding(String encryptedData) {
        return Encryptors.textWithIv(password, salt, Encryptors.AES_CBC_NOPADDING, 256).decrypt(encryptedData);
    }

}
