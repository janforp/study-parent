/*
 * �������ڵ�ADT�ӿ�
 */

package dsa;

public interface BinTreePosition extends Position {

    //�ж��Ƿ��и��ף�Ϊʹ����������ࣩ
    public boolean hasParent();

    //���ص�ǰ�ڵ�ĸ��ڵ�
    public BinTreePosition getParent();

    //���õ�ǰ�ڵ�ĸ��ڵ�
    public void setParent(BinTreePosition p);

    //�ж��Ƿ�ΪҶ��
    public boolean isLeaf();

    //�ж��Ƿ�Ϊ���ӣ�Ϊʹ����������ࣩ
    public boolean isLChild();

    //�ж��Ƿ������ӣ�Ϊʹ����������ࣩ
    public boolean hasLChild();

    //���ص�ǰ�ڵ������
    public BinTreePosition getLChild();

    //���õ�ǰ�ڵ�����ӣ�ע�⣺this.lChild��c.parent����һ��Ϊ�գ�
    public void setLChild(BinTreePosition c);

    //�ж��Ƿ�Ϊ�Һ��ӣ�Ϊʹ����������ࣩ
    public boolean isRChild();

    //�ж��Ƿ����Һ��ӣ�Ϊʹ����������ࣩ
    public boolean hasRChild();

    //���ص�ǰ�ڵ���Һ���
    public BinTreePosition getRChild();

    //���õ�ǰ�ڵ���Һ��ӣ�ע�⣺this.rChild��c.parent����һ��Ϊ�գ�
    public void setRChild(BinTreePosition c);

    //���ص�ǰ�ڵ���Ԫ�ص���Ŀ
    public int getSize();

    //�ں��ӷ����仯�󣬸��µ�ǰ�ڵ㼰�����ȵĹ�ģ
    public void updateSize();

    //���ص�ǰ�ڵ�ĸ߶�
    public int getHeight();

    //�ں��ӷ����仯�󣬸��µ�ǰ�ڵ㼰�����ȵĸ߶�
    public void updateHeight();

    //���ص�ǰ�ڵ�����
    public int getDepth();

    //�ڸ��׷����仯�󣬸��µ�ǰ�ڵ㼰���������
    public void updateDepth();

    //������������Ĵ����ҵ���ǰ�ڵ��ֱ��ǰ��
    public BinTreePosition getPrev();

    //������������Ĵ����ҵ���ǰ�ڵ��ֱ�Ӻ��
    public BinTreePosition getSucc();

    //�Ͼ���ǰ�ڵ����丸�׵ĸ��ӹ�ϵ
    //���ص�ǰ�ڵ�
    public BinTreePosition secede();

    //���ڵ�c��Ϊ��ǰ�ڵ������
    public BinTreePosition attachL(BinTreePosition c);

    //���ڵ�c��Ϊ��ǰ�ڵ���Һ���
    public BinTreePosition attachR(BinTreePosition c);

    //ǰ�����
    public Iterator elementsPreorder();

    //�������
    public Iterator elementsInorder();

    //�������
    public Iterator elementsPostorder();

    //��α���
    public Iterator elementsLevelorder();
}