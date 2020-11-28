/*
 * ����������ұ�ʵ�ֵ�����ʵ�
 */

package dsa;

public class SortedDictionary_ExtArray implements SortedDictionary {

    Vector S;//������ұ�

    Comparator C;//�Ƚ���

    //Ĭ�Ϲ��췽��
    public SortedDictionary_ExtArray() {
        this(new ComparatorDefault());
    }

    //���췽��
    public SortedDictionary_ExtArray(Comparator comp) {
        S = new Vector_ExtArray();
        C = comp;
    }

    /**************************** �������� ****************************/
    //���ֲ���
    //����ֵ����������Ԫ�ص��ȣ�Ҳ������key���Բ������
    //������Σ���Ҫ��һ�����
    //�����ԣ�����key���շ��ص��Ȳ�������������������Ȼ����
    private static int binSearch(Vector s, Comparator c, Object key, int lo, int hi) {
		if (lo > hi) {
			return lo;//�ݹ��������ʧ��
		}

        int mi = (lo + hi) >> 1;//ȡ��ֵ
        Entry e = (Entry) s.getAtRank(mi);//���е���Ŀ
        int flag = c.compare(key, e.getKey());//�ȽϹؼ���
		if (flag < 0) {
			return binSearch(s, c, key, lo, mi - 1);//ת���������
		} else if (flag > 0) {
			return binSearch(s, c, key, mi + 1, hi);//ת���Ұ�����
		} else {
			return mi;//����
		}
    }

    /**************************** ����ʵ�ADT���� ****************************/
    //��ѯ�ʵ�ṹ��ǰ�Ĺ�ģ
    public int getSize() {
        return S.getSize();
    }

    //�жϴʵ�ṹ�Ƿ�Ϊ��
    public boolean isEmpty() {
        return S.isEmpty();
    }

    //���ʵ��д�����keyΪ�ؼ������Ŀ���򷵻����е�һ����Ŀ�����򣬷���null
    public Entry find(Object key) {
        int k = binSearch(S, C, key, 0, S.getSize() - 1);//���ҹؼ���Ϊkey����Ŀ
		if (0 > k || k >= S.getSize() || (0 != C.compare(key, ((Entry) S.getAtRank(k)).getKey()))) {
			return null;//����������Ŀ�����ڣ��򷵻�ʧ�ܱ�־
		}
        return (Entry) S.getAtRank(k);
    }

    //�����ɹؼ���Ϊkey����Ŀ��ɵĵ�����
    public Iterator findAll(Object key) {
        List L = new List_DLNode();//����һ������L

        int k = binSearch(S, C, key, 0, S.getSize() - 1);//���ҹؼ���Ϊkey����Ŀ
		if (0 > k || k >= S.getSize() || (0 != C.compare(key, ((Entry) S.getAtRank(k)).getKey()))) {
			return new IteratorElement(L);//����������Ŀ�����ڣ��򷵻ؿյ�����
		}

        L.insertFirst(S.getAtRank(k));//��e����L��

        int lo = k;//��S[k-1]��ʼ
        while (0 <= --lo) {//������ǰ����
			if (0 != C.compare(key, ((Entry) S.getAtRank(lo)).getKey())) {
				break;//ֱ����һ�������е���Ŀ
			}
            L.insertFirst(S.getAtRank(lo));//�����е���Ŀ����L��
        }

        int hi = k;//��S[k+1]��ʼ
        while (++hi < S.getSize()) {//�����������
			if (0 != C.compare(key, ((Entry) S.getAtRank(hi)).getKey())) {
				break;//ֱ����һ�������е���Ŀ
			}
            L.insertLast(S.getAtRank(hi));//�����е���Ŀ����L��
        }

        return new IteratorElement(L);//��L����������������֮
    }

