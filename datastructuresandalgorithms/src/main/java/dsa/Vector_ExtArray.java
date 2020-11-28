/*
 * ���ڿ��������������ʵ��
 */

package dsa;

public class Vector_ExtArray implements Vector {

    private int N = 8;//������������ɲ�������

    private int n;//������ʵ�ʹ�ģ

    private Object A[];//��������

    //���캯��
    public Vector_ExtArray() {
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
    public Object getAtRank(int r)
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

    //����obj����Ϊ��Ϊr��Ԫ�أ������ظ�Ԫ��
    public Object insertAtRank(int r, Object obj)
            throws ExceptionBoundaryViolation {
		if (0 > r || r > n) {
			throw new ExceptionBoundaryViolation("���⣺��Խ��");
		}
        if (N <= n) {//�ռ�����Ĵ���
            N *= 2;
            Object B[] = new Object[N];//����һ�������ӱ�������
			for (int i = 0; i < n; i++) {
				B[i] = A[i];//A[]�����ݸ�����B[]
			}
            A = B;//��B�滻A��ԭA[]�����Զ����գ�
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
		for (int i = r; i < n - 1; i++) {
			A[i] = A[i + 1];//����Ԫ��˳��ǰ��
		}
        n--;//���µ�ǰ��ģ
        return bak;
    }
}