package com.zhan.design_patterns.decorator;

/**
 *  装饰者设计模式 - 装饰类
 *  Created by zhan on 2016/12/26.
 */
public class Decorator implements Component{

    private Component component;

    /**
     * 构造子函数
     */
    public Decorator(){
        //write your code here
    }

    /**
     * 构造子函数
     * @param component
     */
    public Decorator(Component component){
        this.component = component;
    }


    /**
     * 商业方法，委派给构件
     */
    @Override
    public void sampleOperation() {
        component.sampleOperation();
    }
}
