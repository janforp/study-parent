/*
 * ������ʵ�ֵ���ȫ�������ڵ�
 */

package dsa;

public class ComplBinTreeNode_Rank extends BinTreeNode implements BinTreePosition {

	private Vector T;//��������

	private int rank;//���������е���

	private Object element;//��ŵĶ���

	//���캯��
	public ComplBinTreeNode_Rank(Vector t, Object obj) {
		element = obj;
		T = t;
		rank = T.getSize();
		T.insertAtRank(rank, this);
	}

	//���ص�ǰ�ڵ��д�ŵĶ���
	public Object getElem() {
		return element;
	}

	//������obj���뵱ǰ�ڵ㣬�����ش�ǰ������
	public Object setElem(Object obj) {
		Object bak = element;
		element = obj;
		return bak;
	}

	//�ж��Ƿ��и��ף�Ϊʹ����������ࣩ
	public boolean hasParent() {
		return (0 != rank) ? true : false;
	}

	//���ص�ǰ�ڵ�ĸ��ڵ�
	public BinTreePosition getParent() {
		return hasParent() ? (BinTreePosition) T.getAtRank((rank - 1) / 2) : null;
	}

	//�ж��Ƿ������ӣ�Ϊʹ����������ࣩ
	public boolean hasLChild() {
		return (1 + rank * 2 < T.getSize()) ? true : false;
	}

	//���ص�ǰ�ڵ������
	public BinTreePosition getLChild() {
		return hasLChild() ? (BinTreePosition) (T.getAtRank(1 + rank * 2)) : null;
	}

	//�ж��Ƿ����Һ��ӣ�Ϊʹ����������ࣩ
	public boolean hasRChild() {
		return (2 + rank * 2 < T.getSize()) ? true : false;
	}

	//���ص�ǰ�ڵ���Һ���
	public BinTreePosition getRChild() {
		return hasRChild() ? (BinTreePosition) (T.getAtRank(2 + rank * 2)) : null;
	}

	//���ص�ǰ�ڵ���Ԫ�ص���Ŀ
	public int getSize() {
		int size = 1;
		if (hasLChild()) {
			size += getLChild().getSize();
		}
		if (hasRChild()) {
			size += getRChild().getSize();
		}
		return size;
	}

	//���ص�ǰ�ڵ�ĸ߶�
	public int getHeight() {
		int hL = hasLChild() ? getLChild().getHeight() : -1;
		int hR = hasRChild() ? getRChild().getHeight() : -1;
		return 1 + Math.max(hL, hR);
	}

	//���ص�ǰ�ڵ�����
	public int getDepth() {
		return hasParent() ? 1 + getParent().getDepth() : 0;
	}
}
