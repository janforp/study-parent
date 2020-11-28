/*
 * Ĭ����Ŀ
 */

package dsa;

public class EntryDefault implements Entry {

    protected Object key;

    protected Object value;

    /**************************** ���캯�� ****************************/
    public EntryDefault(Object k, Object v) {
        key = k;
        value = v;
    }

    /**************************** Entry�ӿڷ��� ****************************/
    //ȡ��Ŀ�Ĺؼ���
    public Object getKey() {
        return key;
    }

    //�޸���Ŀ�Ĺؼ��룬���ش�ǰ��ŵĹؼ���
    public Object setKey(Object k) {
        Object oldK = key;
        key = k;
        return oldK;
    }

    //ȡ��Ŀ�����ݶ���
    public Object getValue() {
        return value;
    }

    //�޸���Ŀ�����ݶ��󣬷��ش�ǰ��ŵ����ݶ���
    public Object setValue(Object v) {
        Object oldV = value;
        value = v;
        return oldV;
    }
}