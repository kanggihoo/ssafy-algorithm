package Day0219;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

//비상연락 당번 정보 주어질때 가장 나중에 연락을 받는 사람 중 번호가 가장 큰 사람 구하라

import java.util.*;

import sun.java2d.pipe.BufferedBufImgOps;
public class SW1238_Contact_강기호 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		
		int T =10;
		
		
		for(int t=1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
		
			int N = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			
			Set<Integer>[] G = new HashSet[101];
			for(int i = 1 ; i<=100;i++) G[i] = new HashSet<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N/2 ; i++) {
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				G[n1].add(n2);
			}
			
			// S에서 출발
			int ans = bfs(S, G);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
	
	public static int bfs(int s , Set<Integer>[] G) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[] V = new boolean[101];
		q.offer(new int[] {s,0});
		V[s] = true;
		int maxD = 0;
		int maxV = 0;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			if(cur[1] >maxD) {
				maxD = cur[1];
				maxV = cur[0];
			}else if(cur[1] == maxD && cur[0] > maxV) maxV =cur[0];
			
			for(int adj : G[cur[0]]) {
				if(!V[adj]) {
					V[adj] = true;
					q.offer(new int[] {adj,cur[1]+1});
				}
			}
			
		}
		
		return maxV;
		
	}

}
