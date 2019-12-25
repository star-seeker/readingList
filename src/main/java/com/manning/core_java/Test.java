package com.manning.core_java;

public class Test {

    public static void main(String[] args) {
        String[] s = {"a", "b"};
        Object[] o = s;
        Object obj = new Object();
        o[1] = obj;
    }
}
