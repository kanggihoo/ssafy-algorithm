package Day0204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//18�� ī��� ����
//9�徿 ������ 9���� ���� ���� ����
//�� ���忡 �� �徿 �� ���� �� ����� �� ���� ���� �� ���ؼ� �������
//���� �� ���� �� ī���� �ո�ŭ ����
//���� �� �� ����� ���� X
//
//9���� ������ ���� ������ ���, ������ ���º�
//
//�̹� ���ӿ� �Կ��̰� ���� 9�� ���� �����Ȱ�� 
//�̱�� ���� ���� ��찡 �� ��� ���� �ۼ�
public class SW6808_CardGame_강기호{
	static int[] cards = new int[9];
	static int[] opp = new int[9];
	static int[] order = new int[9];
	
	static int lose;
	static int win;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			boolean[] isIn = new boolean[19];
			for(int i = 0 ; i < 9 ; i++) {
				int num = Integer.parseInt(st.nextToken());
				isIn[num] = true;
				cards[i] = num;
				
			}
			// ���� ī�� ���ϱ�
			int idx = 0;
			for(int i =1 ; i<=18 ; i++) {
				if(!isIn[i]) opp[idx++] = i;
			}
			
			lose=0;
			win=0;
			dfs(0,0);
			sb.append("#").append(t).append(" ").append(win).append(" ").append(lose).append("\n");
		}
		System.out.print(sb);

	}
	
	public static void dfs(int idx , int V) {
		if(idx==9) {
			//���� ���
			int mS=0; int oS=0;
			for(int i = 0 ; i<9 ;i++) {
				int m =cards[i];
				int o = order[i];
				if(m > o) mS+= (m+o);
				else if(m < o) oS+=(m+o);
			}
			if(mS > oS) win++;
			else if(mS < oS) lose++;
			return;
		}
		
		for(int i = 0;i<9 ; i++) {
			if((V&(1<<i))==0) {
				order[idx] = opp[i];
				dfs(idx+1 , V | (1<<i));
			}
		}
	}

}
