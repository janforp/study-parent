package com.janita.java.base.concurrent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 类说明：User
 *
 * @author zhucj
 * @since 20200423
 */
@AllArgsConstructor
public class User implements Cloneable {

    @Getter
    private String name;

    @Setter
    private int age;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        //if(this.name==user.name && this.age==user.age)
        if (this.name.equals(user.name) && this.age == user.age) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode() + age;
    }

    @Override
    public String toString() {
        return "{name:'" + name + "',age:" + age + "}";
    }

    @Override
    public Object clone() {
        Object object = null;
        try {
            object = super.clone();
        } catch (CloneNotSupportedException e) {
            //ignore
        }
        return object;
    }
}

class CollectionModifyExceptionTest {

    public static void main(String[] args) {

        Collection<User> users = new ArrayList<>();

        //new ArrayList();
        users.add(new User("张三",28));
        users.add(new User("李四",25));
        users.add(new User("王五",31));

        Iterator<User> itrUsers = users.iterator();

        while(itrUsers.hasNext()){
            System.out.println("AAA");
            User user = itrUsers.next();
            //删除第一个跟最后一个报错，但是删除中间的不报错
            if("王五".equals(user.getName())){
                users.remove(user);
                // itrUsers.remove();
            } else {
                System.out.println(user);
            }
        }
    }
}
