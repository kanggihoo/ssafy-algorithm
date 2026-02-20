package Day0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW8275_Hamster_강기호{

	static int N;
	static int X;
	static int[] histAcsum;
	static int[][] A;
	static int ans_sum;
	static int[] ans;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // �츮����
			X = Integer.parseInt(st.nextToken()); // �� �츮 �ִ� �����ܽ���
			histAcsum = new int[N+1];
			ans_sum = -1;
			
			int M = Integer.parseInt(st.nextToken()); // M�� ���
			
			A = new int[M][3]; // [l,r,s]
			for(int i =0 ; i < M ; i++) {
				st = new StringTokenizer(br.readLine());
				A[i][0] = Integer.parseInt(st.nextToken())-1;
				A[i][1] = Integer.parseInt(st.nextToken())-1;
				A[i][2] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0);
			sb.append("#").append(t).append(" ");
			if(ans_sum == -1) {
				sb.append(-1);
			}else {
				for(int i = 1 ;i <=N ; i++) sb.append(ans[i]-ans[i-1]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);

	}
	
	public static void dfs(int idx) {
		if(idx == N) {
			// ���� �� 
			if(!check()) return;
			// ũ�� ��

			if(histAcsum[N] < ans_sum) return;
			
			if(histAcsum[N] > ans_sum) {
				ans = histAcsum.clone();
				ans_sum = histAcsum[N];
			}
			return ;
			
		}			
		for(int num =0 ; num<=X ; num++) {
			histAcsum[idx+1] = histAcsum[idx]+ num;
			dfs(idx+1);
		}
	}
	
	public static boolean check() {
		
		for(int[] a : A) {
			if(histAcsum[a[1]+1] - histAcsum[a[0]] != a[2]) return false;
		}
		return true;
	}

}
