/*
 * �������ӿ�
 */

package dsa;

public interface BinTree {

    //��������
    public BinTreePosition getRoot();

    //�ж��Ƿ�����
    public boolean isEmpty();

    //�������Ĺ�ģ���������ĺ����Ŀ��
    public int getSize();

    //�������������ĸ߶�
    public int getHeight();

    //ǰ�����
    public Iterator elementsPreorder();

    //�������
    public Iterator elementsInorder();

    //�������
    public Iterator elementsPostorder();

    //��α���
    public Iterator elementsLevelorder();
}