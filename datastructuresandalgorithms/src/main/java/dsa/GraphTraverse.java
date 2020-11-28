/*
 * ������ͼ�ı����㷨ģ��
 */

package dsa;

public abstract class GraphTraverse {
//����
	final static int UNDISCOVERED = 0;//��δ�����ֵĶ���
	final static int DISCOVERED = 1;//�ѱ����ֵĶ���
	final static int VISITED = 2;//�ѷ��ʹ��Ķ���
	final static int UNKNOWN = 0;//δ֪��
	final static int TREE = 1;//����
	final static int CROSS = 2;//����
	final static int FORWARD = 3;//ǰ����
	final static int BACKWARD = 4;//������

    //����
	protected Graph G;//ͼ

    //���췽��
    public GraphTraverse(Graph g) {
        G = g;
    }

    //��G�и�����ı�־�����ߵķ��ิλ��sΪ������㣩
	protected void reset(Vertex s) {
        for (Iterator it = G.vertices(); it.hasNext(); ) {//����
            Vertex v = (Vertex) it.getNext();//�����
            v.setStatus(UNDISCOVERED);//״̬��ΪUNDISCOVERED
            v.setDistance(Integer.MAX_VALUE);//��̾����ʼ��Ϊ�����
        }
        for (Iterator it = G.edges(); it.hasNext(); )//���б�
        {
            ((Edge) it.getNext()).setType(UNKNOWN);//��ΪUNKNOWN
        }
    }

    //���������жԶ���v�ľ�����ʲ�����ģ�壺ȡ���ڡ������ھ�����㷨algorithm()
	protected abstract Object visit(Vertex v, Object info);

    //���ڱ�������ʵ�ֵ������㷨��ģ�壺sΪ��ʼ���㣬info���㷨���ݲ����������㷨�ķ�����Ϣ
	public abstract Object algorithm(Vertex s, Object info);

    //�����㷨ģ��
	protected abstract Object traverse(Vertex v, Object info);//�Ӷ���v����������
}
