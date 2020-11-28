/*
 * �����ڽӱ߱�ʵ��ͼ�ṹ
 */

package dsa;

public class Graph_List implements Graph {

	//����
	protected List E;//���������ͼ�����б�

	protected List V;//���������ͼ�����ж���

	//���췽��
	public Graph_List() {
		E = new List_DLNode();
		V = new List_DLNode();
	}

	//ȡͼ�ı߱������
	protected List getE() {
		return E;
	}

	protected List getV() {
		return V;
	}

	//ȡͼ�ж��㡢�ߵ���Ŀ
	public int vNumber() {
		return V.getSize();
	}

	public int eNumber() {
		return E.getSize();
	}

	//ȡͼ�����ж��㡢����λ�õĵ�����
	public Iterator vertices() {
		return V.elements();
	}

	public Iterator vPositions() {
		return V.positions();
	}

	//����ͼ�����бߡ���λ�õĵ�����
	public Iterator edges() {
		return E.elements();
	}

	public Iterator ePositions() {
		return E.positions();
	}

	//����Ƿ���ĳ���ߴӶ���uָ��v
	public boolean areAdjacent(Vertex u, Vertex v) {
		return (null != edgeFromTo(u, v));
	}

	//ȡ�Ӷ���uָ��v�ıߣ��������ڣ��򷵻�null
	public Edge edgeFromTo(Vertex u, Vertex v) {
		for (Iterator it = u.outEdges(); it.hasNext(); ) {//��һ���
			Edge e = (Edge) it.getNext();//��uΪβ��ÿһ����e
			if (v == e.getVPosInV(1).getElem())//��e��(u, v)����
			{
				return e;//���ظñ�
			}
		}
		return null;//��������������(u, v)���򷵻�null
	}

	//������v�Ӷ��㼯��ɾ����������֮
	public Vertex remove(Vertex v) {
		while (0 < v.outDeg())//����vΪβ�����б�
		{
			remove((Edge) (((Vertex_List) v).outEdges.first()).getElem());//��һɾ��
		}
		while (0 < v.inDeg())//����vΪͷ�����б�
		{
			remove((Edge) (((Vertex_List) v).inEdges.first()).getElem());//��һɾ��
		}
		return (Vertex) V.remove(v.getVPosInV());//�ڶ������ɾ��v
	}

	//����e�ӱ߼���ɾ����������֮
	public Edge remove(Edge e) {
		((Vertex_List) e.getVPosInV(0).getElem()).outEdges.remove(e.getEPosInI(0));//�����ĳ��߱���ɾ��e
		((Vertex_List) e.getVPosInV(1).getElem()).inEdges.remove(e.getEPosInI(1));//���յ����߱���ɾ��e
		return (Edge) E.remove(e.getEPosInE());//�ӱ߱���ɾ��e
	}

	//�ڶ��㼯V�в����¶���v����������λ��
	public Position insert(Vertex v) {
		return V.insertLast(v);
	}

	//�ڱ߼�E�в����±�e����������λ��
	public Position insert(Edge e) {
		return E.insertLast(e);
	}
}
