package com.janita.java.base.thinkinjava._15_genericity;

import com.janita.java.base.thinkinjava.util.Print;

import java.lang.reflect.*;
import java.util.*;

/**
 * 类说明：Apply
 *
 * @author zhucj
 * @since 20200528
 */
public class Apply {

    public static <T, S extends Iterable<? extends T>> void apply(S seq, Method f, Object... args) {
        try {
            //Iterator<? extends T> iterator = seq.iterator();
            for (T t : seq) {
                f.invoke(t, args);
            }
        } catch (Exception e) {
            // Failures are programmer errors
            throw new RuntimeException(e);
        }
    }
}

class Shape {

    public void rotate() {
        Print.print(this + " rotate");
    }

    public void resize(int newSize) {
        Print.print(this + " resize " + newSize);
    }
}

class Square extends Shape {

}

class FilledList<T> extends ArrayList<T> {

    public FilledList(Class<? extends T> type, int size) {
        try {
            for (int i = 0; i < size; i++) {
                // Assumes default constructor:
                add(type.newInstance());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class ApplyTest {

    public static void main(String[] args) throws Exception {
        List<Shape> shapes = new ArrayList<Shape>();
        for (int i = 0; i < 10; i++) {
            shapes.add(new Shape());
        }

        Method rotateMethod = Shape.class.getMethod("rotate");
        Apply.apply(shapes, rotateMethod);

        Method resizeMethodOfShape = Shape.class.getMethod("resize", int.class);
        Apply.apply(shapes, resizeMethodOfShape, 5);

        System.out.println("===============================");

        List<Square> squares = new ArrayList<Square>();
        for (int i = 0; i < 10; i++) {
            squares.add(new Square());
        }
        Apply.apply(squares, Shape.class.getMethod("rotate"));
        Apply.apply(squares, Shape.class.getMethod("resize", int.class), 5);

        Apply.apply(new FilledList<Shape>(Shape.class, 10), Shape.class.getMethod("rotate"));
        Apply.apply(new FilledList<Shape>(Square.class, 10), Shape.class.getMethod("rotate"));

        SimpleQueue<Shape> shapeQ = new SimpleQueue<Shape>();
        for (int i = 0; i < 5; i++) {
            shapeQ.add(new Shape());
            shapeQ.add(new Square());
        }
        Apply.apply(shapeQ, Shape.class.getMethod("rotate"));
    }
}

