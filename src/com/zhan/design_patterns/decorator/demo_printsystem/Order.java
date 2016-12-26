package com.zhan.design_patterns.decorator.demo_printsystem;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Vector;

/**
 *  发票打印系统 - 抽象角色
 *  Created by zhan on 2016/12/26.
 *  注：本demo 摘自《Java与模式》装饰者模式一节的示例，作者：阎宏博士
 */
abstract public class Order {

    private OrderLine lnkOrderLine;
    protected String customerName;
    protected Date salesDate;
    protected Vector items = new Vector(10);

    public void print(){
        for (int i = 0 ; i < items.size() ; i++){
            OrderLine item = (OrderLine) items.get(i);
            item.printLine();
        }
    }

    /**
     * 获取客户名
     */
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * 获得销售日期
     */
    public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }

    /**
     * 增加一行销售产品
     */
    public void addItem(OrderLine item){
        items.add(item);
    }

    public void removeItem(OrderLine item){
        items.remove(item);
    }

    /**
     * 获得总额
     */
    public double getGrandTotal(){
        double amnt = 0.0D;
        for (int i = 0 ; i < items.size() ; i++){
            OrderLine item = (OrderLine) items.get(i);
            amnt += item.getSubtotal();
        }
        return amnt;
    }

    /**
     * 工具方法：将金额格式化
     */
    protected String formatCurrency(double amnt){
        return NumberFormat.getCurrencyInstance().format(amnt);
    }
}
