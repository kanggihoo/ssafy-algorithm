package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge{
	int adj;
	int w;
	
	public Edge(int a , int w) {
		this.adj = a;
		this.w = w;
	}
}

class Info implements Comparable<Info>{
	int idx;
	long d;
	
	public Info(int k , long d) {
		this.idx = k ;
		this.d = d;
	}
	
	@Override
	public int compareTo(Info o) {
		// TODO Auto-generated method stub
		return Long.compare(this.d, o.d);
	}
	
	
	
}

public class FlightRoutes {
	
	static List<Edge>G[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		G = new List[n+1];
		for(int i = 1 ; i <=n ; i++) G[i] = new ArrayList<>();
		
		for(int i = 0 ; i < m ; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			G[n1].add(new Edge(n2,w));
		}
		
		PriorityQueue<Long>[] D = new PriorityQueue[n+1];
		for(int i = 1 ; i <=n ; i++) D[i] = new PriorityQueue<>((e1,e2)->Long.compare(e2, e1));
		
		// 1에서 출발 
		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.offer(new Info(1,0L));
		
		
		while(!pq.isEmpty()) {
			Info cur = pq.poll();
			
			int curIdx = cur.idx; long d = cur.d;
			
			if(D[curIdx].size()==k && D[curIdx].peek() < d) continue;
			
			for(Edge e : G[curIdx]) {
				int adj = e.adj; int w = e.w;
				long nextD = w+d;
				if(D[adj].size() < k) {
					D[adj].offer(nextD);
					pq.offer(new Info(adj, nextD));
				}else {
					if(D[adj].peek() > nextD) {
						D[adj].poll();
						D[adj].offer(nextD);
						pq.offer(new Info(adj, nextD));
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		long[] ans = new long[k];
		for(int i = k-1 ; i >= 0 ; i--) {
			ans[i] = D[n].poll();
		}
		for(int i = 0 ; i < k ; i++) {
			sb.append(ans[i]).append(" ");
		}
		
		System.out.print(sb);

	}

}
