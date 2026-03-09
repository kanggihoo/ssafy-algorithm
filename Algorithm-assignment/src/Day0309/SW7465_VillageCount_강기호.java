package Day0309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N명의 사람 (1~N)
 * 두 사람은 서로 알 수도 있고 아닐수도 있음. 
 * 알수 이
 */

public class SW7465_VillageCount_강기호 {
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
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			P = new int[n+1];
			rank = new int[n+1];
			for(int i = 1 ; i <=n ; i++) {
				P[i] = i;
			}
			
			for(int i = 0 ; i < m ; i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				union(n1,n2);
			}
			
			// 고유한 부모 갯수 카운팅
			int ans =0;
			for(int i = 1 ; i<=n ; i++) {
				if(P[i] ==i) ans++;
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);

	}
	
	public static void union(int a , int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa!=pb) {
			if(rank[pa] > rank[pb]) {
				P[pb] = pa;
			}else if(rank[pb] > rank[pa]) {
				P[pa] = pb;
			}else {
				P[pb] = pa;
				rank[pa] +=1;
			}
		}
	}
	
	public static int find(int n) {
		if(P[n]!=n) {
			P[n] = find(P[n]);
		}
		return P[n];
	}

}
