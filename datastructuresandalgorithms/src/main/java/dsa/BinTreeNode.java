/*
 * ��������ڵ�ʵ�ֶ������ڵ�
 */

package dsa;

public class BinTreeNode implements BinTreePosition {

    protected Object element;//�ýڵ��д�ŵĶ���

    protected BinTreePosition parent;//����

    protected BinTreePosition lChild;//����

    protected BinTreePosition rChild;//�Һ���

    protected int size;//�����Ŀ

    protected int height;//�߶�

    protected int depth;//���

    /**************************** ���췽�� ****************************/
    public BinTreeNode() {
        this(null, null, true, null, null);
    }

    public BinTreeNode(
            Object e,//�ڵ�����
            BinTreePosition p,//���ڵ�
            boolean asLChild,//�Ƿ���Ϊ���ڵ������
            BinTreePosition l,//����
            BinTreePosition r)//�Һ���
    {
        size = 1;
        height = depth = 0;
        parent = lChild = rChild = null;//��ʼ��

        element = e;//��ŵĶ���

        //�����븸�׵Ĺ�ϵ
		if (null != p) {
			if (asLChild) {
				p.attachL(this);
			} else {
				p.attachR(this);
			}
		}

        //�����뺢�ӵĹ�ϵ
		if (null != l) {
			attachL(l);
		}
		if (null != r) {
			attachR(r);
		}
    }

    /**************************** Position�ӿڷ��� ********************************/
    //���ص�ǰ�ڵ��д�ŵĶ���
    public Object getElem() {
        return element;
    }

    //������obj���뵱ǰ�ڵ㣬�����ش�ǰ������
    public Object setElem(Object obj) {
        Object bak = element;
        element = obj;
        return bak;
    }

    /**************************** BinTreePosition�ӿڷ��� *************************/
    //�ж��Ƿ��и��ף�Ϊʹ����������ࣩ
    public boolean hasParent() {
        return null != parent;
    }

    //���ص�ǰ�ڵ�ĸ��ڵ�
    public BinTreePosition getParent() {
        return parent;
    }

    //���õ�ǰ�ڵ�ĸ��ڵ�
    public void setParent(BinTreePosition p) {
        parent = p;
    }

    //�ж��Ƿ�ΪҶ��
    public boolean isLeaf() {
        return !hasLChild() && !hasRChild();
    }

    //�ж��Ƿ�Ϊ���ӣ�Ϊʹ����������ࣩ
    //����ǰ�ڵ��и��ף����������ӣ��򷵻�true�����򣬷���false
    public boolean isLChild() {
        return (hasParent() && this == getParent().getLChild()) ? true : false;
    }

    //�ж��Ƿ������ӣ�Ϊʹ����������ࣩ
    public boolean hasLChild() {
        return null != lChild;
    }

    //���ص�ǰ�ڵ������
    public BinTreePosition getLChild() {
        return lChild;
    }

    //���õ�ǰ�ڵ�����ӣ�ע�⣺this.lChild��c.parent����һ��Ϊ�գ�
    public void setLChild(BinTreePosition c) {
        lChild = c;
    }

    //�ж��Ƿ�Ϊ�Һ��ӣ�Ϊʹ����������ࣩ
    //����ǰ�ڵ��и��ף��������Һ��ӣ��򷵻�true�����򣬷���false
    public boolean isRChild() {
        return (hasParent() && this == getParent().getRChild()) ? true : false;
    }

    //�ж��Ƿ����Һ��ӣ�Ϊʹ����������ࣩ
    public boolean hasRChild() {
        return null != rChild;
    }

    //���ص�ǰ�ڵ���Һ���
    public BinTreePosition getRChild() {
        return rChild;
    }

    //���õ�ǰ�ڵ���Һ��ӣ�ע�⣺this.rChild��c.parent����һ��Ϊ�գ�
    public void setRChild(BinTreePosition c) {
        rChild = c;
    }

    //���ص�ǰ�ڵ���Ԫ�ص���Ŀ
    public int getSize() {
        return size;
    }

