package com.manning.effective_java.chapter_2.item3;

/**
 * 使用私有构造方法或枚举实现Singleton属性
 *
 * @author zhangyoubao
 * @version 2019/11/22
 */
public class Elvis {

    /*
        两种常见单例
     */
    private Elvis() {
    }

    // 1. 公共静态final属性
    public static final Elvis INSTANCE = new Elvis();

    // 2. 公共静态工厂方法
    private static final Elvis INSTANCE2 = new Elvis();

    public static Elvis getInstance() {
        return INSTANCE2;
    }
}
