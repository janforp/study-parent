/*
 * ������������ʵ��Stack�ӿ�
 */

package dsa;

public class Stack_Array implements Stack {

    public static final int CAPACITY = 1024;//�����Ĭ������

    protected int capacity;//�����ʵ������

    protected Object[] S;//��������

    protected int top = -1;//ջ��Ԫ�ص�λ��

    //��Ĭ����������ջ����
    public Stack_Array() {
        this(CAPACITY);
    }

    //��ָ����������ջ����
    public Stack_Array(int cap) {
        capacity = cap;
        S = new Object[capacity];
    }

    //��ȡջ��ǰ�Ĺ�ģ
    public int getSize() {
        return (top + 1);
    }

    //����ջ�Ƿ�Ϊ��
    public boolean isEmpty() {
        return (top < 0);
    }

    //��ջ
    public void push(Object obj) throws ExceptionStackFull {
		if (getSize() == capacity) {
			throw new ExceptionStackFull("���⣺ջ���");
		}
        S[++top] = obj;
    }

    //ȡջ��Ԫ��
    public Object top() throws ExceptionStackEmpty {
		if (isEmpty()) {
			throw new ExceptionStackEmpty("���⣺ջ��");
		}
        return S[top];
    }

    //��ջ
    public Object pop() throws ExceptionStackEmpty {
        Object elem;
		if (isEmpty()) {
			throw new ExceptionStackEmpty("���⣺ջ��");
		}
        elem = S[top];
        S[top--] = null;
        return elem;
    }
}