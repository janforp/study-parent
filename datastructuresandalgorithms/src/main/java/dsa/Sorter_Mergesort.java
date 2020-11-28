/*
 * �鲢�����㷨
 * ����������б����������merge()�����ľ���ʵ�ֲ�ͬ
 * ����ֻ��Ի����б�����У������㷨ʵ��
 */

package dsa;

public class Sorter_Mergesort implements Sorter {

    protected Comparator C;

    public Sorter_Mergesort() {
        this(new ComparatorDefault());
    }

    public Sorter_Mergesort(Comparator comp) {
        C = comp;
    }

    public void sort(Sequence S) {//Mergesort
        int n = S.getSize();
		if (1 >= n) {
			return;//�ݹ��
		}
        Sequence S1 = new Sequence_DLNode();
        Sequence S2 = new Sequence_DLNode();
        while (!S.isEmpty()) {//��S���ȵطֳ�����������S1��S2
            S1.insertLast(S.remove(S.first()));
			if (!S.isEmpty()) {
				S2.insertLast(S.remove(S.first()));
			}
        }
        sort(S1);
        sort(S2);
        merge(S, S1, S2);
    }

    public void merge(Sequence S, Sequence S1, Sequence S2) {//�����б�Ĺ鲢�㷨
        while (!S1.isEmpty() || !S2.isEmpty()) {
            Object e;
            //���������б��Ϊ��֮ǰ�����ϵ�ժ��������Ԫ���е�С��e
			if (S1.isEmpty()) {
				e = S2.remove(S2.first());
			} else if (S2.isEmpty()) {
				e = S1.remove(S1.first());
			} else if (0 < C.compare(S1.first().getElem(), S2.first().getElem())) {
				e = S2.remove(S2.first());
			} else {
				e = S1.remove(S1.first());
			}
            //����Ԫ�ز���S��β��
            S.insertLast(e);
        }//while
    }
}