/*
 * ��ȫ�������ӿ�
 */

package dsa;

public interface ComplBinTree extends BinTree {

    //���ɲ�����һ�����e���ⲿ�ڵ㣬�ýڵ��Ϊ�µ�ĩ�ڵ�
    BinTreePosition addLast(Object e);

    //ɾ��ĩ�ڵ㣬���������д�ŵ�����
    Object delLast();

    //���ذ��ղ�α������Ϊi�Ľڵ��λ�ã�0 <= i < size()
    BinTreePosition posOfNode(int i);
}