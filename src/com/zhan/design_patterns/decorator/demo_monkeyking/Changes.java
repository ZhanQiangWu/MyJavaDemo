package com.zhan.design_patterns.decorator.demo_monkeyking;

/**
 *  抽象装饰角色，大圣的七十二般变化（Decorator）
 *  Created by zhan on 2016/12/26.
 */
public class Changes implements MonkeyKing{
    private MonkeyKing monkeyKing;

    public Changes(){}

    public Changes(MonkeyKing monkeyKing){
        super();
        this.monkeyKing = monkeyKing;
    }

    @Override
    public void move() {
        monkeyKing.move();
    }
}
