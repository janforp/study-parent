package dsa;/*
 * ��ģʽƥ�䣺Boyer-Moore�㷨
 * ������λ��i > length(T) - length(P)����˵��ʧ��
 * ����iΪƥ��λ��
 */

public class PM_BM {

	final static int CARD_CHAR_SET = 256;//�ַ�����ģ

	//////////////////////////////////////////////////////////////////////////
	// T:	0		1		.		.		.		i		i+1	.		.		.		i+j	.		.		n-1
	//		--------------------|-------------------|------------
	// P:											0		1		.		.		.		j		.		.
	//												|-------------------|
	//////////////////////////////////////////////////////////////////////////
	public static int PM(String T, String P) {
		//Ԥ����
		int[] BC = BuildBC(P);
		int[] GS = BuildGS(P);

		//����ƥ��
		int i = 0;//ģʽ���������������ʼλ�ã���ʼʱ����������룩
		while (T.length() - P.length() >= i) {//�ڵ������Ҷ�ǰ����������ģʽ��
			int j = P.length() - 1;//��ģʽ����ĩβ���ַ���ʼ
			while (P.charAt(j) == T.charAt(i + j))//��������Ƚ�
			{
				if (0 > --j) {
					break;
				}
			}
			ShowProgress(T, P, i, j);
			System.out.print("\n");
			if (0 > j)//������ƥ���׺ == ����ģʽ����˵���Ѿ���ȫƥ�䣩
			{
				break;//����ƥ��λ��
			} else//����
			{
				i += MAX(GS[j], j - BC[T.charAt(i + j)]);//��λ����BC��GS֮��ѡ����ߣ���Ӧ���ƶ�ģʽ��
			}
		}
		return (i);
	}

	/*
	 *	����Bad Charactor Shift��BC[]
	 *
	 *		0												BC['X']																	m-1
	 *		|												|																				|
	 *		........................X****************************************
	 *														|<------------ �����ַ�'X' ------------>|
	 *
	 * ���Ӷ� = O(m + CARD_CHAR_SET)
	 ************************************************************************/
	protected static int[] BuildBC(String P) {
		//��ʼ��
		int[] BC = new int[CARD_CHAR_SET];//BC[]��
		int j;
		for (j = 0; j < CARD_CHAR_SET; j++) {
			BC[j] = -1;//���ȼ�����ַ�û����P�г���
		}

		//�������ҵ��������¸��ַ���BC[]ֵ
		for (j = 0; j < P.length(); j++) {
			BC[P.charAt(j)] = j;//P[j]��������λ��j�������������ɨ������Ǵ����ң����±����������ֻҪĳ���ַ�ch��P�г��ֹ���BC[ch]�ͻ��¼�����е���ҵĳ���λ��
		}
		System.out.println("-- BC[] Table ---------------");
		for (j = 0; j < CARD_CHAR_SET; j++) {
			if (0 <= BC[j]) {
				System.out.print("\t" + (char) j);
			}
		}
		System.out.println();
		for (j = 0; j < CARD_CHAR_SET; j++) {
			if (0 <= BC[j]) {
				System.out.print("\t" + BC[j]);
			}
		}
		System.out.println("\n");
		return (BC);
	}

	/*
	 * ����P�ĸ�ǰ׺��P�ĸ���׺�����ƥ�䳤��
	 * ����P��ÿһǰ׺P[0..j]��SS[j] = max{s | P[j-s+1..j] = P[m-s..m-1]}
	 *
	 *		0												m-SS[j]									m-1
	 *		|												|												|
	 *		........................*************************
	 *														|												|
	 *														<-------- SS[j] -------->
	 *														|												|
	 *								............*************************..................
	 *								|						|												|									|
	 *								0						j-SS[j]+1								j									m-1
	 *
	 * ���Ӷ� = O(m)
	 ************************************************************************/
	protected static int[] ComputeSuffixSize(String P) {
		int m = P.length();
		int[] SS = new int[m];//Suffix Size Table
		int s, t;//�Ӵ�P[s+1, ..., t]���׺P[m+s-t, ..., m-1]ƥ��
		int j;//��ǰ�ַ���λ��

		//	�����һ���ַ����ԣ���֮ƥ������׺��������P������...
		SS[m - 1] = m;

		//	�ӵ����ڶ����ַ�����������ɨ��P�����μ����SS[]�������
		s = m - 1;
		t = m - 2;
		for (j = m - 2; j >= 0; j--) {
			if ((j > s) && (j - s > SS[(m - 1 - t) + j])) {
				SS[j] = SS[(m - 1 - t) + j];
			} else {
				t = j;//���׺ƥ��֮�Ӵ����յ㣬���ǵ�ǰ�ַ�
				s = MIN(s, j);//���׺ƥ��֮�Ӵ������
				while ((0 <= s) && (P.charAt(s) == P.charAt((m - 1 - t) + s))) {
					s--;//�ƺ��Ƕ���ѭ�����ѵ����Ӷ���ƽ��������
				}
				SS[j] = t - s;//���׺ƥ��֮��Ӵ��ĳ���
			}
		}
		System.out.println("-- SS[] Table -------");
		for (j = 0; j < m; j++) {
			System.out.print("\t" + P.charAt(j));
		}
		System.out.println();
		for (j = 0; j < m; j++) {
			System.out.print("\t" + SS[j]);
		}
		System.out.println("\n");
		return (SS);
	}

