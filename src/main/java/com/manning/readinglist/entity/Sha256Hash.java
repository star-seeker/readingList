package com.manning.readinglist.entity;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

public class Sha256Hash {

    private String pwd;

    private String salt;

    public Sha256Hash(String pwd, String salt) {
        this.pwd = pwd;
        this.salt = salt;
    }

    @Override
    public String toString() {
        return DigestUtils.sha256Hex(pwd + salt);
    }

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String pwd = new Sha256Hash("123456", uuid).toString();
        System.out.println(pwd);

    }
}
