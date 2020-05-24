package com.janita.java.base.thinkinjava._17_container.test;

import java.util.List;

/**
 * 类说明：Tester
 *
 * @author zhucj
 * @since 20200528
 */
public class Tester<C> {

    public static int fieldWidth = 8;

    public static TestParam[] defaultParams = TestParam.array(
            10, 5000,
            100, 5000,
            1000, 5000,
            10000, 500);

    // Override this to modify pre-test initialization:
    protected C initialize(int size) {
        return container;
    }

    protected C container;

    //headline = container.getClass().getSimpleName();
    private String headline = "";

    private List<Test<C>> tests;

    private static String stringField() {
        //%8s
        return "%" + fieldWidth + "s";
    }

    private static String numberField() {
        //%8d
        return "%" + fieldWidth + "d";
    }

    private static int sizeWidth = 5;

    //%5s
    private static String sizeField = "%" + sizeWidth + "s";

    //构造器中没给，就使用默认的
    private TestParam[] paramList = defaultParams;

    public Tester(C container, List<Test<C>> tests) {
        this.container = container;
        this.tests = tests;
        if (container != null) {
            headline = container.getClass().getSimpleName();
        }
    }

    public Tester(C container, List<Test<C>> tests, TestParam[] paramList) {
        this(container, tests);
        this.paramList = paramList;
    }

    public void setHeadline(String newHeadline) {
        headline = newHeadline;
    }

    // Generic methods for convenience :
    public static <C> void run(C cntnr, List<Test<C>> tests) {
        Tester<C> tester = new Tester<>(cntnr, tests);
        tester.timedTest();
    }

    public static <C> void run(C cntnr, List<Test<C>> tests, TestParam[] paramList) {
        Tester<C> tester = new Tester<>(cntnr, tests, paramList);
        tester.timedTest();
    }

    private void displayHeader() {
        // Calculate width and pad with '-':
        int width = fieldWidth * tests.size() + sizeWidth;
        int dashLength = width - headline.length() - 1;
        StringBuilder head = new StringBuilder(width);

        for (int i = 0; i < dashLength / 2; i++) {
            head.append('-');
        }

        head.append(' ');
        head.append(headline);
        head.append(' ');

        for (int i = 0; i < dashLength / 2; i++) {
            head.append('-');
        }
        System.out.println(head);
        // Print column headers:

        System.out.format(sizeField, "size");
        for (Test<C> test : tests) {
            //%8s
            String stringField = stringField();
            System.out.format(stringField, test.name);
        }
        System.out.println();
    }

    // Run the tests for this container:
    public void timedTest() {
        displayHeader();

        for (TestParam param : paramList) {
            System.out.format(sizeField, param.size);
            for (Test<C> test : tests) {

                C kontainer = initialize(param.size);

                long start = System.nanoTime();

                // Call the override method:
                int reps = test.test(kontainer, param);

                long duration = System.nanoTime() - start;
                long timePerRep = duration / reps; // Nanoseconds
                System.out.format(numberField(), timePerRep);
            }
            System.out.println();
        }
    }
}