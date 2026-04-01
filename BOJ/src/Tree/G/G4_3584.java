package Tree.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class G4_3584 {
	static List<Integer>G[];
	static int[] depth;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t = 0 ; t<T ; t++) {
			int N = Integer.parseInt(br.readLine()); // N개 노드
			
			// 그래프 
			G = new ArrayList[N+1];
			for(int i = 1 ; i <= N ; i++) G[i] = new ArrayList<Integer>();
			depth = new int[N+1];
			int maxK = 1;
			int K = 0;
			while(maxK<=N) {
				maxK <<=1;
				K++;
			}
			dp = new int[K+1][N+1];
			
			// N-1개 간선 
			int[] degree = new int[N+1];
			for(int i = 0 ; i < N-1 ; i++) {
				st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				G[p].add(c);
				degree[c] ++;
			}
			int root =0;
			for(int i = 1 ; i <= N ; i++) {
				if(degree[i] ==0) {
					root = i;
					break;
				}
			}
			
			
			// 1번에서 dfs로 depth , DP 초기화 채우기 
			dfs(root,-1 , 0);
			
			// 나머지 dp 테이블 완성
			for(int i = 1 ; i <= K ; i++) {
				for(int j = 1 ; j<=N ; j++) {
					dp[i][j] = dp[i-1][dp[i-1][j]];
				}
			}
			
			// 2개 노드 간의 가장 가까운 조상 비교 
			// 	1. depth를 동일하게 
			//  2. 값을 증가시키면서 가장 가까운 조상 찾도록 동시 이동
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// a의 depth가 크다고 가정 
			if(depth[a] < depth[b]) {
				int tmp = a;
				a = b;
				b = tmp;
			}
			// 높이 일치
			int diff = depth[a] - depth[b];
			for(int k=K ; k>=0 ; k--) {
				if(((diff >> k) & 1) != 0){
					a = dp[k][a];
				}
			}
			
			// 이미 부모 같은 경우는 제외
			int ans = a;
			if(a !=b) {
				// 2개 노드 순차적으로 올리기
				for(int k = K ; k>=0 ; k--) {
					if(dp[k][a] != dp[k][b]) {
						a = dp[k][a];
						b = dp[k][b];
					}
				}
				
				// 최종적으로 부모 같기 전까지 이동
				ans = dp[0][a];
			}
			
			sb.append(ans).append("\n");
			
		}
		System.out.print(sb);
	}
	
	public static void dfs(int cur, int pre , int d) {
		depth[cur] = d;
		for(int adj : G[cur]) {
			if(adj != pre) {
				dp[0][adj] = cur;
				dfs(adj , cur , d+1);
			}
		}
		
	}

}
