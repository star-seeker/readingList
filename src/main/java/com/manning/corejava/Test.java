package com.manning.corejava;

public class Test {

    public static void main(String[] args) {
        String[] s = {"a", "b"};
        Object[] o = s;
        Object obj = new Object();
        o[1] = obj;
    }
}
