/*
 * �����б�ʵ��ӳ��ṹ
 */

package dsa;

public class Map_DLNode implements Map {

    private List L;//�����Ŀ���б�

    private EqualityTester T;//�е���

    //���췽��
    public Map_DLNode() {
        this(new EqualityTesterDefault());
    }

    //Ĭ�Ϲ��췽��
    public Map_DLNode(EqualityTester t) {
        L = new List_DLNode();
        T = t;
    }

    /***************************** ADT���� *****************************/
    //��ѯӳ��ṹ��ǰ�Ĺ�ģ
    public int getSize() {
        return L.getSize();
    }

    //�ж�ӳ��ṹ�Ƿ�Ϊ��
    public boolean isEmpty() {
        return L.isEmpty();
    }

    //��M�д�����keyΪ�ؼ������Ŀ���򷵻ظ���Ŀ�����ݶ��󣻷��򣬷���null
    public Object get(Object key) {
        Iterator P = L.positions();
        while (P.hasNext()) {
            Position pos = (Position) P.getNext();
            Entry entry = (EntryDefault) pos.getElem();
            if (T.isEqualTo(entry.getKey(), key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    //��M�в�������keyΪ�ؼ������Ŀ������Ŀ(key, value)���뵽M�в�����null
    //���򣬽�������Ŀ�����ݶ����滻Ϊvalue��������ԭ�ȵ����ݶ���
    public Object put(Object key, Object value) {
        Iterator P = L.positions();
        while (P.hasNext()) {//��һ�Ա�
            Position pos = (Position) P.getNext();//����λ��
            Entry entry = (EntryDefault) pos.getElem();//������Ŀ
            if (T.isEqualTo(entry.getKey(), key)) {//������key�ѳ�����ĳ����Ŀ�У���
                Object oldValue = entry.getValue();//�ȱ�������Ŀԭ�ȵ����ݶ���
                L.replace(pos, new EntryDefault(key, value));//����֮�������ݶ���
                return oldValue;//��󷵻�ԭ�ȵ����ݶ���ע�⣺����nullʱ������
            }
        }//����ѭ��������˵��key��δ��M�г��֣����
        L.insertFirst(new EntryDefault(key, value));//������Ŀ�������ף���
        return null;//����null��־
    }

    //��M�д�����keyΪ�ؼ������Ŀ����ɾ��֮�����������ݶ��󣻷��򣬷���null
    public Object remove(Object key) {
        Iterator P = L.positions();
        while (P.hasNext()) {//��һ�Ա�
            Position pos = (Position) P.getNext();//����λ��
            Entry entry = (EntryDefault) pos.getElem();//������Ŀ
            if (T.isEqualTo(entry.getKey(), key)) {//������key�ѳ�����ĳ����Ŀ�У���
                Object oldValue = entry.getValue();//�ȱ�������Ŀԭ�ȵ����ݶ���
                L.remove(pos);//ɾ������Ŀ
                return oldValue;//��󷵻�ԭ�ȵ����ݶ���ע�⣺����nullʱ������
            }
        }//����ѭ��������˵��key��δ��ӳ���г��֣����
        return null;//����null��־
    }

    //����M��������Ŀ��һ��������
    public Iterator entries() {
        return new IteratorElement(L);
    }//ֱ������List�ӿڵķ�������Ԫ�ص�����
}