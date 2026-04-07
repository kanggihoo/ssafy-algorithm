package TP.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class G4_3151 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) A[i] = Integer.parseInt(st.nextToken());
		
		long ans = 0;
		if(N>=3) {
			Arrays.sort(A);
			
			// 가장 큰 수 선택
			for(int i = N-1 ; i>=2 ; i--) {
				int target = A[i];
				
				int left = 0 ; int right = i-1;
				while(left < right) {
					int tSum = A[left]+A[right];
					if(tSum > -1*target) {
						right --;
					}else if(tSum < -1*target) {
						left++;
					}else {
						if(A[left] == A[right]) {
							int cnt = right - left+1;
							ans += cnt*(cnt-1)/2; // cnt 중에서 2개 선택하는 모든 경우의수
							break;
						}else { // 다른 경우 
							int nextL = left ; int nextR = right;
							while(A[left]==A[nextL]) nextL++;
							while(A[right]==A[nextR]) nextR--;
							
							ans += (nextL-left)*(right-nextR);
							left = nextL;
							right = nextR;
							
						}
						
					}
				}
			}
			
		}
		System.out.print(ans);
		
				
			
		
	}

}
