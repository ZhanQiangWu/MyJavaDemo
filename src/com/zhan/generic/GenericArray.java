package com.zhan.generic;

/**
 *  泛型数组 P384
 *  Created by zhan on 2017/9/27.
 */
public class GenericArray<T> {
    private T[] array;

    public GenericArray(int sz){
        array = (T[]) new Object[sz];
    }

    public void put(int index,T item){
        array[index] = item;
    }

    public T get(int index){
        return array[index];
    }

    public T[] rep(){
        return array;
    }

    public static void main(String[] args) {
        GenericArray<Integer> gai = new GenericArray<Integer>(10);
        // This causes a ClassCastException:
        //[Ljava.lang.Object; cannot be cast to [Ljava.lang.Integer;
        //! Integer[] ia = gai.rep();

        //this is ok
        Object[] oa = gai.rep();
    }
}
