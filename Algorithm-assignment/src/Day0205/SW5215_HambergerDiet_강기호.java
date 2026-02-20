package Day0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//햄버거 맛은 유지하면서 정해진 칼로리 넘지 않는 햄버거 주문
//햄버거 가게는 고객이 원하는 조합으로 햄버거 만들어줌
//
//
//자신이 먹었던 재료에 대한 맛을 점수화 
//재료와 , 가게에서 제공하는 재료에 대한 칼로리 주어질때 
//정해진 칼로리 이하 조합 중 가장 선호하는 햄버거 조합 
//
//같은 재료 여러번 사용불가, 

public class SW5215_HambergerDiet_강기호 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		int[][] Info = new int[20][2]; 
		
		for(int t = 1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 재료수
			int L = Integer.parseInt(st.nextToken()); // 제한 칼로리 
			
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				Info[i][0] = Integer.parseInt(st.nextToken()); // 점수
				Info[i][1] = Integer.parseInt(st.nextToken()); // 칼로리 
			}
			
			int[] D = new int[L+1];
			for(int i = 1 ; i <= N ; i++) {
				int cS = Info[i-1][0];
				int cK = Info[i-1][1];
				for(int j = L; j >=cK ; j--) {
					
					D[j] = Math.max(D[j] , D[j-cK]+cS);
					
				}
			}
			
			int ans = D[L];
			
//			int[][] D = new int[N+1][L+1];
//			for(int i = 1 ; i <= N ; i++) {
//				for(int j = 1; j <=L ; j++) {
//					int cS = Info[i-1][0];
//					int cK = Info[i-1][1];
//					if(j < cK) D[i][j] =D[i-1][j]; 
//					else {
//						D[i][j] = Math.max(D[i-1][j] , D[i-1][j-cK]+cS);
//					}
//				}
//			}
//			
//			int ans = D[N][L];

			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.print(sb);
		

	}

}
