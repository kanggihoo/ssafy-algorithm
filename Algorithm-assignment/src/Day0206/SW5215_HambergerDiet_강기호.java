package Day0206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW5215_HambergerDiet_강기호 {
	
	static int[][] Info = new int[20][2];
	static int N;
	static int L;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 재료수
			L = Integer.parseInt(st.nextToken()); // 제한 칼로리 
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				Info[i][0] = Integer.parseInt(st.nextToken()); // 점수
				Info[i][1] = Integer.parseInt(st.nextToken()); // 칼로리 
			}

			sb.append("#").append(t).append(" ").append(dfs(0,0,0)).append("\n");
		}
		System.out.print(sb);
		
	}
	
	public static int dfs(int idx , int cL , int cS) {
		if(idx == N) {
			return cS;
		}
		
		int[] h = Info[idx];
		
		int res = dfs(idx+1 , cL,cS);
		if(cL+h[1] <=L) {
			int tmp =dfs(idx+1 , cL+h[1] , cS+h[0]);
			if(res < tmp) res = tmp; 
		}
		return res;
		
	}
	

}
