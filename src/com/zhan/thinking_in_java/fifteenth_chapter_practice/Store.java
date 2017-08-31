package com.zhan.thinking_in_java.fifteenth_chapter_practice;

import com.zhan.thinking_in_java.fifteenth_chapter_practice.util.Generator;
import com.zhan.thinking_in_java.fifteenth_chapter_practice.util.Generators;

import java.util.ArrayList;
import java.util.Random;

/**
 *  泛型构建复杂模型 P371
 *  Created by zhan on 2017/9/1.
 */
public class Store extends ArrayList<Aisle>{

    private ArrayList<CheckoutStand> checkouts =
            new ArrayList<CheckoutStand>();
    private Office office = new Office();

    /**
     * @param nAisles : 商店拥有的通道数量
     * @param nShelves ：一个通道拥有的货架数量
     * @param nProducts ： 一个货架拥有的商品数量
     */
    public Store(int nAisles, int nShelves, int nProducts) {
        for(int i = 0; i < nAisles; i++)
            add(new Aisle(nShelves, nProducts));
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Aisle a : this)
            for(Shelf s : a)
                for(Product p : s) {
                    result.append(p);
                    result.append("\n");
                }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Store(14, 5, 10));
    }
}

class Product {
    private final int id;
    private String description;
    private double price;

    public Product(int IDnumber, String descr, double price){
        id = IDnumber;
        description = descr;
        this.price = price;
        System.out.println(toString());
    }

    public String toString() {
        return id + ": " + description + ", price: $" + price;
    }

    public void priceChange(double change) {
        price += change;
    }

    public static Generator<Product> generator = new Generator<Product>() {
        private Random rand = new Random(47);
        @Override
        public Product next() {
            return  new Product(rand.nextInt(1000), "Test",
                    Math.round(rand.nextDouble() * 1000.0) + 0.99);
        }
    };

}


/**
 * 货架
 */
class Shelf extends ArrayList<Product> {
    public Shelf(int nProducts) {
        Generators.fill(this, Product.generator, nProducts);
    }
}

/**
 * 走廊
 */
class Aisle extends ArrayList<Shelf>{
    public Aisle(int nShelves, int nProducts) {
        for(int i = 0; i < nShelves; i++)
            add(new Shelf(nProducts));
    }
}

/**
 * 付款通道
 */
class CheckoutStand {}

/**
 * 办公室
 */
class Office {}

