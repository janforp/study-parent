/*
 * ��������ʽBSTʵ�ֵĴʵ�ṹ
 * ����BinTree��������
 */

package dsa;

public class BSTree extends BinTree_LinkedList implements Dictionary {

    /**************************** ʵ������ ****************************/
    protected Comparator C;//�Ƚ���

    protected BinTreePosition lastV;//�������Ľڵ㣬�Ա�AVL������չ����ƽ��

    /**************************** ���췽�� ****************************/
    public BSTree() {
        this(null, new ComparatorDefault());
    }

    public BSTree(BinTreePosition r) {
        this(r, new ComparatorDefault());
    }

    public BSTree(BinTreePosition r, Comparator c) {
        root = r;
        C = c;
    }

    /**************************** �ʵ䷽�� ****************************/
    //���ʵ��д�����keyΪ�ؼ������Ŀ���򷵻����е�һ����Ŀ�����򣬷���null
    public Entry find(Object key) {
		if (isEmpty()) {
			return null;
		}
        BSTreeNode u = binSearch((BSTreeNode) root, key, C);
        return (0 == C.compare(key, u.getKey())) ? (Entry) u.getElem() : null;
    }

    //�����ɹؼ���Ϊkey����Ŀ��ɵĵ�����
    public Iterator findAll(Object key) {
        List s = new List_DLNode();
        finAllNodes((BSTreeNode) root, key, s, C);
        return s.elements();
    }

    //������Ŀ(key, value)�������ظ���Ŀ
    //lastVָʾ������Ľڵ�
    public Entry insert(Object key, Object value) {
        Entry e = new EntryDefault(key, value);//�����µ�Ԫ��

        if (isEmpty()) {//������ڵ�����
            lastV = root = new BSTreeNode(e, null, true, null, null);//�����½ڵ�
        } else {//����һ��ڵ�����
            BSTreeNode p = (BSTreeNode) root;//�Ӹ��ڵ㿪ʼ�����ҿɲ���λ��
            boolean asLeftChild;//��ʾ�½ڵ��Ƿ���Ϊp�����Ӳ���
            while (true) {//���ϵ�
                p = binSearch(p, key, C);//���ҹؼ���Ϊkey�Ľڵ㣬ֱ��
                if (C.compare(key, p.getKey()) < 0)//����ʧ���������ӽڵ㣬��
                {
                    asLeftChild = true;
                    break;
                } else if (C.compare(key, p.getKey()) > 0)//����ʧ�����Һ��ӽڵ㣬��
                {
                    asLeftChild = false;
                    break;
                } else if (!p.hasLChild())//���ҳɹ����ҿ���Ϊ���Ӳ��룬��
                {
                    asLeftChild = true;
                    break;
                } else if (!p.hasRChild())//���ҳɹ����ҿ���Ϊ�Һ��Ӳ��룬��
				{
					asLeftChild = false;
					break;
				} else//����
				{
					p = (BSTreeNode) p.getLChild();//���������м������ң���Ȼ�����������в�����ɣ�
				}
            }//���ˣ��½ڵ������Ϊp�ĺ��Ӳ��룬����ķ�����childTypeȷ��
            lastV = new BSTreeNode(e, p, asLeftChild, null, null);//�����½ڵ�
        }//else

        return e;
    }

