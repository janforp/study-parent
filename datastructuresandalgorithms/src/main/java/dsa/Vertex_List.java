/*
 * �����ڽӱ߱�ʵ��ͼ�Ķ���ṹ
 */

package dsa;

public class Vertex_List implements Vertex {
//����
	protected Object info;//��ǰ�����д�ŵ�����Ԫ��
	protected Position vPosInV;//��ǰ������������ͼ�Ķ����V�е�λ��
	protected List outEdges;//�����߱�����Ե�ǰ����Ϊβ�����бߣ���λ�ã�
	protected List inEdges;//�����߱�����Ե�ǰ����Ϊͷ�����бߣ���λ�ã�
	protected int status;//���ڱ���ͼ�Ȳ��������У������״̬
	protected int dStamp;//ʱ���ǩ��DFS�����иö��㱻����ʱ��ʱ��
	protected int fStamp;//ʱ���ǩ��DFS�����иö��㱻���ʽ���ʱ��ʱ��
	protected int distance;//��ָ�����ľ��룺BFS��Dijkstra���㷨��ȷ���ö��㵽���ľ���
	protected Vertex bfsParent;//����̾�������BFS��BestFS���еĸ���

	//���췽������ͼG������һ������Ϊx���¶���
	public Vertex_List(Graph G, Object x) {
		info = x;//����Ԫ��
		vPosInV = G.insert(this);//��ǰ������������ͼ�Ķ����V�е�λ��
		outEdges = new List_DLNode();//���߱�
		inEdges = new List_DLNode();//��߱�
		status = UNDISCOVERED;
		dStamp = fStamp = Integer.MAX_VALUE;
		distance = Integer.MAX_VALUE;
		bfsParent = null;
	}

	//���ص�ǰ�������Ϣ
	public Object getInfo() {
		return info;
	}

	//����ǰ�������Ϣ����Ϊx��������ԭ�ȵ���Ϣ
	public Object setInfo(Object x) {
		Object e = info;
		info = x;
		return e;
	}

	//���ص�ǰ����ĳ������
	public int outDeg() {
		return outEdges.getSize();
	}

	public int inDeg() {
		return inEdges.getSize();
	}

	//���ص�ǰ�������й����ߡ�������λ�õĵ�����
	public Iterator inEdges() {
		return inEdges.elements();
	}

	public Iterator inEdgePositions() {
		return inEdges.positions();
	}

	public Iterator outEdges() {
		return outEdges.elements();
	}

	public Iterator outEdgePositions() {
		return outEdges.positions();
	}

	//ȡ��ǰ������������ͼ�Ķ��㼯V�е�λ��
	public Position getVPosInV() {
		return vPosInV;
	}

	//��ȡ�����ö����״̬��DFS + BFS��
	public int getStatus() {
		return status;
	}

	public int setStatus(int s) {
		int ss = status;
		status = s;
		return ss;
	}

	//��ȡ�����ö����ʱ���ǩ��DFS��
	public int getDStamp() {
		return dStamp;
	}

	public int setDStamp(int s) {
		int ss = dStamp;
		dStamp = s;
		return ss;
	}

	public int getFStamp() {
		return fStamp;
	}

	public int setFStamp(int s) {
		int ss = fStamp;
		fStamp = s;
		return ss;
	}

	//��ȡ�����ö�����������̾��루BFS��
	public int getDistance() {
		return distance;
	}

	public int setDistance(int s) {
		int ss = distance;
		distance = s;
		return ss;
	}

	//��ȡ�����ö����ڵ�DFS��BFS��BestFS��MST���еĸ���
	public Vertex getBFSParent() {
		return bfsParent;
	}

	public Vertex setBFSParent(Vertex s) {
		Vertex ss = bfsParent;
		bfsParent = s;
		return ss;
	}
}
