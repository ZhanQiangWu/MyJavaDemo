package com.zhan.design_patterns.decorator.demo_printsystem;

/**
 *  具体装饰类
 *  Created by zhan on 2016/12/26.
 */
public class HeaderDecorator extends OrderDecorator{

    public HeaderDecorator(Order order) {
        super(order);
    }

    public void print(){
        this.printHeader();
        super.order.print();
    }

    private void printHeader() {
        System.out.println("\t***\tI N V O I C E\t***\nXYZ Incorporated\nDate of Sale: "
                + order.getSalesDate());
        System.out.println("========================================================");
        System.out.println("Item\t\tUnits\tUnit Price\tSubtotal");
    }
}
