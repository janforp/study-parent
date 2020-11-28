/*
 * ��չ��
 * ����BSTree������
 */

package dsa;

public class SplayTree extends BSTree implements Dictionary {

    /**************************** ���췽�� ****************************/
    public SplayTree() {
        super();
    }

    public SplayTree(BinTreePosition r) {
        super(r);
    }

    public SplayTree(BinTreePosition r, Comparator c) {
        super(r, c);
    }

    /**************************** �ʵ䷽�������Ǹ���BSTree�� ****************************/
    //���ʵ��д�����keyΪ�ؼ������Ŀ���򷵻����е�һ����Ŀ�����򣬷���null
    public Entry find(Object key) {
		if (isEmpty()) {
			return null;
		}
        BSTreeNode u = binSearch((BSTreeNode) root, key, C);
        root = moveToRoot(u);
        return (0 == C.compare(key, u.getKey())) ? (Entry) u.getElem() : null;
    }

    //������Ŀ(key, value)�������ظ���Ŀ
    public Entry insert(Object key, Object value) {
        Entry e = super.insert(key, value);//���ø��෽����ɲ���
        root = moveToRoot(lastV);//����ƽ�⻯
        return e;
    }

    //���ʵ��д�����keyΪ�ؼ������Ŀ����ժ�����е�һ�������أ����򣬷���null
    public Entry remove(Object key) {
        Entry e = super.remove(key);//���ø��෽�����ɾ��
		if (null != e && null != lastV) {
			root = moveToRoot(lastV);//����ƽ�⻯
		}
        return e;
    }

    /**************************** �������� ****************************/
    //�ӽڵ�z��ʼ�����϶�������ƽ�⻯
    protected static BinTreePosition moveToRoot(BinTreePosition z) {
		while (z.hasParent()) {
			if (!z.getParent().hasParent()) {
				if (z.isLChild()) {
					z = zig(z);
				} else {
					z = zag(z);
				}
			} else if (z.isLChild()) {
				if (z.getParent().isLChild()) {
					z = zigzig(z);
				} else {
					z = zigzag(z);
				}
			} else if (z.getParent().isLChild()) {
				z = zagzig(z);
			} else {
				z = zagzag(z);
			}
		}
        return z;
    }

    //vΪ����
    //˳ʱ����תv��ʹ֮����һ�㣨��չ����
    //�����µ�������
    protected static BinTreePosition zig(BinTreePosition v) {
        if (null != v && v.isLChild()) {//v�����и��ף����ұ���������
            BinTreePosition p = v.getParent();//����
            BinTreePosition g = p.getParent();//�游
            boolean asLChild = p.isLChild();//�����Ƿ��游������
            v.secede();//ժ��v������p������Ϊ�գ�
            BinTreePosition c = v.getRChild();//��v���Һ���
			if (null != c) {
				p.attachL(c.secede());//��Ϊp������
			}
            p.secede();//ժ������
            v.attachR(p);//��p��Ϊv���Һ���
			if (null != g)//���游���ڣ���v��Ϊ�亢��
			{
				if (asLChild) {
					g.attachL(v);
				} else {
					g.attachR(v);
				}
			}
        }
        return v;
    }

    //vΪ�Һ���
    //��ʱ����תv��ʹ֮����һ�㣨��չ����
    //�����µ�������
    protected static BinTreePosition zag(BinTreePosition v) {
        if (null != v && v.isRChild()) {//v�����и��ף����ұ������Һ���
            BinTreePosition p = v.getParent();//����
            BinTreePosition g = p.getParent();//�游
            boolean asLChild = p.isLChild();//�����Ƿ��游������
            v.secede();//ժ��v������p������Ϊ�գ�
            BinTreePosition c = v.getLChild();//��v������
			if (null != c) {
				p.attachR(c.secede());//��Ϊp���Һ���
			}
            p.secede();//ժ������
            v.attachL(p);//��p��Ϊv������
			if (null != g)//���游���ڣ���v��Ϊ�亢��
			{
				if (asLChild) {
					g.attachL(v);
				} else {
					g.attachR(v);
				}
			}
        }
        return v;
    }

