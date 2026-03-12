package Day0311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class SW3124_MST_강기호 {

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
			List<int[]>[] G = new ArrayList[V+1];
			for(int i = 1 ; i <=V ; i++) G[i] = new ArrayList<int[]>();
			boolean[] visited = new boolean[V+1];
			
			for(int i = 0 ; i < E ; i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				int w =  Integer.parseInt(st.nextToken());
				G[n1].add(new int[] {n2,w});
				G[n2].add(new int[] {n1,w});
			}
			
			// 우선순위 큐 
			Queue<int[]> pq = new PriorityQueue<int[]>((e1 , e2)->Integer.compare(e1[1], e2[1]));
			pq.offer(new int[] {1,0});
			long ans=0;
			int cnt = 0;
			
			while(!pq.isEmpty()) {
				int[] cur = pq.poll();
				
				if(visited[cur[0]]) continue;
				
				visited[cur[0]]= true;
				ans += cur[1];
				cnt++;
				
				if(cnt == V) break;
				for(int[] adj : G[cur[0]]) {
					if(!visited[adj[0]]) {						
						pq.offer(new int[] {adj[0] , adj[1]});
					}
//					pq.offer(adj);
				}
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);

	}

}