    //�ں��ӷ����仯�󣬸��µ�ǰ�ڵ㼰�����ȵĹ�ģ
    public void updateSize() {
        size = 1;//��ǰ�ڵ�
		if (hasLChild()) {
			size += getLChild().getSize();//�������Ĺ�ģ
		}
		if (hasRChild()) {
			size += getRChild().getSize();//�������Ĺ�ģ
		}

		if (hasParent()) {
			getParent().updateSize();//�ݹ���¸��������ȵĹ�ģ��¼
		}
    }

    //���ص�ǰ�ڵ�ĸ߶�
    public int getHeight() {
        return height;
    }

    //�ں��ӷ����仯�󣬸��µ�ǰ�ڵ㼰�����ȵĸ߶�
    public void updateHeight() {
        height = 0;//�ȼ���û�����Һ���
		if (hasLChild()) {
			height = Math.max(height, 1 + getLChild().getHeight());//����
		}
		if (hasRChild()) {
			height = Math.max(height, 1 + getRChild().getHeight());//�Һ���
		}

		if (hasParent()) {
			getParent().updateHeight();//�ݹ���¸��������ȵĸ߶ȼ�¼
		}
    }

    //���ص�ǰ�ڵ�����
    public int getDepth() {
        return depth;
    }

    //�ڸ��׷����仯�󣬸��µ�ǰ�ڵ㼰���������
    public void updateDepth() {
        depth = hasParent() ? 1 + getParent().getDepth() : 0;//��ǰ�ڵ�

		if (hasLChild()) {
			getLChild().updateDepth();//�غ�������������£�
		}
		if (hasRChild()) {
			getRChild().updateDepth();//�ݹ�ظ������к������ȼ�¼
		}
    }

    //������������Ĵ����ҵ���ǰ�ڵ��ֱ��ǰ��
    public BinTreePosition getPrev() {
        //���������ǿգ������е�����߼�Ϊ��ǰ�ڵ��ֱ��ǰ��
		if (hasLChild()) {
			return findMaxDescendant(getLChild());
		}
        //���ˣ���ǰ�ڵ�û������
		if (isRChild()) {
			return getParent();//����ǰ�ڵ����Һ��ӣ����׼�Ϊ��ֱ��ǰ��
		}
        //���ˣ���ǰ�ڵ�û�����ӣ�����������
        BinTreePosition v = this;//�ӵ�ǰ�ڵ����
		while (v.isLChild()) {
			v = v.getParent();//��������һֱ����
		}
        //���ˣ�v����û�и��ף������Ǹ��׵��Һ���
        return v.getParent();
    }

    //������������Ĵ����ҵ���ǰ�ڵ��ֱ�Ӻ��
    public BinTreePosition getSucc() {
        //���������ǿգ������е���С�߼�Ϊ��ǰ�ڵ��ֱ�Ӻ��
		if (hasRChild()) {
			return findMinDescendant(getRChild());
		}
        //���ˣ���ǰ�ڵ�û���Һ���
		if (isLChild()) {
			return getParent();//����ǰ�ڵ������ӣ����׼�Ϊ��ֱ�Ӻ��
		}
        //���ˣ���ǰ�ڵ�û���Һ��ӣ��������Һ���
        BinTreePosition v = this;//�ӵ�ǰ�ڵ����
		while (v.isRChild()) {
			v = v.getParent();//���Һ�����һֱ����
		}
        //���ˣ�v����û�и��ף������Ǹ��׵�����
        return v.getParent();
    }

    //�Ͼ���ǰ�ڵ����丸�׵ĸ��ӹ�ϵ
    //���ص�ǰ�ڵ�
    public BinTreePosition secede() {
        if (null != parent) {
			if (isLChild()) {
				parent.setLChild(null);//�жϸ���ָ��ǰ�ڵ������
			} else {
				parent.setRChild(null);
			}

            parent.updateSize();//���µ�ǰ�ڵ㼰�����ȵĹ�ģ
            parent.updateHeight();//���µ�ǰ�ڵ㼰�����ȵĸ߶�

            parent = null;//�жϵ�ǰ�ڵ�ָ��ԭ���׵�����
            updateDepth();//���½ڵ㼰�����ڵ�����
        }

        return this;//���ص�ǰ�ڵ�
    }

