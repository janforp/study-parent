/*
 * ���������㷨
 * ����������б����������createPivot()�����ľ���ʵ�ֲ�ͬ
 * ����ֻ��Ի������������У������㷨ʵ��
 */

package dsa;

public class Sorter_Quicksort implements Sorter {

    private Comparator C;

    public Sorter_Quicksort() {
        this(new ComparatorDefault());
    }

    public Sorter_Quicksort(Comparator comp) {
        C = comp;
    }

    public void sort(Sequence s)//��ڷ���
    {
        qsort(s, 0, s.getSize() - 1);
    }

    public void qsort(Sequence S, int lo, int hi) {//Quicksort
		if (lo >= hi) {
			return;
		}
        int mi = createPivot(S, lo, hi);
        qsort(S, lo, mi - 1);
        qsort(S, mi + 1, hi);
    }

    public int createPivot(Sequence S, int lo, int hi) {//ȷ�����
        while (lo < hi) {
			while ((lo < hi) && (C.compare(S.getAtRank(lo), S.getAtRank(hi)) <= 0)) {
				hi--;
			}
            swap(S, lo, hi);
			while ((lo < hi) && (C.compare(S.getAtRank(lo), S.getAtRank(hi)) <= 0)) {
				lo++;
			}
            swap(S, lo, hi);
        }
        return lo;
    }

    private void swap(Sequence S, int i, int j) {//����S[i]��S[j]
        Object temp = S.getAtRank(i);
        S.replaceAtRank(i, S.getAtRank(j));
        S.replaceAtRank(j, temp);
    }
}