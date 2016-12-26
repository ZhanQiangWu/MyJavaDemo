package com.zhan.design_patterns.decorator.demo_printsystem;

/**
 *  抽象装饰角色
 *  Created by zhan on 2016/12/26.
 */
public class OrderDecorator extends Order{

    protected Order order;

    public OrderDecorator(Order order){
        this.order = order;
        if (order==null) return;
        this.setSalesDate( order.getSalesDate() );
        this.setCustomerName( order.getCustomerName() );
    }

    public void print(){
        super.print();
    }

}
