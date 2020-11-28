/*
 * ����λ�ýӿ�ʵ�ֵ�˫������ڵ���
 */

package dsa;

public class DLNode implements Position {

    private Object element;//���ݶ���

    private DLNode prev;//ָ��ǰ���ڵ�

    private DLNode next;//ָ���̽ڵ�

    /**************************** ���캯�� ****************************/
    public DLNode() {
        this(null, null, null);
    }

    public DLNode(Object e, DLNode p, DLNode n) {
        element = e;
        prev = p;
        next = n;
    }
    //ע�����������Ĵ������ݶ���ǰ���ڵ㡢��̽ڵ�

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

    /**************************** ˫������ڵ㷽�� ****************************/
    //�ҵ����λ��
    public DLNode getNext() {
        return next;
    }

    //�ҵ�ǰ��λ��
    public DLNode getPrev() {
        return prev;
    }

    //�޸ĺ��λ��
    public void setNext(DLNode newNext) {
        next = newNext;
    }

    //�޸�ǰ��λ��
    public void setPrev(DLNode newPrev) {
        prev = newPrev;
    }
}