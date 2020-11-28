/*
 * ������ڵ���
 */

package dsa;

public class Node implements Position {

    private Object element;//���ݶ���

    private Node next;//ָ���̽ڵ�

    /**************************** ���캯�� ****************************/
    public Node() {
        this(null, null);
    }//ָ�����ݶ��󡢺�̽ڵ�����ö��ÿ�

    public Node(Object e, Node n) {
        element = e;
        next = n;
    }//ָ�����ݶ��󼰺�̽ڵ�

    /**************************** Position�ӿڷ��� ****************************/
    //���ش���ڸ�λ�õ�Ԫ��
    public Object getElem() {
        return element;
    }

    //������Ԫ�ش������λ�ã����ش�ǰ��ŵ�Ԫ��
    public Object setElem(Object e) {
        Object oldElem = element;
        element = e;
        return oldElem;
    }

    /**************************** ������ڵ㷽�� ****************************/
    //ȡ��ǰ�ڵ�ĺ�̽ڵ�
    public Node getNext() {
        return next;
    }

    //�޸ĵ�ǰ�ڵ�ĺ�̽ڵ�
    public void setNext(Node newNext) {
        next = newNext;
    }
}