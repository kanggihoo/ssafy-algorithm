package Day0408;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SW3282_Knapsack_강기호 {
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
      
        for(int t = 1 ; t<=T ; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); 
            int K = Integer.parseInt(st.nextToken());
            int[] D = new int[K+1];
            for(int i = 0 ; i < N ; i++) {
            	st = new StringTokenizer(br.readLine());
            	int V =Integer.parseInt(st.nextToken());
            	int C = Integer.parseInt(st.nextToken());
            	
            	for(int w=K ; w>=V ; w--) {
            		D[w] = Math.max(D[w], D[w-V]+C);
            	}
            	
            }

            sb.append("#").append(t).append(" ").append(D[K]).append("\n");
        }
 
        System.out.print(sb);
	}

}
