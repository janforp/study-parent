/*
 * �����ڽӱ߱�ʵ��ͼ�ı߽ṹ
 */

package dsa;

public class Edge_List implements Edge {

	//����
	protected Object info;//��ǰ���д�ŵ�����Ԫ��

	protected Position ePosInE;//��ǰ����������ͼ�ı߱��е�λ��

	protected Position vPosInV[];//��ǰ�������˵��ڶ�����е�λ��

	protected Position ePosInI[];//��ǰ�����������˵�Ĺ����߱��е�λ��

	//Լ������0��1��������ֱ�Ϊβ��ͷ������
	//��ֹͷ��β������ͬ�ı�
	protected int type;//����������֮�󣩱߱���������

	//���췽������ͼG�У�����һ���Ӷ���u��v���±ߣ��ٶ��ñ��в����ڣ�
	public Edge_List(Graph G, Vertex_List u, Vertex_List v, Object x) {
		info = x;//����Ԫ��
		ePosInE = G.insert(this);//��ǰ����������ͼ�ı߱��е�λ��
		vPosInV = new DLNode[2];//��ǰ�������˵��ڶ�����е�λ��
		vPosInV[0] = u.getVPosInV();
		vPosInV[1] = v.getVPosInV();
		ePosInI = new DLNode[2];//��ǰ�����������˵�Ĺ����߱��е�λ��
		ePosInI[0] = u.outEdges.insertLast(this);//��ǰ�߼���u���ڽӣ������߱�
		ePosInI[1] = v.inEdges.insertLast(this);//��ǰ�߼���v���ڽӣ��룩�߱�
		type = UNKNOWN;
	}

	//���ص�ǰ�ߵ���Ϣ
	public Object getInfo() {
		return info;
	}

	//����ǰ�ߵ���Ϣ����Ϊx��������ԭ�ȵ���Ϣ
	public Object setInfo(Object x) {
		Object e = info;
		info = x;
		return e;
	}

	//ȡ��ǰ����������ͼ�ı߼�E�е�λ��
	public Position getEPosInE() {
		return ePosInE;
	}

	//ȡv[i]�ڶ��㼯V�е�λ�ã�i=0��1���ֱ��Ӧ����㡢�յ㣩
	public Position getVPosInV(int i) {
		return vPosInV[i];
	}

	//��ǰ�����������˵�Ĺ����߼�I(v[i])�е�λ��
	public Position getEPosInI(int i) {
		return ePosInI[i];
	}

	//��ȡ�����ñߵ������Ա�����
	public int getType() {
		return type;
	}

	public int setType(int t) {
		int tt = type;
		type = t;
		return tt;
	}

}
