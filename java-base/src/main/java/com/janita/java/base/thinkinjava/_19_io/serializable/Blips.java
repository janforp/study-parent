package com.janita.java.base.thinkinjava._19_io.serializable;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * Blips
 *
 * @author zhucj
 * @since 20200528
 */
class Blip1 implements Externalizable {

    public Blip1() {
        print("Blip1 Constructor");
    }

    @Override
    public void writeExternal(ObjectOutput out) {
        print("Blip1.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) {
        print("Blip1.readExternal");
    }
}

class Blip2 implements Externalizable {

    /**
     * 区别在此
     */
    Blip2() {
        print("Blip2 Constructor");
    }

    @Override
    public void writeExternal(ObjectOutput out) {
        print("Blip2.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) {
        print("Blip2.readExternal");
    }
}

public class Blips {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        print("Constructing objects:");
        Blip1 b1 = new Blip1();
        Blip2 b2 = new Blip2();
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Blips.out"));
        print("Saving objects:");
        o.writeObject(b1);
        o.writeObject(b2);
        o.close();
        // Now get them back:
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Blips.out"));
        print("Recovering b1:");
        //会调用构造器
        b1 = (Blip1) in.readObject();
        // OOPS! Throws an exception:
        print("Recovering b2:");
        b2 = (Blip2) in.readObject();
    }
} /* Output:
Constructing objects:
Blip1 Constructor
Blip2 Constructor
Saving objects:
Blip1.writeExternal
Blip2.writeExternal
Recovering b1:
Blip1 Constructor
Blip1.readExternal
*///:~
