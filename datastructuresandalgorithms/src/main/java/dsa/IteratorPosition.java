/*
 * �����б�ʵ�ֵ�λ�õ�����
 */

package dsa;

public class IteratorPosition implements Iterator {

    private List list;//�б�

    private Position nextPosition;//��ǰ����һ����λ��

    //Ĭ�Ϲ��췽��
    public IteratorPosition() {
        list = null;
    }

    //���췽��
    public IteratorPosition(List L) {
        list = L;
		if (list.isEmpty())//���б�Ϊ�գ���
		{
			nextPosition = null;//��ǰλ���ÿ�
		} else//����
		{
			nextPosition = list.first();//�ӵ�һ��λ�ÿ�ʼ
		}
    }

    //�����������Ƿ���ʣ���λ��
    public boolean hasNext() {
        return (nextPosition != null);
    }

    //���ص������е���һλ��
    public Object getNext() throws ExceptionNoSuchElement {
		if (!hasNext()) {
			throw new ExceptionNoSuchElement("���⣺û����һλ��");
		}
        Position currentPosition = nextPosition;
		if (currentPosition == list.last())//���ѵ���βλ�ã���
		{
			nextPosition = null;//��������һ��λ��
		} else//����
		{
			nextPosition = list.getNext(currentPosition);//ת����һλ��
		}
        return currentPosition;
    }
}