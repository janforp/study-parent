/*
 * ����ɢ�б�ʵ�ֵģ����򣩴ʵ�ṹ
 * ���÷��������Խ����ͻ
 */

package dsa;

public class Dictionary_HashTable implements Dictionary {

    private Dictionary[] A;//Ͱ���飬ÿ��Ͱ����Ҳ��һ���������б�ʵ�ֵģ��ʵ�ṹ

    private int N;//ɢ�б�

    private final double maxLemda = 0.75;//װ����������

    private int size;//�ʵ�ṹ�Ĺ�ģ

    private EqualityTester T;//�е���

    //Ĭ�Ϲ��췽��
    public Dictionary_HashTable() {
        this(0, new EqualityTesterDefault());
    }

    //���췽��
    public Dictionary_HashTable(int n, EqualityTester t) {
        T = t;
        N = p(n);//Ͱ��������ȡΪ��С��n����С����
        A = new Dictionary[N];
		for (int i = 0; i < N; i++) {
			A[i] = new Dictionary_DLNode(T);
		}
        size = 0;
    }

    /***************************** �������� *****************************/
    //ɢ�ж�ַ����������ģ�෨��
    private int h(Object key) {
        return key.hashCode() % N;
    }

    //�ж�n�Ƿ�Ϊ����
    private static boolean prime(int n) {
		for (int i = 3; i < 1 + Math.sqrt(n); i++) {
			if (n / i * i == n) {
				return false;
			}
		}
        return true;
    }

    //ȡ��С��n����С����
    private static int p(int n) {
		if (3 > n) {
			n = 3;
		}
        n = n | 1;//������
		while (!prime(n)) {
			n += 2;
		}
        return n;
    }

    /***************************** ADT���� *****************************/
    //��ѯ�ʵ�ṹ��ǰ�Ĺ�ģ
    public int getSize() {
        return size;
    }

    //�жϴʵ�ṹ�Ƿ�Ϊ��
    public boolean isEmpty() {
        return 0 == size;
    }

    //���ʵ��д�����keyΪ�ؼ������Ŀ���򷵻����е�һ����Ŀ�����򣬷���null
    public Entry find(Object key) {
        return A[h(key)].find(key);
    }

    //�����ɹؼ���Ϊkey����Ŀ��ɵĵ�����
    public Iterator findAll(Object key) {
        return A[h(key)].findAll(key);
    }

    //������Ŀ(key, value)�������ظ���Ŀ
    public Entry insert(Object key, Object value) {
        Entry entry = A[h(key)].insert(key, value);//������Ŀ����ͰA[h(key)]��Ӧ���Ӵʵ�
        size++;//���¹�ģ��¼
		if (size > N * maxLemda) {
			rehash();//��װ�����ӹ�������ɢ��
		}
        return entry;//����null��־
    }

    //���ʵ��д�����keyΪ�ؼ������Ŀ������ժ�������أ����򣬷���null
    public Entry remove(Object key) {
        Entry oldEntry = A[h(key)].remove(key);
		if (null != oldEntry) {
			size--;
		}
        return oldEntry;
    }

    //���شʵ���������Ŀ��һ��������
    public Iterator entries() {
        List L = new List_DLNode();
        for (int i = 0; i < N; i++) {
            Iterator it = A[i].entries();
			while (it.hasNext()) {
				L.insertLast(it.getNext());
			}
        }
        return new IteratorElement(L);
    }

    //��ɢ��
    private void rehash() {
        Iterator it = this.entries();
        N = p(N << 1);
        A = new Dictionary[N];//Ͱ�����������ټӱ�
		for (int i = 0; i < N; i++) {
			A[i] = new Dictionary_DLNode(T);//Ϊÿ��Ͱ����һ���Ӵʵ�
		}
        while (it.hasNext()) {//�����Ӧ�Ĵʵ�ṹ�е�
            Entry e = (Entry) it.getNext();//����Ŀ��һȡ��������
            Object k = e.getKey();//�ؼ����
            Object v = e.getValue();//���ݶ���
            A[h(k)].insert(k, v);//����Ϊ�µ���Ŀ�������Ӧ���Ӵʵ���
        }
    }
}