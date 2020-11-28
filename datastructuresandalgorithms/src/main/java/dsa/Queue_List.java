/*
 * ���ڵ�����ʵ�ֶ��нṹ
 */

package dsa;

public class Queue_List implements Queue {

    protected Node head;//ָ�����Ԫ��

    protected Node tail;//ָ���ĩԪ��

    protected int size;//������Ԫ�ص���Ŀ

    //���췽�����ն��У�
    public Queue_List() {
        head = tail = null;
        size = 0;
    }

    //��ѯ��ǰ���еĹ�ģ
    public int getSize() {
        return size;
    }

    //�ж϶����Ƿ�Ϊ��
    public boolean isEmpty() {
        return (0 == size) ? true : false;
    }

    //���
    public void enqueue(Object obj) {
        Node node = new Node();
        node.setElem(obj);
        node.setNext(null);//�½ڵ���Ϊĩ�ڵ����
		if (0 == size) {
			head = node;//����ǰ����Ϊ�գ���ֱ�Ӳ���
		} else {
			tail.setNext(node);//���򣬽��½ڵ��������ĩ��
		}
        tail = node;//����ָ��ĩ�ڵ�����
        size++;//���¹�ģ
    }

    //����
    public Object dequeue() throws ExceptionQueueEmpty {
		if (0 == size) {
			throw new ExceptionQueueEmpty("���⣺���п�");
		}
        Object obj = head.getElem();
        head = head.getNext();
        size--;
		if (0 == size) {
			tail = null;//�������ѿգ��뽫ĩ�ڵ������ÿ�
		}
        return obj;
    }

    //ȡ������ɾ��������Ԫ��
    public Object front() throws ExceptionQueueEmpty {
		if (isEmpty()) {
			throw new ExceptionQueueEmpty("���⣺���п�");
		}
        return head.getElem();
    }

    //������������ADT��
    public void Traversal() {
        Node p = head;
        while (null != p) {
            System.out.print(p.getElem() + " ");
            p = p.getNext();
        }
    }
}