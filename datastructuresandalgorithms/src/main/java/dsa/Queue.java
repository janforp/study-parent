/*
 * ���нӿ�
 */

package dsa;

public interface Queue {

    public int getSize();//���ض�����Ԫ����Ŀ

    public boolean isEmpty();//�ж϶����Ƿ�Ϊ��

    public Object front()//ȡ����Ԫ�أ�����ɾ����
            throws ExceptionQueueEmpty;

    public void enqueue(Object obj)
            throws ExceptionQueueFull;//���

    public Object dequeue()//����
            throws ExceptionQueueEmpty;

    public void Traversal();//����
}