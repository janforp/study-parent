/*
 * ջ�ӿ�
 */

package dsa;

public interface Stack {

    public int getSize();//����ջ��Ԫ����Ŀ

    public boolean isEmpty();//�ж�ջ�Ƿ�Ϊ��

    public Object top() throws ExceptionStackEmpty;//ȡջ��Ԫ�أ�����ɾ����

    public void push(Object ele);//��ջ

    public Object pop() throws ExceptionStackEmpty;//��ջ
}