    //vΪ���ӣ�����Ϊ����
    //˳ʱ����תv��ʹ֮�������㣨��չ����
    //�����µ�������
    protected static BinTreePosition zigzig(BinTreePosition v) {
        if (null != v && v.isLChild() && v.hasParent() && v.getParent().isLChild()) {
            BinTreePosition p = v.getParent();//����
            BinTreePosition g = p.getParent();//�游
            BinTreePosition s = g.getParent();//���游
            boolean asLChild = g.isLChild();//�游�Ƿ����游������
            g.secede();
            p.secede();
            v.secede();
            BinTreePosition c;//��ʱ�������������ӵ��ƶ�
            c = p.getRChild();
			if (null != c) {
				g.attachL(c.secede());//p���Һ�����Ϊg������
			}
            c = v.getRChild();
			if (null != c) {
				p.attachL(c.secede());//v���Һ�����Ϊp������
			}
            p.attachR(g);//g��Ϊp���Һ���
            v.attachR(p);//p��Ϊv���Һ���
			if (null != s)//�����游���ڣ���v��Ϊ�亢��
			{
				if (asLChild) {
					s.attachL(v);
				} else {
					s.attachR(v);
				}
			}
        }
        return v;
    }

    //vΪ�Һ��ӣ�����Ϊ�Һ���
    //˳ʱ����תv��ʹ֮�������㣨��չ����
    //�����µ�������
    protected static BinTreePosition zagzag(BinTreePosition v) {
        if (null != v && v.isRChild() && v.hasParent() && v.getParent().isRChild()) {
            BinTreePosition p = v.getParent();//����
            BinTreePosition g = p.getParent();//�游
            BinTreePosition s = g.getParent();//���游
            boolean asLChild = g.isLChild();//�游�Ƿ����游������
            g.secede();
            p.secede();
            v.secede();
            BinTreePosition c;//��ʱ�������������ӵ��ƶ�
            c = p.getLChild();
			if (null != c) {
				g.attachR(c.secede());//p��������Ϊg���Һ���
			}
            c = v.getLChild();
			if (null != c) {
				p.attachR(c.secede());//v��������Ϊp���Һ���
			}
            p.attachL(g);//g��Ϊp������
            v.attachL(p);//p��Ϊv������
			if (null != s)//�����游���ڣ���v��Ϊ�亢��
			{
				if (asLChild) {
					s.attachL(v);
				} else {
					s.attachR(v);
				}
			}
        }
        return v;
    }

    //vΪ���ӣ�����Ϊ�Һ���
    //˳ʱ����תv��ʹ֮�������㣨��չ����
    //�����µ�������
    protected static BinTreePosition zigzag(BinTreePosition v) {
        if (null != v && v.isLChild() && v.hasParent() && v.getParent().isRChild()) {
            BinTreePosition p = v.getParent();//����
            BinTreePosition g = p.getParent();//�游
            BinTreePosition s = g.getParent();//���游
            boolean asLChild = g.isLChild();//�游�Ƿ����游������
            g.secede();
            p.secede();
            v.secede();
            BinTreePosition c;//��ʱ�������������ӵ��ƶ�
            c = v.getLChild();
			if (null != c) {
				g.attachR(c.secede());//v��������Ϊg���Һ���
			}
            c = v.getRChild();
			if (null != c) {
				p.attachL(c.secede());//v���Һ�����Ϊp������
			}
            v.attachL(g);//g��Ϊv������
            v.attachR(p);//p��Ϊv���Һ���
			if (null != s)//�����游���ڣ���v��Ϊ�亢��
			{
				if (asLChild) {
					s.attachL(v);
				} else {
					s.attachR(v);
				}
			}
        }
        return v;
    }

    //vΪ�Һ��ӣ�����Ϊ����
    //˳ʱ����תv��ʹ֮�������㣨��չ����
    //�����µ�������
    protected static BinTreePosition zagzig(BinTreePosition v) {
        if (null != v && v.isRChild() && v.hasParent() && v.getParent().isLChild()) {
            BinTreePosition p = v.getParent();//����
            BinTreePosition g = p.getParent();//�游
            BinTreePosition s = g.getParent();//���游
            boolean asLChild = g.isLChild();//�游�Ƿ����游������
            g.secede();
            p.secede();
            v.secede();
            BinTreePosition c;//��ʱ�������������ӵ��ƶ�
            c = v.getRChild();
			if (null != c) {
				g.attachL(c.secede());//v���Һ�����Ϊg������
			}
            c = v.getLChild();
			if (null != c) {
				p.attachR(c.secede());//v��������Ϊp���Һ���
			}
            v.attachR(g);//g��Ϊv���Һ���
            v.attachL(p);//p��Ϊv������
			if (null != s)//�����游���ڣ���v��Ϊ�亢��
			{
				if (asLChild) {
					s.attachL(v);
				} else {
					s.attachR(v);
				}
			}
        }
        return v;
    }
}