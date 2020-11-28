/*
 * ��������ʵ�ֵ�BST�ڵ���
 */

package dsa;

public class BSTreeNode extends BinTreeNode implements BinTreePosition, Entry {

    /**************************** ���췽�� ****************************/
    public BSTreeNode() {
        super();
    }

    public BSTreeNode(
            Object e,//�ڵ�����
            BinTreePosition p,//���ڵ�
            boolean asLChild,//�Ƿ���Ϊ���ڵ������
            BinTreePosition l,//����
            BinTreePosition r)//�Һ���
    {
        super(e, p, asLChild, l, r);
    }

    /**************************** ʵ��Entry�ӿڵķ��� ****************************/
    //���ص�ǰ�ڵ�Ĺؼ���
    public Object getKey() {
        return ((Entry) getElem()).getKey();
    }

    //�޸���Ŀ�Ĺؼ��룬���ش�ǰ��ŵĹؼ���
    public Object setKey(Object k) {
        return ((Entry) getElem()).setKey(k);
    }

    //ȡ��Ŀ�����ݶ���
    public Object getValue() {
        return ((Entry) getElem()).getValue();
    }

    //�޸���Ŀ�����ݶ��󣬷��ش�ǰ��ŵ����ݶ���
    public Object setValue(Object v) {
        return ((Entry) getElem()).setValue(v);
    }
}