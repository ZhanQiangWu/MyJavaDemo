package com.zhan.design_patterns.decorator.demo_monkeyking;

import com.zhan.design_patterns.decorator.demo_monkeyking.changesimpl.Bird;
import com.zhan.design_patterns.decorator.demo_monkeyking.changesimpl.Fish;

/**
 *  Created by zhan on 2016/12/26.
 */
public class Client {

    public static void main(String args[]){
        MonkeyKing mk = new ConCreteMonkeyKing();
        MonkeyKing fish = new Fish(mk);
        MonkeyKing bird = new Bird(fish);
        //fish.move();
        bird.move();
    }
}
