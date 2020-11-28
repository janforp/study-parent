/*
 * ������ͼ����DFSģ��Ŀɴ�����㷨
 */

package dsa;

public class DFSReachableComponent extends DFS {

	//���췽��
	public DFSReachableComponent(Graph g) {
		super(g);
	}

	//DFS�����жԶ���v�ľ�����ʲ�����infoʵ������һ��ջ����¼���пɴ�Ķ��㣩
	protected Object visit(Vertex v, Object info) {
		((Stack) info).push(v);
		return null;
	}

	//����DFS�Ŀɴ����㷨��sΪ��ʼ���㣨infoʵ������һ��ջ����¼���пɴ�Ķ��㣩
	public Object algorithm(Vertex s, Object info) {
		reset(s);
		Stack S = new Stack_Array();//�������ʼ����ɴ�Ķ���
		traverse(s, info);//DFS
		return null;
	}
}
