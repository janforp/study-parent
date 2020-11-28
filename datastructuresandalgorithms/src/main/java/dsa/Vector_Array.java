/*
 * �������������ʵ��
 */

package dsa;

public class Vector_Array implements Vector {

    private final int N = 1024;//���������

    private int n = 0;//������ʵ�ʹ�ģ

    private Object[] A;//��������

    //���캯��
    public Vector_Array() {
        A = new Object[N];
        n = 0;
    }

    //����������Ԫ����Ŀ
    public int getSize() {
        return n;
    }

    //�ж������Ƿ�Ϊ��
    public boolean isEmpty() {
        return (0 == n) ? true : false;
    }

    //ȡ��Ϊr��Ԫ��
    public Object getAtRank(int r)//O(1)
            throws ExceptionBoundaryViolation {
		if (0 > r || r >= n) {
			throw new ExceptionBoundaryViolation("���⣺��Խ��");
		}
        return A[r];
    }

    //����Ϊr��Ԫ���滻Ϊobj
    public Object replaceAtRank(int r, Object obj)
            throws ExceptionBoundaryViolation {
		if (0 > r || r >= n) {
			throw new ExceptionBoundaryViolation("���⣺��Խ��");
		}
        Object bak = A[r];
        A[r] = obj;
        return bak;
    }

    //����obj����Ϊ��Ϊr��Ԫ�أ����ظ�Ԫ��
    public Object insertAtRank(int r, Object obj)
            throws ExceptionBoundaryViolation {
		if (0 > r || r > n) {
			throw new ExceptionBoundaryViolation("���⣺��Խ��");
		}
		if (n >= N) {
			throw new ExceptionBoundaryViolation("���⣺�������");
		}
		for (int i = n; i > r; i--) {
			A[i] = A[i - 1];//����Ԫ��˳�κ���
		}
        A[r] = obj;//����
        n++;//���µ�ǰ��ģ
        return obj;
    }

    //ɾ����Ϊr��Ԫ��
    public Object removeAtRank(int r)
            throws ExceptionBoundaryViolation {
		if (0 > r || r >= n) {
			throw new ExceptionBoundaryViolation("���⣺��Խ��");
		}
        Object bak = A[r];
		for (int i = r; i < n; i++) {
			A[i] = A[i + 1];//����Ԫ��˳��ǰ��
		}
        n--;//���µ�ǰ��ģ
        return bak;
    }
}