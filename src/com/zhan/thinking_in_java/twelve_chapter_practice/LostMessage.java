package com.zhan.thinking_in_java.twelve_chapter_practice;//: exceptions/LostMessage.java

/**
 * How an exception can be lost. P477 缺憾：异常丢失
 */

class VeryImportantException extends Exception {
  public String toString() {
    return "A very important exception!";
  }
}

class HoHumException extends Exception {
  public String toString() {
    return "A trivial exception";
  }
}

public class LostMessage {
  void f() throws VeryImportantException {
    throw new VeryImportantException();
  }
  void dispose() throws HoHumException {
    throw new HoHumException();
  }
  public static void main(String[] args) {
    LostMessage lm = new LostMessage();

    try {
        lm.f();

    } catch(Exception e) {
      System.out.println(e);
    }finally {
      try {
        lm.dispose();
      } catch (HoHumException e) {
        e.printStackTrace();
      }
    }
  }
} /* Output:
A trivial exception
*///:~
