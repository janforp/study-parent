/*
 * �б�ADT�ӿ�
 */

package dsa;

public interface List {

    //��ѯ�б�ǰ�Ĺ�ģ
    public int getSize();

    //�ж��б��Ƿ�Ϊ��
    public boolean isEmpty();

    //���ص�һ��Ԫ�أ���λ�ã�
    public Position first();

    //�������һ��Ԫ�أ���λ�ã�
    public Position last();

    //���ؽ��Ӹ���λ��֮���Ԫ�أ���λ�ã�
    public Position getNext(Position p)
            throws ExceptionPositionInvalid, ExceptionBoundaryViolation;

    //���ؽ�������λ��֮ǰ��Ԫ�أ���λ�ã�
    public Position getPrev(Position p)
            throws ExceptionPositionInvalid, ExceptionBoundaryViolation;

    //��e��Ϊ��һ��Ԫ�ز����б�
    public Position insertFirst(Object e);

    //��e��Ϊ���һ��Ԫ�ز����б�
    public Position insertLast(Object e);

    //��e���������Ӹ���λ��֮���λ��
    public Position insertAfter(Position p, Object e)
            throws ExceptionPositionInvalid;

    //��e��������������λ��֮ǰ��λ��
    public Position insertBefore(Position p, Object e)
            throws ExceptionPositionInvalid;

    //ɾ������λ�ô���Ԫ�أ�������֮
    public Object remove(Position p)
            throws ExceptionPositionInvalid;

    //ɾ����Ԫ�أ�������֮
    public Object removeFirst();

    //ɾ��ĩԪ�أ�������֮
    public Object removeLast();

    //�����ڸ���λ�õ�Ԫ���滻Ϊ��Ԫ�أ������ر��滻��Ԫ��
    public Object replace(Position p, Object e)
            throws ExceptionPositionInvalid;

    //λ�õ�����
    public Iterator positions();

    //Ԫ�ص�����
    public Iterator elements();
}