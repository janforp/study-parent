package com.janita.design.mode.prototype;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：ProtoTypeMode
 *
 * 原型模式是创建型模式的一种，其特点在于通过“复制”一个已经存在的实例来返回新的实例,而不是新建实例。
 * 被复制的实例就是我们所称的“原型”，这个原型是可定制的。
 *
 * 原型模式多用于创建复杂的或者耗时的实例，因为这种情况下，复制一个已经存在的实例使程序运行更高效；或者创建值相等，只是命名不一样的同类数据。
 *
 * @author zhucj
 * @since 20200423
 */
public class ProtoTypeMode {

    public static void main(String[] args) throws CloneNotSupportedException {

        List<Cookie> cookieList = new ArrayList<>(100000 * 1000);
        System.out.println("开始：" + LocalDateTime.now());
        for (int i = 0; i < 10000 * 1000; i++) {
            PizzaCookie cookie = new PizzaCookie();
            cookie.setName("名称" + i);
            cookie.setPrice(i);
            cookieList.add(cookie);
        }
        System.out.println("结束：" + LocalDateTime.now());

        List<Cookie> cookie2List = new ArrayList<>(100000 * 1000);
        System.out.println("开始2：" + LocalDateTime.now());
        PizzaCookie cookie = new PizzaCookie();
        for (int i = 0; i < 10000 * 1000; i++) {
            PizzaCookie clone = (PizzaCookie) cookie.clone();
            clone.setName("名称" + i);
            clone.setPrice(i);
            cookie2List.add(clone);
        }
        System.out.println("结束2：" + LocalDateTime.now());
        cookieList = null;
        cookie2List = null;
    }
}

/**
 * protoType
 */
class Cookie implements Cloneable {

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

@Data
@EqualsAndHashCode(callSuper = false)
class PizzaCookie extends Cookie {

    private String name;

    private int price;
}

