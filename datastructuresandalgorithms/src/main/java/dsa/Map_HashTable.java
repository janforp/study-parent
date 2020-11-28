/*
 * ����ɢ�б�ʵ�ֵ�ӳ��ṹ
 * ���÷��������Խ����ͻ
 */

package dsa;

public class Map_HashTable implements Map {

    private Map[] A;//Ͱ���飬ÿ��Ͱ����Ҳ��һ���������б�ʵ�ֵģ�ӳ��ṹ

    private int N;//ɢ�б�

    private final double maxLemda = 0.75;//װ����������

    private int size;//ӳ��ṹ�Ĺ�ģ

    private EqualityTester T;//�е���

    //Ĭ�Ϲ��췽��
    public Map_HashTable() {
        this(0, new EqualityTesterDefault());
    }

    //���췽��
    public Map_HashTable(int n, EqualityTester t) {
        T = t;
        N = p(n);//Ͱ��������ȡΪ��С��n����С����
        A = new Map[N];
        for (int i = 0; i < N; i++) {
            A[i] = new Map_DLNode(T);
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
    //��ѯӳ��ṹ��ǰ�Ĺ�ģ
    public int getSize() {
        return size;
    }

    //�ж�ӳ��ṹ�Ƿ�Ϊ��
    public boolean isEmpty() {
        return 0 == size;
    }

    //��M�д�����keyΪ�ؼ������Ŀ���򷵻ظ���Ŀ�����ݶ��󣻷��򣬷���null
    public Object get(Object key) {
        return A[h(key)].get(key);
    }

    //��M�в�������keyΪ�ؼ������Ŀ������Ŀ(key, value)���뵽M�в�����null
    //���򣬽�������Ŀ�����ݶ����滻Ϊvalue��������ԭ�ȵ����ݶ���
    public Object put(Object key, Object value) {
        Object oldValue = A[h(key)].put(key, value);
        if (null == oldValue) {//���������Ŀδ������ԭɢ�б��У���
            size++;//���¹�ģ��¼
            if (size > N * maxLemda) {
                rehash();//��װ�����ӹ�������ɢ��
            }
        }
        return oldValue;
    }

    //��M�д�����keyΪ�ؼ������Ŀ����ɾ��֮�����������ݶ��󣻷��򣬷���null
    public Object remove(Object key) {
        Object oldValue = A[h(key)].remove(key);
        if (null != oldValue) {
            size--;
        }
        return oldValue;
    }

    //����M��������Ŀ��һ��������
    //����Ͱ��Ӧ��ӳ��ṹ�ĵ�����������������������ĵ�����
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
        A = new Map[N];//Ͱ�����������ټӱ�
        for (int i = 0; i < N; i++) {
            A[i] = new Map_DLNode(T);//Ϊÿ��Ͱ����һ����ӳ��
        }
        while (it.hasNext()) {//�����Ӧ��ӳ��ṹ�е�
            Entry e = (Entry) it.getNext();//����Ŀ��һȡ��������
            Object k = e.getKey();//�ؼ����
            Object v = e.getValue();//���ݶ���
            A[h(k)].put(k, v);//����Ϊ�µ���Ŀ�������Ӧ����ӳ����
        }
    }
}