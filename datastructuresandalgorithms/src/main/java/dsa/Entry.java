/*
 * ��Ŀ�ӿ�
 */

package dsa;

public interface Entry {

	//ȡ��Ŀ�Ĺؼ���
	public Object getKey();

	//�޸���Ŀ�Ĺؼ��룬���ش�ǰ��ŵĹؼ���
	public Object setKey(Object k);

	//ȡ��Ŀ�����ݶ���
	public Object getValue();

	//�޸���Ŀ�����ݶ��󣬷��ش�ǰ��ŵ����ݶ���
	public Object setValue(Object v);
}