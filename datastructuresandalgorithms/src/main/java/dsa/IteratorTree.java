/**
 * �����б�ʵ�ֵ���������
 */

package dsa;

public class IteratorTree implements Iterator {

    private List list;//�б�

    private Position nextPosition;//��ǰ����һ����Ԫ�ص�λ��

    //Ĭ�Ϲ��췽��
    public IteratorTree() {
        list = null;
    }

    //ǰ�����
    public void elementsPreorderIterator(TreeLinkedList T) {
		if (null == T) {
			return;//�ݹ��
		}
        list.insertLast(T);//���������ǰ�ڵ�
        TreeLinkedList subtree = T.getFirstChild();//�ӵ�ǰ�ڵ�ĳ��ӿ�ʼ
        while (null != subtree) {//���ζԵ�ǰ�ڵ�ĸ�������
            this.elementsPreorderIterator(subtree);//��ǰ�����
            subtree = subtree.getNextSibling();
        }
    }

    //�������
    public void elementsPostorderIterator(TreeLinkedList T) {
		if (null == T) {
			return;//�ݹ��
		}
        TreeLinkedList subtree = T.getFirstChild();//�ӵ�ǰ�ڵ�ĳ��ӿ�ʼ
        while (null != subtree) {//���ζԵ�ǰ�ڵ�ĸ�������
            this.elementsPostorderIterator(subtree);//���������
            subtree = subtree.getNextSibling();
        }
        list.insertLast(T);//�����к�������ʹ������ŷ��ʵ�ǰ�ڵ�
    }

    //��α���
    public void levelTraversalIterator(TreeLinkedList T) {
		if (null == T) {
			return;
		}
        Queue_List Q = new Queue_List();//�ն�
        Q.enqueue(T);//���ڵ����
        while (!Q.isEmpty()) {//�ڶ������±��֮ǰ
            TreeLinkedList tree = (TreeLinkedList) (Q.dequeue());//ȡ�������׽ڵ�
            list.insertLast(tree);//���³��ӵĽڵ�����������
            TreeLinkedList subtree = tree.getFirstChild();//��tree�ĵ�һ��������
            while (null != subtree) {//�����ҳ����к��ӣ���
                Q.enqueue(subtree);//�������������
                subtree = subtree.getNextSibling();
            }
        }
    }

    //�����������Ƿ���ʣ���Ԫ��
    public boolean hasNext() {
        return (null != nextPosition);
    }

    //���ص������е���һԪ��
    public Object getNext() throws ExceptionNoSuchElement {
		if (!hasNext()) {
			throw new ExceptionNoSuchElement("No next position");
		}
        Position currentPosition = nextPosition;
		if (currentPosition == list.last())//���ѵ���βԪ�أ���
		{
			nextPosition = null;//��������һԪ��
		} else//����
		{
			nextPosition = list.getNext(currentPosition);//ת����һԪ��
		}
        return currentPosition.getElem();
    }
}