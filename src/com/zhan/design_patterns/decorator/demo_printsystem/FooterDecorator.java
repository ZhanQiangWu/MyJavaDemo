package com.zhan.design_patterns.decorator.demo_printsystem;

/**
 *  具体装饰类
 *  Created by zhan on 2016/12/26.
 */
public class FooterDecorator extends OrderDecorator{

    public FooterDecorator(Order order) {
        super(order);
    }

    public void print(){
        super.order.print();
        printFooter();
    }

    private void printFooter() {
        System.out.println("========================================================");
        System.out.println("Total\t\t\t\t" +
                formatCurrency(super.order.getGrandTotal()));
    }
}