    //���ʵ��д�����keyΪ�ؼ������Ŀ����ժ��������һ���ڵ㣬���������д�ŵ���Ŀ�����򣬷���null
    //lastVָʾ��ɾ���ڵ�ĸ���
    public Entry remove(Object key) {
		if (isEmpty()) {
			return null;//����
		}

        BinTreePosition v = binSearch((BSTreeNode) root, key, C);//����
		if (0 != C.compare(key, ((BSTreeNode) v).getKey())) {
			return null;//������ʧ�ܣ��򷵻�null
		}

        //���˲��ұسɹ���vΪ��ɾ���ڵ�
        if (v.hasLChild()) {//��v���������ǿգ���
            BinTreePosition w = v.getPrev();//��v�����������ҳ���ֱ��ǰ��w
            w.setElem(v.setElem(w.getElem()));//����v��u�����ݶ���
            v = w;//�������൱��ɾ��w
        }
        //���ˣ�v����ֻ��һ������
        //���棬ɾ��v����֮���亢��
        lastV = v.getParent();//ȡ��ɾ���ڵ�v�ĸ���
        BinTreePosition u = v.hasLChild() ? v.getLChild() : v.getRChild();//ȡv�ĺ���u
        if (null == lastV)//��vǡΪ����
        {
			if (null != u) {
				u.secede();
			}
            root = u;
        }//��u��Ϊ����
        else {//����
            if (v.isLChild())//��v��p�����ӣ���
            {
                v.secede();
                lastV.attachL(u);
            }//ժ��v����u��Ϊp������
            else//����
            {
                v.secede();
                lastV.attachR(u);
            }//ժ��v����u��Ϊp���Һ���
        }
        return (Entry) v.getElem();//���ر�ɾ���ڵ��д�ŵĵ�Ԫ��
    }

    //���شʵ���������Ŀ��һ��������
    public Iterator entries() {
        List list = new List_DLNode();
        concatenate(list, (BSTreeNode) root);
        return list.elements();
    }

    /**************************** �������� ****************************/
    //����vΪ���������в��ҹؼ���Ϊkey�Ľڵ㣨�����������Ϊ�գ�
    //  ���ҵ����򷵻ظýڵ�
    //  ���򣬷��ر����ʵ����һ���ڵ�
    //Ϊ��ȷ���Ƿ�ɹ����ϲ㷽����Ҫ�ټ��һ�η��ؽڵ�Ĺؼ���
    protected static BSTreeNode binSearch(BSTreeNode v, Object key, Comparator c) {
        BSTreeNode u = v;//��ǰ�ڵ�
        while (true) {//���ϵ�
            int comp = c.compare(key, u.getKey());//����ǰ�ڵ���Ŀ��ؼ������Ƚ�
			if (comp < 0)//��Ŀ��ؼ����С����
			{
				if (u.hasLChild())//��u������
				{
					u = (BSTreeNode) u.getLChild();//�ݹ��������������
				} else {
					return u;//��ֹ�������ӽڵ�
				}
			} else if (comp > 0)//��Ŀ��ؼ��������
			{
				if (u.hasRChild())//u���Һ���
				{
					u = (BSTreeNode) u.getRChild();//�ݹ��������������
				} else {
					return u;//��ֹ�����Һ��ӽڵ�
				}
			} else {
				return u;//��������
			}
        }
    }

    //����vΪ���ڵ�ģ��ӣ����У��ݹ���ҳ��ؼ���Ϊkey�����нڵ�
    //��Щ�ڵ㱻��֯Ϊһ���б���˿�������һ����������
    protected static void finAllNodes(BSTreeNode v, Object k, List s, Comparator c) {
		if (null == v) {
			return;//�ݹ��������
		}
        int comp = c.compare(k, v.getKey());
		if (0 >= comp) {
			finAllNodes((BSTreeNode) v.getLChild(), k, s, c);//����������
		}
		if (0 == comp) {
			s.insertLast(v);//����
		}
		if (0 <= comp) {
			finAllNodes((BSTreeNode) v.getRChild(), k, s, c);//����������
		}
    }

    //��v�����к���ڵ㣨�д�ŵ���Ŀ����֯Ϊһ���б���˿�������һ����������
    protected static void concatenate(List list, BSTreeNode v) {
		if (null == v) {
			return;
		}
        concatenate(list, (BSTreeNode) v.getLChild());
        list.insertLast(v.getElem());
        concatenate(list, (BSTreeNode) v.getRChild());
    }
}