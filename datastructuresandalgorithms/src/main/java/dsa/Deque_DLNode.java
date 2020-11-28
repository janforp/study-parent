/*
 * ����˫������ʵ��˫�˶��нṹ
 */

package dsa;

public class Deque_DLNode implements Deque {

    protected DLNode header;//ָ��ͷ�ڵ㣨�ڱ���

    protected DLNode trailer;//ָ��β�ڵ㣨�ڱ���

    protected int size;//������Ԫ�ص���Ŀ

    //���캯��
    public Deque_DLNode() {
        header = new DLNode();
        trailer = new DLNode();
        header.setNext(trailer);
        trailer.setPrev(header);
        size = 0;
    }

    //���ض�����Ԫ����Ŀ
    public int getSize() {
        return size;
    }

    //�ж϶����Ƿ�Ϊ��
    public boolean isEmpty() {
        return (0 == size) ? true : false;
    }

    //ȡ��Ԫ�أ�����ɾ����
    public Object first() throws ExceptionQueueEmpty {
		if (isEmpty()) {
			throw new ExceptionQueueEmpty("���⣺˫�˶���Ϊ��");
		}
        return header.getNext().getElem();
    }

    //ȡĩԪ�أ�����ɾ����
    public Object last() throws ExceptionQueueEmpty {
		if (isEmpty()) {
			throw new ExceptionQueueEmpty("���⣺˫�˶���Ϊ��");
		}
        return trailer.getPrev().getElem();
    }

    //�ڶ���ǰ�˲����½ڵ�
    public void insertFirst(Object obj) {
        DLNode second = header.getNext();
        DLNode first = new DLNode(obj, header, second);
        second.setPrev(first);
        header.setNext(first);
        size++;
    }

    //�ڶ��к�˲����½ڵ�
    public void insertLast(Object obj) {
        DLNode second = trailer.getPrev();
        DLNode first = new DLNode(obj, second, trailer);
        second.setNext(first);
        trailer.setPrev(first);
        size++;
    }

    //ɾ���׽ڵ�
    public Object removeFirst() throws ExceptionQueueEmpty {
		if (isEmpty()) {
			throw new ExceptionQueueEmpty("���⣺˫�˶���Ϊ��");
		}
        DLNode first = header.getNext();
        DLNode second = first.getNext();
        Object obj = first.getElem();
        header.setNext(second);
        second.setPrev(header);
        size--;
        return (obj);
    }

    //ɾ��ĩ�ڵ�
    public Object removeLast() throws ExceptionQueueEmpty {
		if (isEmpty()) {
			throw new ExceptionQueueEmpty("���⣺˫�˶���Ϊ��");
		}
        DLNode first = trailer.getPrev();
        DLNode second = first.getPrev();
        Object obj = first.getElem();
        trailer.setPrev(second);
        second.setNext(trailer);
        size--;
        return (obj);
    }

    //����
    public void Traversal() {
        DLNode p = header.getNext();
        while (p != trailer) {
            System.out.print(p.getElem() + " ");
            p = p.getNext();
        }
        System.out.println();
    }
}