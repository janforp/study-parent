/*
 * ���ȶ��нӿ�
 */

package dsa;

public interface PQueue {

    //ͳ�����ȶ��еĹ�ģ
    int getSize();

    //�ж����ȶ����Ƿ�Ϊ��
    boolean isEmpty();

    //��Q�ǿգ��򷵻����е���С��Ŀ������ɾ����;���򣬱���
    Entry getMin() throws ExceptionPQueueEmpty;

    //������obj��ؼ���k�ϳ�һ����Ŀ���������Q�У������ظ���Ŀ
    Entry insert(Object key, Object obj) throws ExceptionKeyInvalid;

    //��Q�ǿգ��������ժ���ؼ�����С����Ŀ�������ظ���Ŀ�����򣬱���
    Entry delMin() throws ExceptionPQueueEmpty;
}