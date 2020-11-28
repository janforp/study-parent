/*
 * ������ͼ����BFS����̾����㷨
 */

package dsa;

public class BFSDistance extends BFS {

	//���췽��
	public BFSDistance(Graph g) {
		super(g);
	}

	//������ʲ������ڱ��㷨�У�info�Ƕ���v��ǰ��
	protected Object visit(Vertex v, Object info) {
		if (null == info)//vΪBFS����ʼ����
		{
			v.setDistance(0);
		} else {
			v.setDistance(((Vertex) info).getDistance() + 1);//����v��s�ľ��� = ǰ���ľ���+1
		}
		return null;
	}

	//����BFSʵ�ֵ���̾����㷨��sΪ��ʼ���㣬info���㷨���ݲ���
	public Object algorithm(Vertex s, Object info) {
		reset(s);
		traverse(s, info);//BFS����������̾����¼�ڸ������distance����
		return null;
	}
}
