package com.manning.aes.test;

import com.manning.aes.utils.AesUtils;

/**
 * @author zhangyoubao
 * @version 2019/12/25
 */
public class Test {

    public static void main(String[] args) {

        String data = "74e4fece55844254b96968dc95840af3";

        String encrypt1 = AesUtils.aesEcb128EncWithPadding(data);
        System.out.println("encrypt1: " + encrypt1);

        String decrypt1 = AesUtils.aesEcb128DecWithPadding(encrypt1);
        System.out.println("decrypt1: " + decrypt1);

        String encrypt2 = AesUtils.aesEcb128EncNoPadding(data);
        System.out.println("encrypt2: " + encrypt2);

        String decrypt2 = AesUtils.aesEcb128DecNoPadding(encrypt2);
        System.out.println("decrypt2: " + decrypt2);

        String encrypt3 = AesUtils.aesEcb256EncWithPadding(data);
        System.out.println("encrypt3: " + encrypt3);

        String decrypt3 = AesUtils.aesEcb256DecWithPadding(encrypt3);
        System.out.println("decrypt3: " + decrypt3);

        String encrypt4 = AesUtils.aesEcb256EncNoPadding(data);
        System.out.println("encrypt4: " + encrypt4);

        String decrypt4 = AesUtils.aesEcb256DecNoPadding(encrypt4);
        System.out.println("decrypt4: " + decrypt4);

        String encrypt5 = AesUtils.aesCbc128EncWithPadding(data);
        System.out.println("encrypt5: " + encrypt5);

        String decrypt5 = AesUtils.aesCbc128DecWithPadding(encrypt5);
        System.out.println("decrypt5: " + decrypt5);

        String encrypt6 = AesUtils.aesCbc128EncNoPadding(data);
        System.out.println("encrypt6: " + encrypt6);

        String decrypt6 = AesUtils.aesCbc128DecNoPadding(encrypt6);
        System.out.println("decrypt6: " + decrypt6);

        String encrypt7 = AesUtils.aesCbc256EncWithPadding(data);
        System.out.println("encrypt7: " + encrypt7);

        String decrypt7 = AesUtils.aesCbc256DecWithPadding(encrypt7);
        System.out.println("decrypt7: " + decrypt7);

        String encrypt8 = AesUtils.aesCbc256EncNoPadding(data);
        System.out.println("encrypt8: " + encrypt8);

        String decrypt8 = AesUtils.aesCbc256DecNoPadding(encrypt8);
        System.out.println("decrypt8: " + decrypt8);
    }
}
