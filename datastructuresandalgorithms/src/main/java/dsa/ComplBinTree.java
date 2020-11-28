/*
 * ��ȫ�������ӿ�
 */

package dsa;

public interface ComplBinTree extends BinTree {

    //���ɲ�����һ�����e���ⲿ�ڵ㣬�ýڵ��Ϊ�µ�ĩ�ڵ�
    public BinTreePosition addLast(Object e);

    //ɾ��ĩ�ڵ㣬���������д�ŵ�����
    public Object delLast();

    //���ذ��ղ�α������Ϊi�Ľڵ��λ�ã�0 <= i < size()
    public BinTreePosition posOfNode(int i);
}