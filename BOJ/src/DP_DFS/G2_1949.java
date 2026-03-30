package DP_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.util.*;
public class G2_1949 {

	static int[] people;
	static List<Integer>[] G;
	static int[][]DP;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		people = new int[N+1];
		G = new ArrayList[N+1];
		DP = new int[N+1][2];
		st = new StringTokenizer(br.readLine());
		for(int i =1 ; i <= N ; i++) {
			people[i] = Integer.parseInt(st.nextToken());
			G[i] = new ArrayList<>();
			for(int j = 0 ; j<2 ; j++) DP[i][j] = -1;
		}
		
		
		
		for(int i = 0 ; i < N-1 ; i++) {
			st = new StringTokenizer(br.readLine());
			int c1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			G[c1].add(c2);
			G[c2].add(c1);
		}
		
		int res1 = dfs(1,0,0); // 시작노드는 우수 마을 X
		int res2 = dfs(1,1,0); // 시작노드는 우수 마을 
		System.out.print(Math.max(res1, res2));
		
	}
	
	public static int dfs(int cur , int state , int pre) {
		if(DP[cur][state] !=-1) return DP[cur][state];
		
		int ans = state == 1 ? people[cur] : 0;
		for(int adj : G[cur]) {
			if(adj == pre) continue;
			if(state == 0) { // 현재 노드 우수마을 아닌경우 
				ans += Math.max(dfs(adj,1 , cur), dfs(adj,0 , cur));
			}else {
				ans += dfs(adj , 0,cur); // 현재 노드를 우수 마을로 , 인접노드는 무조건 우수마을 X
			}
		}
		return DP[cur][state] = ans;
	}
}
