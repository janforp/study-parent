/*
 * 迭代器ADT接口
 */

package dsa;

public interface Iterator<E> {

    boolean hasNext();//检查迭代器中是否还有剩余的元素

    E getNext();//返回迭代器中的下一元素
}