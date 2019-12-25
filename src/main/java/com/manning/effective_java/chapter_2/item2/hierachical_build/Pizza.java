package com.manning.effective_java.chapter_2.item2.hierachical_build;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author zhangyoubao
 * @version 2019/11/21
 */

/*
 * Build模式非常适合类层次结构，使用平行层次的builder，每个builder嵌套在相应的类中
 * 抽象类有抽象类的builder，具体的类有具体的builder
 */

// Builder pattern for class hierarchies
public abstract class Pizza {

    public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}

    final Set<Topping> toppings;

    // 递归类型参数，模拟自我类型
    abstract static class Builder<T extends Builder<T>> {

        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }
}
