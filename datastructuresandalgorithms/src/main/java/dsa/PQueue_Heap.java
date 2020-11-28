/*
 * ���ö�ʵ�����ȶ���
 */

package dsa;

public class PQueue_Heap implements PQueue {

    private ComplBinTree H;//��ȫ��������ʽ�Ķ�

    private Comparator comp;//�Ƚ���

    //���췽��
    public PQueue_Heap() {
        this(new ComparatorDefault(), null);
    }

    //���췽����Ĭ�ϵĿ����ȶ���
    public PQueue_Heap(Comparator c) {
        this(c, null);
    }

    //���췽��������ĳһ����ֱ������ʽ������㷨��S��Ԫ�ض�������(key, value)����Ŀ
    public PQueue_Heap(Sequence S) {
        this(new ComparatorDefault(), S);
    }

    //���췽��������ĳһ����ֱ������ʽ������㷨��s��Ԫ�ض�������(key, value)����Ŀ
    public PQueue_Heap(Comparator c, Sequence s) {
        comp = c;
        H = new ComplBinTree_Vector(s);
        if (!H.isEmpty()) {
			for (int i = H.getSize() / 2 - 1; i >= 0; i--)//�Ե׶���
			{
				percolateDown(H.posOfNode(i));//��ڵ��������
			}
        }
    }

    /*-------- PQueue�ӿ��ж���ķ��� --------*/
    //ͳ�����ȶ��еĹ�ģ
    public int getSize() {
        return H.getSize();
    }

    //�ж����ȶ����Ƿ�Ϊ��
    public boolean isEmpty() {
        return H.isEmpty();
    }

    //��Q�ǿգ��򷵻����е���С��Ŀ������ɾ����;���򣬱���
    public Entry getMin() throws ExceptionPQueueEmpty {
		if (isEmpty()) {
			throw new ExceptionPQueueEmpty("���⣺���ȶ���Ϊ��");
		}
        return (Entry) H.getRoot().getElem();
    }

    //������obj��ؼ���k�ϳ�һ����Ŀ���������Q�У������ظ���Ŀ
    public Entry insert(Object key, Object obj) throws ExceptionKeyInvalid {
        checkKey(key);
        Entry entry = new EntryDefault(key, obj);
        percolateUp(H.addLast(entry));
        return entry;
    }

    //��Q�ǿգ��������ժ���ؼ�����С����Ŀ�������ظ���Ŀ�����򣬱���
    public Entry delMin() throws ExceptionPQueueEmpty {
		if (isEmpty()) {
			throw new ExceptionPQueueEmpty("���⣺���ȶ���Ϊ��");
		}
        Entry min = (Entry) H.getRoot().getElem();//�����Ѷ�
		if (1 == getSize())//��ֻʣ�����һ����Ŀ
		{
			H.delLast();//ֱ��ժ��֮
		} else {//����
			H.getRoot().setElem(((ComplBinTreeNode_Rank) H.delLast()).getElem());
			//ȡ�����һ����Ŀ��ֲ��Ѷ�
			percolateDown(H.getRoot());
		}
        return min;//����ԭ�Ѷ�
    }

    /*-------- �������� --------*/
    //���ؼ���ĿɱȽ���
    protected void checkKey(Object key) throws ExceptionKeyInvalid {
        try {
            comp.compare(key, key);
        } catch (Exception e) {
            throw new ExceptionKeyInvalid("�޷��ȽϹؼ���");
        }
    }

    //���ؽڵ�v����������Ŀ���Ĺؼ���
    protected Object key(BinTreePosition v) {
        return ((Entry) (v.getElem())).getKey();
    }

    /*-------- �㷨���� --------*/
    //�������ӽڵ㣨������ŵ����ݣ�
    protected void swapParentChild(BinTreePosition u, BinTreePosition v) {
        Object temp = u.getElem();
        u.setElem(v.getElem());
        v.setElem(temp);
    }

    //�����㷨
    protected void percolateUp(BinTreePosition v) {
        BinTreePosition root = H.getRoot();//��¼���ڵ�
        while (v != H.getRoot()) {//���ϵ�
            BinTreePosition p = v.getParent();//ȡ��ǰ�ڵ�ĸ���
			if (0 >= comp.compare(key(p), key(v))) {
				break;//���Ǹ��ױȺ���С
			}
            swapParentChild(p, v);//���򣬽������Ӵ���
            v = p;//���������µĸ��ڵ㣨��ԭ�ȵĺ��ӣ�
        }
    }

    //�����㷨
    protected void percolateDown(BinTreePosition v) {
        while (v.hasLChild()) {//ֱ��v��ΪҶ��
            BinTreePosition smallerChild = v.getLChild();//���ȼ������ӵģ��ؼ��룩��С
			if (v.hasRChild() && 0 < comp.compare(key(v.getLChild()), key(v.getRChild()))) {
				smallerChild = v.getRChild();//���Һ��Ӵ����Ҹ�С�����Һ�����Ϊ��һ���ȽϵĶ���
			}
			if (0 <= comp.compare(key(smallerChild), key(v))) {
				break;//���������Ӷ�����v��С�����������
			}
            swapParentChild(v, smallerChild);//���򣬽������С�ĺ��ӽ���
            v = smallerChild;//�����������������
        }
    }
}