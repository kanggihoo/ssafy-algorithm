package Tree.P;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import sun.util.locale.StringTokenIterator;
public class P5_11438 {
	
	static List<Integer>[] G;
	static int[][] dp;
	static int[] depth;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		G = new ArrayList[N+1];
		for(int i =1 ; i <= N ; i++)G[i] = new ArrayList<Integer>();
		// N-1개 노드 (루트가 1) 
		for(int i = 0 ; i < N-1 ; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			G[n1].add(n2);
			G[n2].add(n1);
		}
		
		// 조상 및 dp 초기화
		int maxV = 1;
		int K = 1;
		while(maxV <= N) {
			maxV <<= 1;
			K++;
		}
		
		dp = new int[K][N+1];
		depth = new int[N+1];
		dfs(1,0,0);
		
		// dp 초기화 k=1부터 
		for(int k = 1; k<K ; k++) {
			for(int i = 1 ; i <=N ; i++) {
				dp[k][i] = dp[k-1][dp[k-1][i]];
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		// M개의 최소 조상 확인
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine()); 
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			// 항상 n1의 높이가 크게  
			if(depth[n1] < depth[n2]) {
				int tmp = n1;
				n1 = n2;
				n2 = tmp;
			}
			
			// 두 노드 높이 동일하게 만들기 
			int diff = depth[n1] - depth[n2]; // diff 만큼 움직여야함. 
			for(int k = K-1 ; k>=0 ; k--) {
				if(((diff >> k) & 1) != 0){
					n1 = dp[k][n1];
				}
			}
			
			int ans = n1;
			if(n1 != n2) { // 높이 같은데 조상 다른 경우 적절하게 위로 이동 
				for(int k = K-1 ; k>=0 ; k--) {
					if(dp[k][n1] != dp[k][n2]) {
						n1 = dp[k][n1];
						n2 = dp[k][n2];
					}
				}
				// 최종 조상 구하기
				ans = dp[0][n1];
			}
			sb.append(ans).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static void dfs(int cur , int pre , int d) {
		dp[0][cur] = pre;
		depth[cur] = d;
		
		for(int adj : G[cur]) {
			if(adj != pre) {
				dfs(adj , cur , d+1);
			}
		}
	}

}
