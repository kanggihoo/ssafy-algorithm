package Day0203;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//과자 두 봉지 양손에 
//N개 과자 봉자, 각 봉지는 ai 그램
//두 봉지 무게 합이 M 그램 초과 X
//들 수 있는 최대 무게 합


public class SW9229_SpotMart_강기호 {
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t <=T ; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] A = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) A[i] = Integer.parseInt(st.nextToken());
			
			// 정렬
			Arrays.sort(A);
			
			// 슬라이딩 윈도우
			int s = 0; int e=N-1;
			int ans = 0;
			
			sb.append("#").append(t).append(" ");
			
			if(A[0]+A[1]>M) {
				sb.append(-1).append("\n");
				continue;
			}
			while(s<e) {
				int sum = A[s] + A[e];
				if(sum < M) {
					ans = ans < sum ? sum:ans;
					s++;
				}
				else if(sum > M) e--;
				else {
					ans = sum;
					break;	
				}
			}
			if(ans ==0) ans =-1;
			sb.append(ans).append("\n");
			
			
		}
		System.out.print(sb);
		
	}

}
