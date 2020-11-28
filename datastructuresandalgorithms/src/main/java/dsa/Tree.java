/*
 * ��ADT�ӿ�
 */

package dsa;

public interface Tree {

    //���ص�ǰ�ڵ��д�ŵĶ���
    public Object getElem();

    //������obj���뵱ǰ�ڵ㣬�����ش�ǰ������
    public Object setElem(Object obj);

    //���ص�ǰ�ڵ�ĸ��ڵ�
    public TreeLinkedList getParent();

    //���ص�ǰ�ڵ�ĳ���
    public TreeLinkedList getFirstChild();

    //���ص�ǰ�ڵ�����ܵ�
    public TreeLinkedList getNextSibling();

    //���ص�ǰ�ڵ���Ԫ�ص���Ŀ�����Ե�ǰ�ڵ�Ϊ���������Ĺ�ģ
    public int getSize();

    //���ص�ǰ�ڵ�ĸ߶�
    public int getHeight();

    //���ص�ǰ�ڵ�����
    public int getDepth();
}