    //������Ŀ(key, value)�������ظ���Ŀ
    public Entry insert(Object key, Object value) {
        Entry e = new EntryDefault(key, value);//��������Ŀ

        //���ʵ�Ϊ�գ���ֱ�Ӳ�����Ԫ��
		if (S.isEmpty()) {
			return (Entry) S.insertAtRank(0, e);
		}

        //ͨ�����ֲ��ң�ȷ���ɲ���λ��
        //������Լ���飺����key��S��Ϊ��С����󣬶�������������
        return (Entry) S.insertAtRank(binSearch(S, C, key, 0, S.getSize() - 1), e);
    }

    //���ʵ��д�����keyΪ�ؼ������Ŀ����ժ�����е�һ�������أ����򣬷���null
    public Entry remove(Object key) {
        int k = binSearch(S, C, key, 0, S.getSize() - 1);//���ҹؼ���Ϊkey����Ŀ
		if (0 > k || k >= S.getSize() || (0 != C.compare(key, ((Entry) S.getAtRank(k)).getKey()))) {
			return null;//����������Ŀ�����ڣ��򷵻�ʧ�ܱ�־
		}
        return (Entry) S.removeAtRank(k);
    }

    //���شʵ���������Ŀ��һ��������
    public Iterator entries() {
        List L = new List_DLNode();
		for (int i = 0; i < S.getSize(); i++) {
			L.insertLast(S.getAtRank(i));
		}
        return new IteratorElement(L);//ֱ������List�ӿڵķ�������Ԫ�ص�����
    }

    /**************************** ����ʵ�ADT���� ****************************/
    //���ʵ�ǿգ��򷵻����йؼ�����С����Ŀ�����򣬷���null
    public Entry first() {
        return (S.isEmpty()) ? null : (Entry) S.getAtRank(0);
    }

    //���ʵ�ǿգ��򷵻����йؼ���������Ŀ�����򣬷���null
    public Entry last() {
        return (S.isEmpty()) ? null : (Entry) S.getAtRank(S.getSize() - 1);
    }

    //�����ɹؼ��벻С��key����Ŀ���ǽ�����ɵĵ�����
    public Iterator successors(Object key) {
        List L = new List_DLNode();//����һ������L

        int k = binSearch(S, C, key, 0, S.getSize() - 1);//���ҹؼ���Ϊkey����Ŀ
		if (0 > k || k >= S.getSize() || (0 != C.compare(key, ((Entry) S.getAtRank(k)).getKey()))) {
			return new IteratorElement(L);//����������Ŀ�����ڣ��򷵻ؿյ�����
		}

		while (0 <= --k)//��S[k-1]��ʼ��ǰ������ֱ������Ҫ��ġ�����С��Ԫ��
		{
			if (0 != C.compare(key, ((Entry) S.getAtRank(k)).getKey())) {
				break;
			}
		}
		while (S.getSize() > ++k)//����̵�����Ԫ������
		{
			L.insertLast(S.getAtRank(k));//����L��
		}

        return new IteratorElement(L);//��L����������������֮
    }

    //�����ɹؼ��벻����key����Ŀ����������ɵĵ�����
    public Iterator predecessors(Object key) {
        List L = new List_DLNode();//����һ������L

        int k = binSearch(S, C, key, 0, S.getSize() - 1);//���ҹؼ���Ϊkey����Ŀ
		if (0 > k || k >= S.getSize() || (0 != C.compare(key, ((Entry) S.getAtRank(k)).getKey()))) {
			return new IteratorElement(L);//����������Ŀ�����ڣ��򷵻ؿյ�����
		}

		while (S.getSize() > ++k)//��S[k-1]��ʼ���������ֱ������Ҫ��ġ�������Ԫ��
		{
			if (0 != C.compare(key, ((Entry) S.getAtRank(k)).getKey())) {
				break;
			}
		}
		while (0 <= --k)//��ǰ��������Ԫ������
		{
			L.insertLast(S.getAtRank(k));//����L��
		}

        return new IteratorElement(L);//��L����������������֮
    }
}