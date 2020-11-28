/*
 * ��������ʵ�ֶ�����
 */

package dsa;

public class BinTree_LinkedList implements BinTree {

    protected BinTreePosition root;//���ڵ�

    /**************************** ���캯�� ****************************/
    public BinTree_LinkedList() {
        this(null);
    }

    public BinTree_LinkedList(BinTreePosition r) {
        root = r;
    }

    /**************************** BinaryTree�ӿڷ��� ****************************/
    //��������
    public BinTreePosition getRoot() {
        return root;
    }

    //�ж��Ƿ�����
    public boolean isEmpty() {
        return null == root;
    }

    //�������Ĺ�ģ���������ĺ����Ŀ��
    public int getSize() {
        return isEmpty() ? 0 : root.getSize();
    }

    //�������������ĸ߶�
    public int getHeight() {
        return isEmpty() ? -1 : root.getHeight();
    }

    //ǰ�����
    public Iterator elementsPreorder() {
        return root.elementsPreorder();
    }

    //�������
    public Iterator elementsInorder() {
        return root.elementsInorder();
    }

    //�������
    public Iterator elementsPostorder() {
        return root.elementsPostorder();
    }

    //��α���
    public Iterator elementsLevelorder() {
        return root.elementsLevelorder();
    }
}