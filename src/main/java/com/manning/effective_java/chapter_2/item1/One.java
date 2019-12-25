package com.manning.effective_java.chapter_2.item1;

/**
 * 考虑使用静态工厂方法替代构造方法
 *
 * @author zhangyoubao
 * @version 2019/11/21
 */
public class One {

    /*
     *  获取类实例的方法：
     *  1. 传统方法：提供一个公共构造参数
     *  2. 提供一个公共静态工厂方法，只返回类实例
     *
     *  优点:
     *  1. 静态工厂方法有名字，更易于使用，生成的客户端代码更易于阅读
     *  2. 不需要每次调用时都创建一个新对象 —— 应用于不可变类，请求等价对象
     *  3. 可以返回其返回类型的子类型的对象
     *  4. 返回对象的类可以根据输入参数的不同而不同
     *  5. 在编写包含该方法的类时，返回的对象的类不需要存在（？）
     *
     *  缺点：
     *  1. 没有公共或受保护构造方法的类不能被子类化 —— 鼓励使用组合而不是继承
     *  2. 程序员很难找到它们
     */
}