    //���ڵ�c��Ϊ��ǰ�ڵ������
    public BinTreePosition attachL(BinTreePosition c) {
		if (hasLChild()) {
			getLChild().secede();//ժ����ǰ�ڵ�ԭ�ȵ�����
		}

        if (null != c) {
            c.secede();//c����ԭ����
            lChild = c;
            c.setParent(this);//ȷ���µĸ��ӹ�ϵ
            updateSize();//���µ�ǰ�ڵ㼰�����ȵĹ�ģ
            updateHeight();//���µ�ǰ�ڵ㼰�����ȵĸ߶�
            c.updateDepth();//����c�������ڵ�����
        }

        return this;
    }

    //���ڵ�c��Ϊ��ǰ�ڵ���Һ���
    public BinTreePosition attachR(BinTreePosition c) {
		if (hasRChild()) {
			getRChild().secede();//ժ����ǰ�ڵ�ԭ�ȵ��Һ���
		}

        if (null != c) {
            c.secede();//c����ԭ����
            rChild = c;
            c.setParent(this);//ȷ���µĸ��ӹ�ϵ
            updateSize();//���µ�ǰ�ڵ㼰�����ȵĹ�ģ
            updateHeight();//���µ�ǰ�ڵ㼰�����ȵĸ߶�
            c.updateDepth();//����c�������ڵ�����
        }

        return this;
    }

    //ǰ�����
    public Iterator elementsPreorder() {
        List list = new List_DLNode();
        preorder(list, this);
        return list.elements();
    }

    //�������
    public Iterator elementsInorder() {
        List list = new List_DLNode();
        inorder(list, this);
        return list.elements();
    }

    //�������
    public Iterator elementsPostorder() {
        List list = new List_DLNode();
        postorder(list, this);
        return list.elements();
    }

    //��α���
    public Iterator elementsLevelorder() {
        List list = new List_DLNode();
        levelorder(list, this);
        return list.elements();
    }

    /**************************** �������� ****************************/
    //��v�ĺ���У��ҳ���С��
    protected static BinTreePosition findMinDescendant(BinTreePosition v) {
		if (null != v) {
			while (v.hasLChild()) {
				v = v.getLChild();//��v��������������һֱ�½�
			}
		}
        //���ˣ�v����Ϊ�գ�����û������
        return v;
    }

    //��v�ĺ���У��ҳ������
    protected static BinTreePosition findMaxDescendant(BinTreePosition v) {
		if (null != v) {
			while (v.hasRChild()) {
				v = v.getRChild();//��v���������Һ�����һֱ�½�
			}
		}
        //���ˣ�v����Ϊ�գ�����û���Һ���
        return v;
    }

    //ǰ�������vΪ���ڵģ��ӣ���
    protected static void preorder(List list, BinTreePosition v) {
		if (null == v) {
			return;//�ݹ��������
		}
        list.insertLast(v);//����v
        preorder(list, v.getLChild());//����������
        preorder(list, v.getRChild());//����������
    }

    //���������vΪ���ڵģ��ӣ���
    protected static void inorder(List list, BinTreePosition v) {
		if (null == v) {
			return;//�ݹ��������
		}
        inorder(list, v.getLChild());//����������
        list.insertLast(v);//����v
        inorder(list, v.getRChild());//����������
    }

    //���������vΪ���ڵģ��ӣ���
    protected static void postorder(List list, BinTreePosition v) {
		if (null == v) {
			return;//�ݹ��������
		}
        postorder(list, v.getLChild());//����������
        postorder(list, v.getRChild());//����������
        list.insertLast(v);//����v
    }

    //��α�����vΪ���ڵģ��ӣ���
    protected static void levelorder(List list, BinTreePosition v) {
        Queue_List Q = new Queue_List();//�ն�
        Q.enqueue(v);//���ڵ����
        while (!Q.isEmpty()) {
            BinTreePosition u = (BinTreePosition) Q.dequeue();//����
            list.insertLast(u);//����v
			if (u.hasLChild()) {
				Q.enqueue(u.getLChild());
			}
			if (u.hasRChild()) {
				Q.enqueue(u.getRChild());
			}
        }
    }
}