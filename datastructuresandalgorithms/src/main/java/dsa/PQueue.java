/*
 * ���ȶ��нӿ�
 */

package dsa;

public interface PQueue {

    //ͳ�����ȶ��еĹ�ģ
    public int getSize();

    //�ж����ȶ����Ƿ�Ϊ��
    public boolean isEmpty();

    //��Q�ǿգ��򷵻����е���С��Ŀ������ɾ����;���򣬱���
    public Entry getMin() throws ExceptionPQueueEmpty;

    //������obj��ؼ���k�ϳ�һ����Ŀ���������Q�У������ظ���Ŀ
    public Entry insert(Object key, Object obj) throws ExceptionKeyInvalid;

    //��Q�ǿգ��������ժ���ؼ�����С����Ŀ�������ظ���Ŀ�����򣬱���
    public Entry delMin() throws ExceptionPQueueEmpty;
}