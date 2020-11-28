/*
 * ���ڵ�����ʵ��ջ�ṹ
 */

package dsa;

public class Stack_List implements Stack {

    protected Node top;//ָ��ջ��Ԫ��

    protected int size;//ջ��Ԫ�ص���Ŀ

    //���췽������ջ��
    public Stack_List() {
        top = null;
        size = 0;
    }

    //��ѯ��ǰջ�Ĺ�ģ
    public int getSize() {
        return size;
    }

    //�ж��Ƿ�ջ��
    public boolean isEmpty() {
        return (top == null) ? true : false;
    }

    //ѹջ
    public void push(Object elem) {
        Node v = new Node(elem, top);//����һ���½ڵ㣬������Ϊ�׽ڵ����
        top = v;//�����׽ڵ�����
        size++;//���¹�ģ��¼
    }

    //��ȡ������ɾ����ջ��
    public Object top() throws ExceptionStackEmpty {
		if (isEmpty()) {
			throw new ExceptionStackEmpty("���⣺ջ��");
		}
        return top.getElem();
    }

    //����ջ��
    public Object pop() throws ExceptionStackEmpty {
		if (isEmpty()) {
			throw new ExceptionStackEmpty("���⣺ջ��");
		}
        Object temp = top.getElem();
        top = top.getNext();//�����׽ڵ�����
        size--;//���¹�ģ��¼
        return temp;
    }
}