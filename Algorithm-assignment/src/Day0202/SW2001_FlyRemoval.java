package Day0202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW2001_FlyRemoval {
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		int T = Integer.parseInt(br.readLine());
		for(int t = 1 ;t <=T ;t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] D = new int[N+1][N+1];
			
			for(int i = 1; i <=N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j =1 ; j<=N ; j++) {
					int val = Integer.parseInt(st.nextToken()); 
					D[i][j] = val +D[i-1][j] +D[i][j-1] - D[i-1][j-1];
				}
			}
			
			
			int ans = 0;
			for(int i = M ; i<=N ; i++) {
				for(int j = M ; j <=N ; j++) {
					int curSum = D[i][j] - D[i-M][j]-D[i][j-M]+D[i-M][j-M];
					if(ans < curSum) ans = curSum;
				}
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
		

	}

}
