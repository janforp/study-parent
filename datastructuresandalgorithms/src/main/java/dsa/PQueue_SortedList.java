/*
 * �������򣨷������б�ʵ�ֵ����ȶ���
 */

package dsa;

public class PQueue_SortedList implements PQueue {

    private List L;

    private Comparator C;

    //���췽����ʹ��Ĭ�ϱȽ�����
    public PQueue_SortedList() {
        this(new ComparatorDefault(), null);
    }

    //���췽����ʹ��ָ���Ƚ�����
    public PQueue_SortedList(Comparator c) {
        this(c, null);
    }

    //���췽����ʹ��ָ����ʼԪ�أ�
    public PQueue_SortedList(Sequence s) {
        this(new ComparatorDefault(), s);
    }

    //���췽����ʹ��ָ���Ƚ����ͳ�ʼԪ�أ�
    public PQueue_SortedList(Comparator c, Sequence s) {
        L = new List_DLNode();
        C = c;
		if (null != s) {
			while (!s.isEmpty()) {
				Entry e = (Entry) s.removeFirst();
				insert(e.getKey(), e.getValue());
			}
		}
    }

    //ͳ�����ȶ��еĹ�ģ
    public int getSize() {
        return L.getSize();
    }

    //�ж����ȶ����Ƿ�Ϊ��
    public boolean isEmpty() {
        return L.isEmpty();
    }

    //��Q�ǿգ��򷵻����е���С��Ŀ������ɾ����;���򣬱���
    public Entry getMin() throws ExceptionPQueueEmpty {
		if (L.isEmpty()) {
			throw new ExceptionPQueueEmpty("���⣺���ȶ��п�");
		}
        return (Entry) L.last();
    }

    //������obj��ؼ���k�ϳ�һ����Ŀ���������Q�У������ظ���Ŀ
    public Entry insert(Object key, Object obj) throws ExceptionKeyInvalid {
        Entry entry = new EntryDefault(key, obj);//����һ������Ŀ
		if (L.isEmpty()//�����ȶ���Ϊ��
				|| (0 > C.compare(((Entry) (L.first().getElem())).getKey(), entry.getKey())))
		//������Ŀ�ǵ�ǰ�����
		{
			L.insertFirst(entry);//��ֱ�Ӳ�������ͷ
		} else {//����
			Position curPos = L.last();//��β��Ŀ��ʼ
			while (0 > C.compare(((Entry) (curPos.getElem())).getKey(), entry.getKey())) {
				curPos = L.getPrev(curPos);//����ǰ�ƣ�ֱ����һ����С��entry����Ŀ
			}
			L.insertAfter(curPos, entry);//���Ӹ���Ŀ֮�����entry
		}
        return (entry);
    }

    //��Q�ǿգ��������ժ���ؼ�����С����Ŀ�������ظ���Ŀ�����򣬱���
    public Entry delMin() throws ExceptionPQueueEmpty {
		if (L.isEmpty()) {
			throw new ExceptionPQueueEmpty("���⣺���ȶ��п�");
		}
        return (Entry) L.remove(L.last());
    }
}