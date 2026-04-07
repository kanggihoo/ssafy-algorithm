package TP.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class G5_22862 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] A = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) A[i] = Integer.parseInt(st.nextToken());
		
		int ans = 0;
		int left = 0; 
		int cntK=0;
		for(int right = 0 ; right < N ; right++) {
			if(A[right] % 2 == 1) { // 홀수 인 경우
				cntK++;
			}
			while(cntK > K) {
				if(A[left] % 2 ==1) cntK--; // left가 홀수이면
				left++;
			}
			ans = Math.max(ans, right-left+1-cntK);
			
		}
		System.out.print(ans);

		}


}
