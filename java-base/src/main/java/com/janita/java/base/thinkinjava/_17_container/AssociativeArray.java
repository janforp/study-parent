package com.janita.java.base.thinkinjava._17_container;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * 类说明：使用数组实现一个简单的 map 功能
 *
 * @author zhucj
 * @since 20200528
 */
public class AssociativeArray<K, V> {

    private Object[][] pairs;

    private int index;

    public AssociativeArray(int length) {
        //一个长度为 length 的数组，这个数组的每一个元素也是一个数组（包含2个元素）
        pairs = new Object[length][2];
    }

    public void put(K key, V value) {
        if (index >= pairs.length) {
            //其实可以扩容的
            throw new ArrayIndexOutOfBoundsException();
        }
        pairs[index++] = new Object[] { key, value };
    }

    @SuppressWarnings("unchecked")
    public V get(K key) {
        //循环数组查询 O(n)
        for (int i = 0; i < index; i++) {
            if (key.equals(pairs[i][0])) {
                return (V) pairs[i][1];
            }
        }
        return null; // Did not find key
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < index; i++) {
            result.append(pairs[i][0].toString());
            result.append(" : ");
            result.append(pairs[i][1].toString());
            if (i < index - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        AssociativeArray<String, String> map = new AssociativeArray<String, String>(6);
        map.put("sky", "blue");
        map.put("grass", "green");
        map.put("ocean", "dancing");
        map.put("tree", "tall");
        map.put("earth", "brown");
        map.put("sun", "warm");
        try {
            //超过数组的最大长度
            map.put("extra", "object"); // Past the end
        } catch (ArrayIndexOutOfBoundsException e) {
            print("Too many objects!");
        }
        print(map);
        print(map.get("ocean"));
    }
} /* Output:
Too many objects!
sky : blue
grass : green
ocean : dancing
tree : tall
earth : brown
sun : warm
dancing
*///:~
