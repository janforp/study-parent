/*
 * ���������б�ʵ�ֵ����ȶ���
 */

package dsa;

public class PQueue_UnsortedList implements PQueue {

    private List L;

    private Comparator C;

    //���췽����ʹ��Ĭ�ϱȽ�����
    public PQueue_UnsortedList() {
        this(new ComparatorDefault(), null);
    }

    //���췽����ʹ��ָ���Ƚ�����
    public PQueue_UnsortedList(Comparator c) {
        this(c, null);
    }

    //���췽����ʹ��ָ����ʼԪ�أ�
    public PQueue_UnsortedList(Sequence s) {
        this(new ComparatorDefault(), s);
    }

    //���췽����ʹ��ָ���Ƚ����ͳ�ʼԪ�أ�
    public PQueue_UnsortedList(Comparator c, Sequence s) {
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
        Position minPos = L.first();
        Position curPos = L.getNext(minPos);
		while (null != curPos)//���μ������λ�ã��ҳ���С��Ŀ
		{
			if (0 < C.compare(minPos.getElem(), curPos.getElem())) {
				minPos = curPos;
			}
		}
        return (Entry) minPos.getElem();
    }

    //������obj��ؼ���k�ϳ�һ����Ŀ���������Q�У������ظ���Ŀ
    public Entry insert(Object key, Object obj) throws ExceptionKeyInvalid {
        Entry entry = new EntryDefault(key, obj);//����һ������Ŀ
        L.insertLast(entry);//�����б�ĩβ
        return (entry);
    }

    //��Q�ǿգ��������ժ���ؼ�����С����Ŀ�������ظ���Ŀ�����򣬱���
    public Entry delMin() throws ExceptionPQueueEmpty {
		if (L.isEmpty()) {
			throw new ExceptionPQueueEmpty("���⣺���ȶ��п�");
		}
        Position minPos = L.first();
        Iterator it = L.positions();
        while (it.hasNext()) {//���μ������λ�ã��ҳ���С��Ŀ
            Position curPos = (Position) (it.getNext());
            //			System.out.println("\t" + ((Entry)(curPos.getElem())).getKey());
			if (0 < C.compare(
					((Entry) (minPos.getElem())).getKey(),
					((Entry) (curPos.getElem())).getKey())
			) {
				minPos = curPos;
			}
        }
        return (Entry) L.remove(minPos);
    }
}