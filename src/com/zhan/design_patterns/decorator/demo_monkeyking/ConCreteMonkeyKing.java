package com.zhan.design_patterns.decorator.demo_monkeyking;

/**
 *  大圣本尊类( ConcreteComponent 实体大圣类)
 *  Created by zhan on 2016/12/26.
 */
public class ConCreteMonkeyKing implements MonkeyKing{

    public ConCreteMonkeyKing(){

    }

    @Override
    public void move() {
        System.out.println("我是齐天大圣本尊");
    }
}
