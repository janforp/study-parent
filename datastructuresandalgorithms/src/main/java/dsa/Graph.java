/*
 * ������ͼ�ṹ�ӿ�
 *	����ͼҲ���Կ���������ͼ��Ϊ�ˣ�ֻ�轫ÿ��������滻Ϊ�ԳƵ�һ�������
 */

package dsa;

public interface Graph {

	//ȡͼ�ж��㡢�ߵ���Ŀ
	public int vNumber();

	public int eNumber();

	//ȡͼ�����ж��㡢����λ�õĵ�����
	public Iterator vertices();

	public Iterator vPositions();

	//����ͼ�����бߡ���λ�õĵ�����
	public Iterator edges();

	public Iterator ePositions();

	//����Ƿ���ĳ���ߴӶ���uָ��v
	public boolean areAdjacent(Vertex u, Vertex v);

	//ȡ�Ӷ���uָ��v�ıߣ��������ڣ��򷵻�null
	public Edge edgeFromTo(Vertex u, Vertex v);

	//������v�Ӷ��㼯��ɾ����������֮
	public Vertex remove(Vertex v);

	//����e�ӱ߼���ɾ����������֮
	public Edge remove(Edge e);

	//�ڶ��㼯V�в����¶���v����������λ��
	public Position insert(Vertex v);

	//�ڱ߼�E�в����±�e����������λ��
	public Position insert(Edge e);
}