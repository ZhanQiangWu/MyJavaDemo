package com.zhan.design_patterns.decorator.demo_monkeyking.changesimpl;

import com.zhan.design_patterns.decorator.demo_monkeyking.Changes;
import com.zhan.design_patterns.decorator.demo_monkeyking.MonkeyKing;

/**
 *  实变化雀类（ ConCreteDecorator 大圣变化之一）
 *  Created by zhan on 2016/12/26.
 */
public class Bird extends Changes{

    public Bird(MonkeyKing fish) {
        super(fish);
    }


    @Override
    public void move() {
        super.move();
        System.out.println("我是七十二变化之 Bird");
    }

}
