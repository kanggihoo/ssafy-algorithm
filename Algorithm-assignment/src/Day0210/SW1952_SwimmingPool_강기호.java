package Day0210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//���� 1�� ���� �� ���� �̿� ��ȹ �����ϰ� ���� ���� ������� ������ �̿��� ���
//�̿���� 4����
//1�� , 1�� , 3��(����), 1�� �̿��

//- 3�� �̿���� ���ӵ� 3���̸� 11��, 12������ 3�� �̿�� ����. 
//=> �� ��Ž�ΰ�? �׸��� ���� �ʳ�? db�� ? 
public class SW1952_SwimmingPool_강기호 {
	static int N;
	static int[] P;
	static int[] M;
	static int ans;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		P = new int[4];
		M = new int[12];
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i<4; i ++) P[i] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i <12; i++) M[i] =Integer.parseInt(st.nextToken());
			
			ans = Integer.MAX_VALUE; 
			dfs(0,0);
			// 1�� �̿� ���
			ans = Math.min(ans, P[3]);
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
	
	public static void dfs(int idx , int total) {
		if(idx >= 12) {
			ans = Math.min(ans , total);
			return;
		}
		// ����ġ��
		if(total >= ans) return;
		
		if(M[idx] == 0) {
			dfs(idx+1,total);
		}else {
			// 1��, 1��, 3�� ��ݼ���
			for(int i = 0 ; i<3; i ++) {
				if(i == 0) dfs(idx+1 , total+P[i]*M[idx]);
				else if(i==1) dfs(idx+1 , total+P[i]);
				else dfs(idx+3,total+P[i]);
			}
		}
	}

}
