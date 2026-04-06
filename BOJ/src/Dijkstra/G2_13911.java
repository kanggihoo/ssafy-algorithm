package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class G2_13911 {
	static List<int[]>[] G;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 맥세권 , 스세권 구하고, 맥세권과 스세권 만족하는 집중 최단거리 합이 최소인 집
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		G = new ArrayList[V+1];
		for(int i = 1 ; i <= V ; i++) G[i] = new ArrayList<>();
		for(int i = 0 ; i < E ; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int w =  Integer.parseInt(st.nextToken());
			G[n1].add(new int[] {n2,w});
			G[n2].add(new int[] {n1,w});
		}
		// 맥도날드 , 스타벅스 아닌 노드 확인
		boolean[] nodes = new boolean[V+1];
		
		// 맥도날드 수 , 맥세권 조건
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		// M개 맥노날드
		int[] mNodes = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M ; i++) {
			mNodes[i] = Integer.parseInt(st.nextToken());
			nodes[mNodes[i]] = true;
		}
			
		// S개 스타벅스
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		int[] sNodes = new int[S];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < S ; i++) {
			sNodes[i] = Integer.parseInt(st.nextToken());
			nodes[sNodes[i]] = true;
		}
		
		
		// 모든 맥도날드에서 다익스트라
		int[] dist1 =dijkstra(mNodes , X , V);
		// 모든 S개 스타벅스에서 다익스트라
		int[] dist2 =dijkstra(sNodes , Y , V);
		
		// 최종 비교 
		int ans = Integer.MAX_VALUE;
		for(int i = 1; i <= V ; i++) {
			if(nodes[i]) continue;
			
			if(dist1[i] == Integer.MAX_VALUE || dist2[i] == Integer.MAX_VALUE) continue;
			
			ans = Math.min(ans, dist1[i]+dist2[i]);
		}
		
		if(ans == Integer.MAX_VALUE) {
			System.out.print(-1);
		}else {
			System.out.print(ans);
		}

	}
	
	public static int[] dijkstra(int[]starts , int range , int vCnt) {
		int[] dist = new int[vCnt+1];
		for(int i = 1 ; i<=vCnt ; i++) dist[i] = Integer.MAX_VALUE;
		
		Queue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));
		for(int node : starts) {
			dist[node] = 0;
			pq.add(new int[] {node , 0});
		}
		
		while(!pq.isEmpty()) {
			int [] info = pq.poll();
			int cur = info[0]; int d = info[1];
			if(dist[cur] < d) continue;
			
			for(int[] adInfo : G[cur]) {
				int next = adInfo[0]; int w = adInfo[1];
				if(dist[cur]+w > range) continue;
				
				if(dist[next] > dist[cur] + w) {
					dist[next] = dist[cur] +w;
					pq.add(new int[] {next,dist[next]});
				}
			}
		}
		
		return dist;
	}

}
