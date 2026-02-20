package Day0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 해야 할 작업 V개 , 선행 관계 
// 사이클 X

public class SW1267_JobOrder {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T =10;
		
		for(int t=1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			sb2.setLength(0);
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			int[] D = new int[V+1];
			List<Integer>[] G = new ArrayList[V+1];
			for(int i = 1 ; i<=V ; i++) {
				G[i] = new ArrayList<Integer>();
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < E ; i++) {
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				
				G[n1].add(n2);
				D[n2]++;
			}
			
			// 시작 노드 찾기
			Queue<Integer> q = new ArrayDeque<Integer>();
			for(int i = 1 ; i<=V ; i++) {
				if(D[i] ==0) {
					q.offer(i);
				}
			}
			
			while(!q.isEmpty()) {
				int cur = q.poll();
				
				sb2.append(cur).append(" ");
				
				for(int adj : G[cur]) {
					
					D[adj] -=1;
					if(D[adj] <=0) {
						q.offer(adj);
					}
				}
			}
	

			sb.append("#").append(t).append(" ").append(sb2).append("\n");
		}
		System.out.print(sb);
	}

}