	/*
	 * ����Good Suffix Shift��GS[]
	 * ���Ӷ� = O(m)
	 ************************************************************************/
	protected static int[] BuildGS(String P) {
		int m = P.length();
		int[] SS = ComputeSuffixSize(P);//������ַ���Ӧ���ƥ���׺����

		int[] GS = new int[m];//Good Suffix Index
		int j;
		for (j = 0; j < m; j++) {
			GS[j] = m;
		}

		/*
		 *								i < m-j-1��ʧ��λ�ã�
		 *								|
		 *		0						|					m-j-1									m-1
		 *		|						|					|											|
		 *		............A#########***********************
		 *								|					|											|
		 *								|					<---- Suffix Size ----><------ GS Shift ------>
		 *								|					<---- SS[j] = j+1 ----><-------- m-j-1 ------->
		 *								|					|											|												|
		 *													***********************........................
		 *													|											|												|
		 *													0											j												m-1
		 *													<--<--<--<--<--<--< ������ɨ�� <--<--<--<--<--<
		 *
		 ************************************************************************/
		int i = 0;
		for (j = m - 1; j >= -1; j--)//���ʣ����������������ң�ɨ�������Ϊʲô��
		{
			if (-1 == j || j + 1 == SS[j])//������SS[-1] = 0�����ͳһΪ��if (j+1 == SS[j])
			{
				for (; i < m - j - 1; i++)//�ƺ��Ƕ���ѭ�����ѵ����Ӷ���ƽ��������
				{
					if (GS[i] == m) {
						GS[i] = m - j - 1;
					}
				}
			}
		}

		/*
		 *								  m-SS[j]-1��ʧ��λ�ã�
		 *								  |
		 *		0						  |m-SS[j]					m-1
		 *		|						  ||							|
		 *		....................A**********************
		 *								  ||							|
		 *								  |<--- Suffix Size ----><-- GS Shift ->
		 *								  |<------- SS[j] ------><--- m-j-1 --->
		 *								  ||							|					|
		 *							.....B**********************...............
		 *							|	   |							|					|
		 *							0		j-SS[j]+1				j					m-1
		 * 						>-->-->-->-->--> ��������ɨ�� --->-->-->-->
		 *
		 ************************************************************************/
		for (j = 0; j < m - 1; j++)//���ʣ�����������������ɨ�������Ϊʲô��
		{
			GS[m - SS[j] - 1] = m - j - 1;
		}

		System.out.println("-- GS[] Table ---------------");
		for (j = 0; j < m; j++) {
			System.out.print("\t" + P.charAt(j));
		}
		System.out.println();
		for (j = 0; j < m; j++) {
			System.out.print("\t" + GS[j]);
		}
		System.out.println("\n");
		return (GS);
	}

	protected static void ShowProgress(//��̬��ʾƥ���չ
			String T,//����
			String P,//ģʽ��
			int i,//ģʽ���������������ʼλ��
			int j)//ģʽ���ĵ�ǰ�ַ�
	{
		int t;

		System.out.println("-------------------------------------------");
		for (t = 0; t < T.length(); t++) {
			System.out.print("\t" + T.charAt(t));
		}
		System.out.print("\n");

		if (0 <= i + j) {
			for (t = 0; t < i + j; t++) {
				System.out.print("\t");
			}
			System.out.print("\t|");
		}
		System.out.println();

		for (t = 0; t < i; t++) {
			System.out.print("\t");
		}
		for (t = 0; t < P.length(); t++) {
			System.out.print("\t" + P.charAt(t));
		}
		System.out.print("\n");
		System.out.println();
	}

	protected static int MAX(int a, int b) {
		return (a > b) ? a : b;
	}

	protected static int MIN(int a, int b) {
		return (a < b) ? a : b;
	}
}
