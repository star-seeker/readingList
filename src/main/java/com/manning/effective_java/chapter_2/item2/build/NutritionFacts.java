package com.manning.effective_java.chapter_2.item2.build;

/**
 * 当构造方法参数过多时使用 builder 模式
 *
 * @author zhangyoubao
 * @version 2019/11/21
 */
public class NutritionFacts {

    /*
     * 应用场景：很多可选参数的场景
     * 结合可伸缩构造方法模式的安全性和JavaBean模式的可读性
     * 客户端不直接构造所需的对象，而是调用一个包含所有必需参数的构造方法（或静态工厂）得到一个builder对象
     * 然后，客户端调用builder对象的与setter相似方法来设置可选参数
     * 最后，客户端调用builder对象的一个无参的build方法来生成对象，该对象通常是不可变的
     * Builder通常是它所构建的类的一个静态成员类
     */

    // Builder pattern
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {

        // Required parameters
        private final int servingSize;
        private final int servings;

        // Optional parameters - initialized to default values
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        public Builder fat(int val) {
            fat = val;
            return this;
        }

        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    public static void main(String[] args) {
        NutritionFacts cocaCola = new Builder(240, 8).calories(100).sodium(35).carbohydrate(27).build();
        System.out.println(cocaCola.toString());
    }
}
