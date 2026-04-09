package DP.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_2073 {
	 public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        int D = Integer.parseInt(st.nextToken());
	        int P = Integer.parseInt(st.nextToken());
	        int[][] A = new int[P][2];
	        for(int i = 0 ; i < P ; i++) {
	        	st = new StringTokenizer(br.readLine());
	        	A[i][0] = Integer.parseInt(st.nextToken()); // length
	        	A[i][1] = Integer.parseInt(st.nextToken()); // 용량 
	        }
	        
	        int[] dp = new int[D+1];
	        dp[0] = Integer.MAX_VALUE;	
        	for(int i = 1 ; i<=P ; i++) {
        		int l = A[i-1][0];
        		int c = A[i-1][1];
        		for(int j = D ; j>=l ; j--) {
        			dp[j] = Math.max(Math.min(dp[j-l],c) , dp[j]);
        			
        		}
	        }
	        System.out.print(dp[D]);

	    }

}
