/*
 * ������ͼ�ı߽ṹ�ӿ�
 *	����ͼҲ���Կ���������ͼ��Ϊ�ˣ�ֻ�轫ÿ��������滻Ϊ�ԳƵ�һ�������
 */

package dsa;

public interface Edge {

	//����
	final static int UNKNOWN = 0;//δ֪��
	final static int TREE = 1;//����
	final static int CROSS = 2;//����
	final static int FORWARD = 3;//ǰ����
	final static int BACKWARD = 4;//������

	//���ص�ǰ�ߵ���Ϣ�����ڴ�Ȩͼ��Ҳ���Ǹ��ߵ�Ȩ�أ�
	public Object getInfo();

	//����ǰ�ߵ���Ϣ����Ϊx��������ԭ�ȵ���Ϣ
	public Object setInfo(Object x);

	//ȡ��ǰ����������ͼ�ı߼�E�е�λ��
	public Position getEPosInE();

	//ȡv[i]�ڶ��㼯V�е�λ�ã�i=0��1���ֱ��Ӧ����㡢�յ㣩
	public Position getVPosInV(int i);

	//��ǰ�����������˵�Ĺ����߼�I(v[i])�е�λ��
	public Position getEPosInI(int i);

	//��ȡ�����ñߵ������Ա�����
	public int getType();

	public int setType(int t);
}
