package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Road implements Comparable<Road>{
	int idx;
	long w;
	
	Road(int idx , long w){
		this.idx = idx;
		this.w = w;
	}

	@Override
	public int compareTo(Road o) {
		// TODO Auto-generated method stub
		return Long.compare(this.w, o.w);
	}
	
	
}

public class G1_24042 {
	
	
	static List<int[]>[] G;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		G = new ArrayList[N+1];
		for(int i = 1; i <=N ; i++) G[i] = new ArrayList<>();
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			G[n1].add(new int[] {n2,i});
			G[n2].add(new int[]{n1,i});
		}
		long[] D = new long[N+1];
		for(int i = 1; i <= N ; i++) D[i] = Long.MAX_VALUE;
		
		Queue<Road> pq = new PriorityQueue<Road>();
		pq.offer(new Road(1,0L));
		D[1] =0L;
		
		while(!pq.isEmpty()) {
			Road cur = pq.poll();
			
			if(D[cur.idx] < cur.w) continue;
			
			for(int[] adj : G[cur.idx]) {
				int waitTime = (adj[1]-(int)(cur.w%M)+M)%M;
				if(D[adj[0]] > cur.w+waitTime) {
					D[adj[0]] = cur.w+waitTime+1;
					pq.offer(new Road(adj[0] , D[adj[0]]));
				}
			}
		}
		System.out.print(D[N]);
		

	}

}
