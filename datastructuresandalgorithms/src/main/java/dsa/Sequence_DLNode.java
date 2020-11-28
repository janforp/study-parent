/*
 * ����˫������ʵ������
 */

package dsa;

public class Sequence_DLNode extends List_DLNode implements Sequence {

    //�����r�Ƿ���[0, n)֮��
    protected void checkRank(int r, int n)
            throws ExceptionBoundaryViolation {
		if (r < 0 || r >= n) {
			throw new ExceptionBoundaryViolation("���⣺�Ƿ�����" + r + "��Ӧ������[0, " + n + ")");
		}
    }

    //��0 <= r < getSize()���򷵻���Ϊr��Ԫ�����ڵ�λ�ã����򣬱���--O(n)
    public Position rank2Pos(int r) throws ExceptionBoundaryViolation {
        DLNode node;
        checkRank(r, getSize());
        if (r <= getSize() / 2) {//���Ƚ�С����
            node = header.getNext();//��ǰ�˿�ʼ
			for (int i = 0; i < r; i++) {
				node = node.getNext();//��һɨ��
			}
        } else {//���Ƚϴ���
            node = trailer.getPrev();//�Ӻ�˿�ʼ
			for (int i = 1; i < getSize() - r; i++) {
				node = node.getPrev();//��һɨ��
			}
        }
        return node;
    }

    //��p�������еĺϷ�λ�ã��򷵻ش����p����Ԫ�ص��ȣ����򣬱���--O(n)
    public int pos2Rank(Position p) throws ExceptionPositionInvalid {
        DLNode node = header.getNext();
        int r = 0;
        while (node != trailer) {
			if (node == p) {
				return (r);
			}
            node = node.getNext();
            r++;
        }
        throw new ExceptionPositionInvalid("���⣺��Ϊ������λ�ò���������");
    }

    //ȡ��Ϊr��Ԫ��--O(n)
    public Object getAtRank(int r) throws ExceptionBoundaryViolation {
        checkRank(r, getSize());
        return rank2Pos(r).getElem();
    }

    //����Ϊr��Ԫ���滻Ϊobj--O(n)
    public Object replaceAtRank(int r, Object obj) throws ExceptionBoundaryViolation {
        checkRank(r, getSize());
        return replace(rank2Pos(r), obj);
    }

    //����obj����Ϊ��Ϊr��Ԫ��--O(n)�����ظ�Ԫ��
    public Object insertAtRank(int r, Object obj) throws ExceptionBoundaryViolation {
        checkRank(r, getSize() + 1);
		if (getSize() == r) {
			insertLast(obj);
		} else {
			insertBefore(rank2Pos(r), obj);
		}
        return obj;
    }

    //ɾ����Ϊr��Ԫ��--O(n)
    public Object removeAtRank(int r) throws ExceptionBoundaryViolation {
        checkRank(r, getSize());
        return remove(rank2Pos(r));
    }
}