package Day0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SW3124_MST_강기호 {
	
	static int[] P;
	static int[] rank; 

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T =Integer.parseInt(br.readLine());
		
		for(int t=1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			P = new int[V+1];
			rank = new int[V+1];
			for(int i = 1 ; i<=V;i++) P[i] = i;
			List<int[]> edges = new ArrayList<int[]>();
			
			for(int i = 1 ; i <=E ; i++) {
				st = new StringTokenizer(br.readLine());
				int e1 = Integer.parseInt(st.nextToken());
				int e2 = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				edges.add(new int[] {e1,e2,w});
			}
			
			edges.sort((e1 , e2)->Integer.compare(e1[2], e2[2]));
			int eCnt = 0;
			long ans = 0;
			for(int[] edge : edges) {
				if(eCnt == V-1)break;
				int e1 = edge[0];
				int e2 = edge[1];
				if(union(e1, e2)) {
					eCnt++;
					ans += edge[2];
				}
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);

	}
	
	public static int find(int a) {
		if(P[a] != a) {
			P[a] = find(P[a]);
		}
		return P[a];
	}
	
	public static boolean union(int n1 ,int n2) {
		int p1 = find(n1);
		int p2 = find(n2);
		if(p1!= p2) {
			if(rank[p1] > rank[p2]) {
				P[p2] = p1;
			}else if(rank[p1] < rank[p2]) {
				P[p1] = p2;
			}else {
				P[p2] = p1;
				rank[p1]+=1;
			}
			return true;
		}
		return false;
	}

}
