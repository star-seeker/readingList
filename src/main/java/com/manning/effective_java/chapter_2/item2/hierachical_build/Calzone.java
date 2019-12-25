package com.manning.effective_java.chapter_2.item2.hierachical_build;

/**
 * @author zhangyoubao
 * @version 2019/11/21
 */
public class Calzone extends Pizza {

    private final boolean sauceInside;

    public static class Builder extends Pizza.Builder<Builder> {

        private boolean sauceInside = false; // default

        public Builder sauceInside() {
            sauceInside = true;
            return this;
        }

        @Override
        public Calzone build() {
            return new Calzone(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private Calzone(Builder builder) {
        super(builder);
        sauceInside = builder.sauceInside;
    }

    public static void main(String[] args) {
        Calzone calzone = new Builder().addTopping(Topping.HAM).sauceInside().build();
        System.out.println(calzone.toppings);
    }
}
