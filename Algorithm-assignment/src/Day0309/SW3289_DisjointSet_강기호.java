package Day0309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW3289_DisjointSet_강기호 {
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
			// 자기 자신을 부모로 
			for(int i = 1 ; i <=n ;i++) P[i] = i;
			
			
			sb.append("#").append(t).append(" ");
			//m개 연산 개수(합집합은 0, 포함여부는 1)
			for(int i = 0 ; i < m ; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(c==0) { // union
					union(a,b);
				}else { // 같은 집합인지여부 확인 
					if(find(a) == find(b)) {
						sb.append(1);
					}else {
						sb.append(0);
					}
				}
			}


			sb.append("\n");
		}
		System.out.print(sb);

	}
	
	public static void union(int a , int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa != pb) {
			if(rank[pa] > rank[pb]) {
				P[pb] = pa;
			}else if(rank[pa] < rank[pb]) {
				P[pa] = pb;
			}else {
				P[pa] = pb;
				rank[pb]+=1;
			}
		}
		
	}
	
	public static int find(int n) {
		if(P[n] != n) {
			P[n] = find(P[n]);
		}
		return P[n];
	}

}
