package Day0211;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


//NxN 농장, N은 홀수
//수확은 농장 크기에 딱 맞는 정사각형 마름모 형태만 가능 

public class SW2805_CropHarvest_강기호 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] A = new int[49][49];
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1 ; t<=T ; t++) {
			int N = Integer.parseInt(br.readLine());
			
			for(int i = 0 ; i<N ; i++) {
				String line = br.readLine();
				for(int j = 0 ; j<N ; j++) {
					A[i][j] = line.charAt(j)-'0';
				}
			}
			
			// 마름모 계산
			int ans = 0;
			for(int i = 0; i<N/2 ; i++) {
				for(int j = 0 ; j<i*2+1 ; j++) {
					ans+=A[i][N/2+j-i] ;
				}
			}
			for(int i=0;i<N ; i++) ans += A[N/2][i];
			
			for(int i = 0 ; i<N/2 ; i++) {
				for(int j = i+1 ;j<N-(i+1) ; j++) {
					ans += A[N/2+1+i][j];
				}
			}
		
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);

	}

}
