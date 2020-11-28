/*
 * ��������ʵ�����ṹ
 */

package dsa;

public class TreeLinkedList implements Tree {

	private Object element;//�����ڵ�

	private TreeLinkedList parent, firstChild, nextSibling;//���ס����Ӽ����ĵܵ�

	//�����ڵ��������췽��
	public TreeLinkedList() {
		this(null, null, null, null);
	}

	//���췽��
	public TreeLinkedList(Object e, TreeLinkedList p, TreeLinkedList c, TreeLinkedList s) {
		element = e;
		parent = p;
		firstChild = c;
		nextSibling = s;
	}

	/*---------- Tree�ӿ��и�������ʵ�� ----------*/
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

	//���ص�ǰ�ڵ�ĸ��ڵ㣻���ڸ��ڵ㣬����null
	public TreeLinkedList getParent() {
		return parent;
	}

	//���ص�ǰ�ڵ�ĳ��ӣ���û�к��ӣ��򷵻�null
	public TreeLinkedList getFirstChild() {
		return firstChild;
	}

	//���ص�ǰ�ڵ�����ܵܣ���û�еܵܣ��򷵻�null
	public TreeLinkedList getNextSibling() {
		return nextSibling;
	}

	//���ص�ǰ�ڵ���Ԫ�ص���Ŀ�����Ե�ǰ�ڵ�Ϊ���������Ĺ�ģ
	public int getSize() {
		int size = 1;//��ǰ�ڵ�Ҳ���Լ��ĺ��
		TreeLinkedList subtree = firstChild;//�ӳ��ӿ�ʼ
		while (null != subtree) {//����
			size += subtree.getSize();//�ۼ�
			subtree = subtree.getNextSibling();//���к��ӵĺ����Ŀ
		}
		return size;//���ɵõ���ǰ�ڵ�ĺ������
	}

	//���ص�ǰ�ڵ�ĸ߶�
	public int getHeight() {
		int height = -1;
		TreeLinkedList subtree = firstChild;//�ӳ��ӿ�ʼ
		while (null != subtree) {//����
			height = Math.max(height, subtree.getHeight());//�����к�����ȡ���߶�
			subtree = subtree.getNextSibling();
		}
		return height + 1;//���ɵõ���ǰ�ڵ�ĸ߶�
	}

	//���ص�ǰ�ڵ�����
	public int getDepth() {
		int depth = 0;
		TreeLinkedList p = parent;//�Ӹ��׿�ʼ
		while (null != p) {//����
			depth++;
			p = p.getParent();//���ʸ���������
		}
		return depth;//�����ȵ���Ŀ����Ϊ��ǰ�ڵ�����
	}
}
