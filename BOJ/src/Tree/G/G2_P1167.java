package Tree.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 트리의 지름 
public class G2_P1167 {
	static List<int[]>[]  G;
	static int ans;
	

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int V = Integer.parseInt(br.readLine());
		G = new ArrayList[V+1];
		for(int i = 1 ; i <=V ; i++) G[i] = new ArrayList<>();
		
		for(int i = 0 ; i < V ; i++) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			
			while(st.hasMoreTokens()) {
				int n2 = Integer.parseInt(st.nextToken());
				if(n2 == -1) break;
				int w = Integer.parseInt(st.nextToken());
				G[n].add(new int[] {n2,w});
			}
		}
		ans = 0;
		dfs(3,-1);
		System.out.print(ans);
		
		
	}
	
	public static int dfs(int cur , int prev) {
		
		int first = 0 ; int second = 0;
		
		for(int[] adj : G[cur]) {
			int next = adj[0]; int w = adj[1];
			if(next == prev) continue;
			
			int tmp = dfs(next , cur)+w;
			
			if(tmp > first) {
				second = first;
				first = tmp;
			}else if(tmp > second) second = tmp;
			ans = Math.max(ans, first+second);
//			System.out.printf("curNode : %d preNode : %d  tmp : %d  F : %d S : %d\n", cur , prev , tmp , first , second);
		}
		return first;
	}

}
