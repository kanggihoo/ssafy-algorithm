package Day0212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class SW4012_TheChef_DFS_강기호 {

	static int[][] S = new int[16][16];
	static int N;
	static int ans;
	static int[] c1 = new int[8];
	static int[] c2 = new int[8];

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1 ; t<=T ; t++) {
			N = Integer.parseInt(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) S[i][j] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0 ; i < N-1 ; i++) {
				for(int j = i+1 ; j<N ; j++ ) S[i][j] += S[j][i];
			}
			
			ans = Integer.MAX_VALUE;
			dfs(0,0,0);
			
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
	
	public static void dfs(int idx ,int cnt, int state) {
		if(N-idx+cnt  < N/2) return ;
		if(idx == N) {
			// 계산 
			ans = Math.min(ans, calculate(state));
			return;
		}
		if(cnt < N/2) {
			dfs(idx+1 ,cnt+1, state|(1<<idx));
		}
		dfs(idx+1 ,cnt, state);
		
	}
	
	public static int calculate(int i) {
		int idx1=0;
		int idx2=0;
		for(int j = 0 ; j <N ; j++) {
			if((i&1<<j) !=0) c1[idx1++] = j;
			else c2[idx2++] = j;
		}
		
		int s1=0;
		int s2 =0;
		for(int  j = 0 ; j < N/2-1 ; j++) {
			for(int k = j+1 ; k<N/2 ; k++) {
				s1+=S[c1[j]][c1[k]];
				s2+=S[c2[j]][c2[k]];
			}
			
		}
		
		return Math.abs(s1-s2);
	}
	
	
	
	

}
