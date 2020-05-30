package com.janita.java.base.thinkinjava._20_annitation.test;

import com.janita.java.base.thinkinjava._20_annitation.atunit.Test;
import com.janita.java.base.thinkinjava.util.OSExecute;

/**
 * AtUnitExample1
 *
 * @author zhucj
 * @since 20200528
 */
public class AtUnitExample1 {

    public String methodOne() {
        return "This is methodOne";
    }

    public int methodTwo() {
        System.out.println("This is methodTwo");
        return 2;
    }

    @Test
    boolean methodOneTest() {
        return methodOne().equals("This is methodOne");
    }

    @Test
    boolean m2() {
        return methodTwo() == 2;
    }

    @Test
    private boolean m3() {
        return true;
    }

    // Shows output for failure:
    @Test
    boolean failureTest() {
        return false;
    }

    @Test
    boolean anotherDisappointment() {
        return false;
    }

    public static void main(String[] args) {
        OSExecute.command(
                "java /Users/janita/code/studyCode/sp/java-base/src/main/java/com/janita/java/base/thinkinjava/_20_annitation/atunit/AtUnit.java"
        );
    }
} /* Output:
annotations.AtUnitExample1
  . methodOneTest
  . m2 This is methodTwo

  . m3
  . failureTest (failed)
  . anotherDisappointment (failed)
(5 tests)

>>> 2 FAILURES <<<
  annotations.AtUnitExample1: failureTest
  annotations.AtUnitExample1: anotherDisappointment
*///:~
