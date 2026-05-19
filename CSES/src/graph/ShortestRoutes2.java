package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;




public class ShortestRoutes2 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		long[][] D = new long[n+1][n+1];
		
		for(int i = 1 ; i <= n ;i++) {
			for(int j = 1 ; j <=n ; j++) {
				if(i==j) {
					D[i][j] = 0L;
					D[j][i] = 0L;
				}
				else {
					D[i][j] = Long.MAX_VALUE;
				}
				
			}
		}
		
		for(int i = 0 ; i < m ; i++) {
			st= new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			long w = Long.parseLong(st.nextToken());
			long v = Math.min(D[n1][n2],w);
			D[n1][n2] =v;
			D[n2][n1] = v;
		}

		
		// 최단거리 플로이드 워샬
		for(int k = 1 ; k <=n ; k++) {
			for(int i = 1 ; i <= n ; i++) {
				for(int j = 1 ; j<=n ; j++) {
					if(D[i][k] != Long.MAX_VALUE && D[k][j] !=Long.MAX_VALUE && D[i][j] > D[i][k] + D[k][j]) {
						D[i][j] = D[i][k] + D[k][j];
					}
				}
			}
		}
		
		// m개 질의
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < q ; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			long ans = D[n1][n2] != Long.MAX_VALUE ? D[n1][n2] : -1L;
			sb.append(ans).append("\n");
		}
		System.out.print(sb);
	}

}
