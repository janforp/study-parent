/*
 * ������ͼ��������ȱ����㷨ģ��
 */

package dsa;

public abstract class DFS extends GraphTraverse {

	//����
	protected static int clock = 0;//����������ʹ�õļ�ʱ��

	//���췽��
	public DFS(Graph g) {
		super(g);
	}

	//������ȱ����㷨
	protected Object traverse(Vertex v, Object info) {//�Ӷ���v��������������Ȳ���
		if (UNDISCOVERED != v.getStatus()) {
			return null;//�����ѷ��ʹ��Ķ��㣨��Է���ͨͼ��
		}
		v.setDStamp(clock++);
		v.setStatus(DISCOVERED);
		visit(v, info);//���ʵ�ǰ����
		for (Iterator it = v.outEdges(); it.hasNext(); ) {//����붥��v
			Edge e = (Edge) it.getNext();//ͨ����e = (v, u)
			Vertex u = (Vertex) e.getVPosInV(1).getElem();//������ÿһ����u
			switch (u.getStatus()) {//����u��ǰ�Ĳ�ͬ״̬���ֱ�����Ӧ����
				case UNDISCOVERED://��u��δ�����֣���
					e.setType(TREE);//e������Ϊ�����ߡ�
					traverse(u, info);//��u������������������Ȳ���
					break;
				case DISCOVERED://��u�Ѿ������֣������������δ��������
					e.setType(BACKWARD);//��e����Ϊ�������ߡ�
					break;
				default://VISITED������u�ķ����Ѿ�����
					if (u.getDStamp() < v.getDStamp())//�������v��u�����ֵø��磬��
					{
						e.setType(CROSS);//��e����Ϊ�����ߡ�
					} else//����
					{
						e.setType(FORWARD);//��e����Ϊ��ǰ���ߡ�
					}
					break;
			}
		}//���ˣ�v�������ھӶ��ѷ��ʽ�������
		v.setFStamp(clock++);
		v.setStatus(VISITED);//��v���ΪVISITED
		return null;//Ȼ�����
	}
}
