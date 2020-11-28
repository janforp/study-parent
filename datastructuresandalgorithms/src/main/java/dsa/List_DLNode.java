/*
 * ����˫������ʵ���б�ṹ
 */

package dsa;

public class List_DLNode implements List {

	protected int numElem;//�б��ʵ�ʹ�ģ

	protected DLNode header, trailer;//�ڱ����׽ڵ�+ĩ�ڵ�

	//���캯��
	public List_DLNode() {
		numElem = 0;//�ձ�
		header = new DLNode(null, null, null);//ͷ�ڵ�
		trailer = new DLNode(null, header, null);//β�ڵ�
		header.setNext(trailer);//ͷ��β�ڵ��໥����
	}

	/**************************** �������� ****************************/
	//������λ�����б����Ƿ�Ϸ������ǣ�����ת��Ϊ*DLNode
	protected DLNode checkPosition(Position p) throws ExceptionPositionInvalid {
		if (null == p) {
			throw new ExceptionPositionInvalid("���⣺���ݸ�List_DLNode��λ����null");
		}
		if (header == p) {
			throw new ExceptionPositionInvalid("���⣺ͷ�ڵ��ڱ�λ�÷Ƿ�");
		}
		if (trailer == p) {
			throw new ExceptionPositionInvalid("���⣺β����ڱ�λ�÷Ƿ�");
		}
		DLNode temp = (DLNode) p;
		return temp;
	}

	/**************************** ADT���� ****************************/
	//��ѯ�б�ǰ�Ĺ�ģ
	public int getSize() {
		return numElem;
	}

	//�ж��б��Ƿ�Ϊ��
	public boolean isEmpty() {
		return (numElem == 0);
	}

	//���ص�һ��Ԫ�أ���λ�ã�
	public Position first() throws ExceptionListEmpty {
		if (isEmpty()) {
			throw new ExceptionListEmpty("���⣺�б��");
		}
		return header.getNext();
	}

	//�������һ��Ԫ�أ���λ�ã�
	public Position last() throws ExceptionListEmpty {
		if (isEmpty()) {
			throw new ExceptionListEmpty("���⣺�б��");
		}
		return trailer.getPrev();
	}

	//���ؽ�������λ��֮ǰ��Ԫ�أ���λ�ã�
	public Position getPrev(Position p)
			throws ExceptionPositionInvalid, ExceptionBoundaryViolation {
		DLNode v = checkPosition(p);
		DLNode prev = v.getPrev();
		if (prev == header) {
			throw new ExceptionBoundaryViolation("���⣺��ͼԽ���б�ǰ��");
		}
		return prev;
	}

	//���ؽ��Ӹ���λ��֮���Ԫ�أ���λ�ã�
	public Position getNext(Position p)
			throws ExceptionPositionInvalid, ExceptionBoundaryViolation {
		DLNode v = checkPosition(p);
		DLNode next = v.getNext();
		if (next == trailer) {
			throw new ExceptionBoundaryViolation("���⣺��ͼԽ���б���");
		}
		return next;
	}

	//��e��������������λ��֮ǰ��λ��
	public Position insertBefore(Position p, Object element)
			throws ExceptionPositionInvalid {
		DLNode v = checkPosition(p);
		numElem++;
		DLNode newNode = new DLNode(element, v.getPrev(), v);
		v.getPrev().setNext(newNode);
		v.setPrev(newNode);
		return newNode;
	}

	//��e���������Ӹ���λ��֮���λ��
	public Position insertAfter(Position p, Object element)
			throws ExceptionPositionInvalid {
		DLNode v = checkPosition(p);
		numElem++;
		DLNode newNode = new DLNode(element, v, v.getNext());
		v.getNext().setPrev(newNode);
		v.setNext(newNode);
		return newNode;
	}

	//��e��Ϊ��һ��Ԫ�ز����б�
	public Position insertFirst(Object e) {
		numElem++;
		DLNode newNode = new DLNode(e, header, header.getNext());
		header.getNext().setPrev(newNode);
		header.setNext(newNode);
		return newNode;
	}

	//��e��Ϊ���һ��Ԫ�ز����б�
	public Position insertLast(Object e) {
		numElem++;
		DLNode newNode = new DLNode(e, trailer.getPrev(), trailer);
		if (null == trailer.getPrev()) {
			System.out.println("!!!Prev of trailer is NULL!!!");
		}
		trailer.getPrev().setNext(newNode);
		trailer.setPrev(newNode);
		return newNode;
	}

	//ɾ������λ�ô���Ԫ�أ�������֮
	public Object remove(Position p)
			throws ExceptionPositionInvalid {
		DLNode v = checkPosition(p);
		numElem--;
		DLNode vPrev = v.getPrev();
		DLNode vNext = v.getNext();
		vPrev.setNext(vNext);
		vNext.setPrev(vPrev);
		Object vElem = v.getElem();
		//����λ�ã��ڵ㣩���б���ժ�����Ա�ϵͳ������ռ�õĿռ�
		v.setNext(null);
		v.setPrev(null);
		return vElem;
	}

	//ɾ����Ԫ�أ�������֮
	public Object removeFirst() {
		return remove(header.getNext());
	}

	//ɾ��ĩԪ�أ�������֮
	public Object removeLast() {
		return remove(trailer.getPrev());
	}

	//�����ڸ���λ�õ�Ԫ���滻Ϊ��Ԫ�أ������ر��滻��Ԫ��
	public Object replace(Position p, Object obj)
			throws ExceptionPositionInvalid {
		DLNode v = checkPosition(p);
		Object oldElem = v.getElem();
		v.setElem(obj);
		return oldElem;
	}

	//λ�õ�����
	public Iterator positions() {
		return new IteratorPosition(this);
	}

	//Ԫ�ص�����
	public Iterator elements() {
		return new IteratorElement(this);
	}
}
