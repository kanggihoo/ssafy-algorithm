package Day0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//NxN 벌통 안에 꿀있음
//
//벌꿀을 채취하여 최대한 많은 수익 얻기 
//
//2명 일꾼, 꿀을 채취할 수 있는 벌통의 수 M
//각 일꾼은 가로로 연속되도록 M개 벌통 선택, 두 일꾼이 선택한 벌통 겹치면안됨
//
//C : 두일꾼이 채취할 수 있는 꿀의 최대양
//선택된 벌통으로 부터 1개의 용기에 담아야하며, 벌통으로 부터 덜어낼 수 없음.
//C를 넘어서 채취 불가능하며 꿀을 덜어낼 수 없음. (정렬)
//
//최종 용기에 담기는 벌통의 제곱의 합이 최종 수익 
//두 일꿉ㄴ이 꿀을 채취하여 얻는 수익의 최대값 구하라

public class SW2115_HoneyHarvest_강기호 {
	static int N;
	static int M;
	static int C;
	static int[] hist = new int[2];
	static int ans;
	static int[][] A= new int[10][10];

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T= Integer.parseInt(br.readLine());
		
		
		for(int t = 1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j<N ; j++) {
					A[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 계산
			ans = 0;
			dfs(0,0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.print(sb);

	}
	
	public static void dfs(int cnt, int idx) {
		if(cnt == 2) {
			int total = 0;
			for(int i = 0 ; i <2;i++) { // 2명 일꾼계산
				int r = hist[i]/N;
				int c = hist[i]%N;
				int maxV = 0;
				for(int j=1 ; j<(1<<M) ; j++) {
					
					int sum = 0;
					int profit = 0;
					for(int t = 0 ; t<M ; t++) {
						if((j&(1<<t))!=0) {
							sum += A[r][c+t];
							profit +=  A[r][c+t]* A[r][c+t];
						}
					}
					if(sum <= C && profit > maxV) maxV = profit;  
				}
				total += maxV;
			}
			ans = Math.max(ans, total);
			
			return;
		}
		
		for(int i =idx ; i<N*N ; i++) {
			// 선택할 수 있는  i 인지 확인이 필요함. 
			int row = i/N;
			int col = i%N;
			
			// 현재 열 선택 가능한 경우 
			if(col+M<=N) {
				hist[cnt] = i;
				dfs(cnt+1 , i+M);	
			}
			
		}
	}

}
