package com.zhan.design_patterns.decorator.demo_printsystem;

import java.util.Date;

/**
 *  客户端
 *  Created by zhan on 2016/12/26.
 */
public class Client {

    private static Order order;

    public static void main(String[] args){
        order = new SalesOrder();
        order.setSalesDate(new Date());
        order.setCustomerName("XYZ Repair Shop");

        OrderLine line1 = new OrderLine();
        line1.setItemName("FireWheel Tire");
        line1.setUnitPrice(154.23);
        line1.setUnits(4);

        OrderLine line2 = new OrderLine();
        line2.setItemName("Front Fender");
        line2.setUnitPrice(300.45);
        line2.setUnits(1);

        order.addItem(line1);
        order.addItem(line2);

        order = new FooterDecorator(order);//FooterDecorator由于需要主体部分的数据，因而需要先在HeaderDecorator前修饰
        order = new HeaderDecorator(order);

        order.print();
    }
}
