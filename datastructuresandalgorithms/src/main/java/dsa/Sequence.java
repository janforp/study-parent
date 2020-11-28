/*
 * ���нӿ�
 */

package dsa;

public interface Sequence extends Vector, List {

    //��0 <= r < getSize()���򷵻���Ϊr��Ԫ�����ڵ�λ�ã����򣬱���
    public Position rank2Pos(int r)
            throws ExceptionBoundaryViolation;

    //��p�������еĺϷ�λ�ã��򷵻ش����p����Ԫ�ص��ȣ����򣬱���
    public int pos2Rank(Position p)
            throws ExceptionPositionInvalid;
}