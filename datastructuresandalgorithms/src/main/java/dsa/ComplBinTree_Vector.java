/*
 * ��������ʵ�ֵ���ȫ������
 */

package dsa;

public class ComplBinTree_Vector extends BinTree_LinkedList implements ComplBinTree {

	private Vector T;//����

	//���췽����Ĭ�ϵĿ���
	public ComplBinTree_Vector() {
		T = new Vector_ExtArray();
		root = null;
	}

	//���췽�������ո����Ľڵ����У�����ʽ������ȫ������
	public ComplBinTree_Vector(Sequence s) {
		this();
		if (null != s) {
			while (!s.isEmpty()) {
				addLast(s.removeFirst());
			}
		}
	}

	/*---------- BinaryTree�ӿ��и�������ʵ�� ----------*/
	//������������д��
	public BinTreePosition getRoot() {
		return T.isEmpty() ? null : posOfNode(0);
	}

	//�ж��Ƿ����գ���д��
	public boolean isEmpty() {
		return T.isEmpty();
	}

	//�������Ĺ�ģ����д��
	public int getSize() {
		return T.getSize();
	}

	//�������������ĸ߶ȣ���д��
	public int getHeight() {
		return isEmpty() ? -1 : getRoot().getHeight();
	}

	/*---------- ComplBinTree�ӿ��и�������ʵ�� ----------*/
	//���ɲ�����һ�����e���ⲿ�ڵ㣬�ýڵ��Ϊ�µ�ĩ�ڵ�
	public BinTreePosition addLast(Object e) {
		BinTreePosition node = new ComplBinTreeNode_Rank(T, e);
		root = (BinTreePosition) T.getAtRank(0);
		return node;
	}

	//ɾ��ĩ�ڵ㣬���������д�ŵ�����
	public Object delLast() {
		if (isEmpty()) {
			return null;//�������ѣ��ѿգ��޷�ɾ��
		}
		if (1 == getSize()) {
			root = null;//��ɾ�����һ���ڵ㣬������
		}
		return T.removeAtRank(T.getSize() - 1);
	}

	//���ذ��ղ�α������Ϊi�Ľڵ��λ�ã�0 <= i < size()
	public BinTreePosition posOfNode(int i) {
		return (BinTreePosition) T.getAtRank(i);
	}
}
