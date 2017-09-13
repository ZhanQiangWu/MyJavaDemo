package com.zhan.thinking_in_java.threaddemo;

/**
 *  学习 Continue 和 break 在携带标签时的用法
 *  参考资料：http://blog.csdn.net/sunkun2013/article/details/11958543
 *  Created by zhan on 2017/9/13.
 */
public class ContinueAndBreakTest {

    public static void main(String[] args){

        ContinueAndBreakTest test = new ContinueAndBreakTest();
        test.testContinue1();
        test.testBreak1();
        test.testContinue2();
        test.testBreak2();
    }

    /**
     * 测试continue
     * continue用来结束本次循环
     */
    public void testContinue1() {
        System.out.println("--------测试不带标签的continue-------");
        for (int i = 1; i <= 5; i++) {
            if (i == 3)
                continue;
            System.out.println("i = " + i);
        }
    }

    /**
     * break用来结束整个循环体
     */
    public void testBreak1() {
        System.out.println("--------测试不带标签break-------");
        for (int i = 1; i <= 5; i++) {
            if (i == 3)
                break;
            System.out.println("i = " + i);
        }
    }

    /**
     * 测试带标签的continue和break语句
     * 标签只能写在循环体之前，顺便学习一下java中语句标签的定义和使用
     * continue 带标签表示跳出本次循环到标识处，并开始下一次的循环
     * 经过测试觉得：标签代表临近的循环的别名；如下面的lable1代表第一层for循环。lable2代表第二层for循环
     * break 或 continue 哪一个标签即表示break 或 continue 该层for循环
     */
    public void testContinue2() {
        System.out.println("--------测试带标签的continue-------");
        lable1:
        for (int i = 1; i < 10; i++) {
            lable2:
            for (int j = 0; j < 3; j++) {
                if (j == 1)
                    continue lable2;
                System.out.println("i = " + i + "" + j);
            }
        }
    }

    public void testBreak2() {
        System.out.println("--------测试带标签的break-------");
        int i = 1;
        int k = 4;
        lable1:
        for (; i <= 5; i++, k--) {
            if (k == 2)
                break lable1;
            System.out.println("i = " + i + " ; k = " + k);
        }
    }
}
