package Day0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SW1251_Hanaro_강기호 {
	
	static class E implements Comparable<E>{
		int i;
		int j;
		double w;
		
		public E(int i , int j , double w){
			this.i = i;
			this.j = j;
			this.w = w;
		}
		
		@Override
		public int compareTo(E other) {
			return Double.compare(this.w, other.w);
		}
	}

	static int[] P;
	static int[] rank; 

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T =Integer.parseInt(br.readLine());
		
		for(int t=1 ; t<=T ; t++) {
			int N = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			int[] X = new int[N];
			for(int i = 0 ; i < N ; i++) X[i] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int[] Y = new int[N];
			for(int i = 0 ; i < N ; i++) Y[i] = Integer.parseInt(st.nextToken());
			
			double e = Double.parseDouble(br.readLine());
			
			
			
			P = new int[N];
			rank = new int[N];
			for(int i = 0 ; i<N;i++) P[i] = i;
			
			
			List<E> edges = new ArrayList<>();
			for(int i = 0 ; i < N ; i++) {
				for(int j = i+1 ; j < N ; j++) {
					double dist = e*(Math.pow(X[i]-X[j] ,2) + Math.pow(Y[i]-Y[j] ,2));
					E edge = new E(i,j,dist);
					edges.add(edge);	
				}
			}
			edges.sort(null);
			
			int eCnt = 0;
			double sum = 0;
			for(E edge : edges) {
				if(eCnt == N-1)break;
				int e1 = edge.i;
				int e2 = edge.j;
				if(union(e1, e2)) {
					eCnt++;
					sum += edge.w;
				}
			}
			long ans = Math.round(sum);
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
