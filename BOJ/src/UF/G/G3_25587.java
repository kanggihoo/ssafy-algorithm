package UF.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;


// 1~N번도시 
public class G3_25587 {
	
	static int[] A; 
	static int[] B; 
	static int[] rank;
	static int[] P;
	static int[] size;
	static int ans;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		A = new int[N];
		B = new int[N];
		rank = new int[N];
		P = new int[N];
		size = new int[N];
		
		// 부모 배열, size배열 초기화
		for(int i = 0;i < N ; i++) {
			P[i] = i;
			size[i] = 1;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		// 강수량 
		ans = 0; // 잠기는 도시 개수 
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			B[i] = Integer.parseInt(st.nextToken());
			if(B[i] > A[i]) ans ++;
		}
		
		// M개 쿼리
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			if(q == 1) { // x번 도시와 y번 도시 합치기 
				int x = Integer.parseInt(st.nextToken()) -1;
				int y = Integer.parseInt(st.nextToken()) -1;
				union(x,y);
			}else { // 현재 상태에서 홍수가 날 도시 개수 
				sb.append(ans).append("\n");
			}
		}
		System.out.print(sb);
	}
	
	public static int find(int i) {
		if(P[i] == i) return i;
		P[i] = find(P[i]);
		return P[i];
	}
	
	public static void union(int i , int j) {
		int p1 = find(i);
		int p2 = find(j);
		if(p1 == p2) return ; 
		
		int finalP=p2;
		int finalB = p2;
		if(B[p1] > A[p1]) ans -= size[p1];
		if(B[p2] > A[p2]) ans -= size[p2];
		
		// rank가 큰 쪽에 연결
		if(rank[p1] > rank[p2]) {
			P[p2] = p1;
			size[p1] += size[p2];
			A[p1] += A[p2];
			B[p1] +=B[p2];
			finalP = p1;
			finalB = p1;
		}else if(rank[p1] < rank[p2]) {
			P[p1] = p2;
			size[p2] += size[p1];
			A[p2] += A[p1];
			B[p2] +=B[p1];
		}else { // 아무거나 연결(p1를 p2에 연결)  
			P[p1] = p2;
			rank[p2] +=1;
			size[p2] += size[p1];
			A[p2] += A[p1];
			B[p2] +=B[p1];
		}
		
		// 합치기 연산 후 잠기는 도시 업데이트 
		
		if(B[finalB] > A[finalP]) {
			ans += size[finalP];
		}
	}
}
