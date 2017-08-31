package com.zhan.thinking_in_java.fifteenth_chapter_practice.util;

import java.util.Collection;

/**
 *  Created by zhan on 2017/9/1.
 */
public class Generators {

    public static <T> Collection<T> fill(Collection<T> coll,Generator<T> gen,int n){
        for (int i =0;i < n;i++){
            coll.add(gen.next());
        }
        return coll;
    }
}
