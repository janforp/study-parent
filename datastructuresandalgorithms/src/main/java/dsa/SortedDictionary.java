/*
 * ����ʵ�ӿ�
 */

package dsa;

public interface SortedDictionary extends Dictionary {

    //���ʵ�ǿգ��򷵻����йؼ�����С����Ŀ�����򣬷���null
    public Entry first();

    //���ʵ�ǿգ��򷵻����йؼ���������Ŀ�����򣬷���null
    public Entry last();

    //�����ɹؼ��벻С��key����Ŀ���ǽ�����ɵĵ�����
    public Iterator successors(Object key);

    //�����ɹؼ��벻����key����Ŀ����������ɵĵ�����
    public Iterator predecessors(Object key);
}