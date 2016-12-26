package com.zhan.design_patterns.decorator.demo_printsystem;

import java.text.NumberFormat;

/**
 *  销售单中的货物清单的一行，它包括如下信息：
 *  产品名、数量、单价、小计金额
 *  Created by zhan on 2016/12/26.
 */
public class OrderLine {

    private String itemName;
    private int units;
    private double unitPrice;

    /**
     * 产品名字的取值方法
     */
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 单位数量的取值方法
     */
    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    /**
     * 单价的取值方法
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void printLine(){
        System.out.println(itemName + "\t" + units
                + "\t" + formatCurrency(unitPrice)
                + "\t" + formatCurrency(getSubtotal()));
    }

    /**
     * 小计金额的取值方法
     */
    public double getSubtotal(){
        return unitPrice * units;
    }

    /**
     * 工具方法：将金额格式化
     */
    private String formatCurrency(double amnt) {
        return NumberFormat.getCurrencyInstance().format(amnt);
    }
}
