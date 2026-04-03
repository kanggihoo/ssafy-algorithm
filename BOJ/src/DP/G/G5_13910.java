package DP.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//public class G5_13910 {
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
//		
//		st = new StringTokenizer(br.readLine());
//		int N = Integer.parseInt(st.nextToken());
//		int M = Integer.parseInt(st.nextToken());
//		int[] S = new int[M];
//		st = new StringTokenizer(br.readLine());
//		for(int i = 0 ; i < M ; i++) {
//			S[i] = Integer.parseInt(st.nextToken());
//		}
//		
//		Set<Integer> uniqueS = new HashSet<>();
//		for(int i = 0 ; i < M ; i++) uniqueS.add(S[i]);
//		
//		// 초기화 
//		int[] D = new int[N+1];
//		for(int i = 1 ; i <= N ; i++) D[i] = Integer.MAX_VALUE;
//		
//		// 가능한 조합만 미리 계산
//		for(int i = 0 ; i < M-1 ; i++) {
//			for(int j = i+1 ; j< M ; j++) {
//				uniqueS.add(S[i]+S[j]);
//			}
//		}
//		
//		// DP 계산 
//		for(int i = 1 ; i <=N ; i++) {
//			for(int s : uniqueS) {
//				if(i-s < 0) continue;
//				if(D[i-s] == Integer.MAX_VALUE) continue;
//				D[i] = Math.min(D[i], D[i-s]+1);
//			}
//		}
//		
//		if(D[N] == Integer.MAX_VALUE) {
//			System.out.print(-1);
//		}else {
//			System.out.print(D[N]);
//		}
//		
//	}
//}


public class G5_13910 {
	
	static int[] uniqueS;
	static int[] D;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] S = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M ; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}
		
		Set<Integer> set = new HashSet<>();
		for(int i = 0 ; i < M ; i++) set.add(S[i]);
		
		// 초기화 
		D = new int[N+1];
//		for(int i = 1 ; i <= N ; i++) D[i] = Integer.MAX_VALUE;
		
		// 가능한 조합만 미리 계산
		for(int i = 0 ; i < M-1 ; i++) {
			for(int j = i+1 ; j< M ; j++) {
				set.add(S[i]+S[j]);
			}
		}
		uniqueS = set.stream().mapToInt(Integer::intValue).toArray();
		
		int ans = dfs(N);
		if(ans == Integer.MAX_VALUE) {
			System.out.print(-1);
		}else {
			System.out.print(ans);
		}
		
	}
	
	public static int dfs(int cur) {
		if(cur == 0) {
			return 0;
		}
		if(D[cur] != 0) return D[cur];
			
		D[cur] = Integer.MAX_VALUE;
		for(int s : uniqueS) {
			if(cur - s <0) continue;
			int res = dfs(cur-s);
			if(res != Integer.MAX_VALUE) {
				D[cur] = Math.min(D[cur], res+1);
			}
		}
		return D[cur]; 
	}
}