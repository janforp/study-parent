/*
 * �����б�ʵ�֣����򣩴ʵ�ṹ
 */

package dsa;

public class Dictionary_DLNode implements Dictionary {

    private List L;//�����Ŀ���б�

    private EqualityTester T;//�е���

    //���췽��
    public Dictionary_DLNode() {
        this(new EqualityTesterDefault());
    }

    //Ĭ�Ϲ��췽��
    public Dictionary_DLNode(EqualityTester t) {
        L = new List_DLNode();
        T = t;
    }

    /***************************** ADT���� *****************************/
    //��ѯ�ʵ�ṹ��ǰ�Ĺ�ģ
    public int getSize() {
        return L.getSize();
    }

    //�жϴʵ�ṹ�Ƿ�Ϊ��
    public boolean isEmpty() {
        return L.isEmpty();
    }

    //���ʵ��д�����keyΪ�ؼ������Ŀ���򷵻����е�һ����Ŀ�����򣬷���null
    public Entry find(Object key) {
        Iterator P = L.positions();
        while (P.hasNext()) {
            Position pos = (Position) P.getNext();
            Entry entry = (EntryDefault) pos.getElem();
			if (T.isEqualTo(entry.getKey(), key)) {
				return entry;
			}
        }
        return null;
    }

    //�����ɹؼ���Ϊkey����Ŀ��ɵĵ�����
    public Iterator findAll(Object key) {
        List list = new List_DLNode();
        Iterator P = L.positions();
        while (P.hasNext()) {
            Position pos = (Position) P.getNext();
            Entry entry = (EntryDefault) pos.getElem();
			if (T.isEqualTo(entry.getKey(), key)) {
				list.insertLast(entry);
			}
        }
        return new IteratorElement(list);
    }

    //������Ŀ(key, value)�������ظ���Ŀ
    public Entry insert(Object key, Object value) {
        Entry entry = new EntryDefault(key, value);//��������Ŀ
        L.insertFirst(entry);//������Ŀ�������ף���
        return entry;//����null��־
    }

    //���ʵ��д�����keyΪ�ؼ������Ŀ����ժ�����е�һ�������أ����򣬷���null
    public Entry remove(Object key) {
        Iterator P = L.positions();
        while (P.hasNext()) {//��һ�Ա�
            Position pos = (Position) P.getNext();//����λ��
            Entry entry = (EntryDefault) pos.getElem();//������Ŀ
            if (T.isEqualTo(entry.getKey(), key)) {//������key�ѳ�����ĳ����Ŀ�У���
                Entry oldEntry = entry;//�ȱ�������Ŀ
                L.remove(pos);//ɾ������Ŀ
                return oldEntry;//��󷵻�ԭ�ȵ���Ŀ
            }
        }//����ѭ��������˵��key��δ�ڴʵ��г��֣����
        return null;//����null��־
    }

    //���شʵ���������Ŀ��һ��������
    public Iterator entries() {
        return new IteratorElement(L);
    }//ֱ������List�ӿڵķ�������Ԫ�ص�����
}