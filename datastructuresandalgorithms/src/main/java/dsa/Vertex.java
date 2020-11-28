/*
 * ������ͼ�Ķ���ṹ�ӿ�
 *	����ͼҲ���Կ���������ͼ��Ϊ�ˣ�ֻ�轫ÿ��������滻Ϊ�ԳƵ�һ�������
 */

package dsa;

public interface Vertex {

	//����
	final static int UNDISCOVERED = 0;//��δ�����ֵĶ���
	final static int DISCOVERED = 1;//�ѱ����ֵĶ���
	final static int VISITED = 2;//�ѷ��ʹ��Ķ���

	//���ص�ǰ�������Ϣ
	public Object getInfo();

	//����ǰ�������Ϣ����Ϊx��������ԭ�ȵ���Ϣ
	public Object setInfo(Object x);

	//���ص�ǰ����ĳ������
	public int outDeg();

	public int inDeg();

	//���ص�ǰ�������й����ߡ�������λ�õĵ�����
	public Iterator inEdges();

	public Iterator inEdgePositions();

	public Iterator outEdges();

	public Iterator outEdgePositions();

	//ȡ��ǰ������������ͼ�Ķ��㼯V�е�λ��
	public Position getVPosInV();

	//��ȡ�����ö����״̬��DFS + BFS��
	public int getStatus();

	public int setStatus(int s);

	//��ȡ�����ö����ʱ���ǩ��DFS��
	public int getDStamp();

	public int setDStamp(int s);

	public int getFStamp();

	public int setFStamp(int s);

	//��ȡ�����ö�����������̾��루BFS��BestFS��
	public int getDistance();

	public int setDistance(int s);

	//��ȡ�����ö����ڵ�DFS��BFS��BestFS��MST���еĸ���
	public Vertex getBFSParent();

	public Vertex setBFSParent(Vertex s);
}
