package Day0415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 지렁이 친구 N마리 
 * 임의 2마리 매칭 후 한쪽이 다른 지렁이가 있는 곳으로 이동
 * => 이때 지렁이가 움직인 벡터 합의 크기가 작게 
 * 
 * 
 * 
 * 
 */
public class SW1494_LoveCounselor_강기호 {
	static int N;
	static int[][] A;
	static long[][] D;
	static long ans;
	
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
      
        for(int t = 1 ; t<=T ; t++){
        	N = Integer.parseInt(br.readLine());
        	A = new int[N][2];
        	for(int i = 0; i < N ; i++) {
        		st= new StringTokenizer(br.readLine());
        		A[i][0] = Integer.parseInt(st.nextToken());
        		A[i][1] = Integer.parseInt(st.nextToken());
        	}

        	ans = Long.MAX_VALUE;
        	dfs(0,0);
        	sb.append("#").append(t).append(" ").append(ans).append("\n");
        	
        }
            
           
 
        System.out.print(sb);
	}
	
	public static void dfs(int t , int state) {
		if(Integer.bitCount(state) == N/2) {
			int[] S = new int[N/2];
			int[] E = new int[N/2];
			int sIdx=0; int eIdx=0;
			for(int i = 0 ; i < N ; i++) {
				if((state & 1<<i) ==0){
					S[sIdx++] =i;
				}else {
					E[eIdx++] = i;
				}
			}
			int sumX = 0 ; int sumY = 0;
			for(int i = 0 ; i < N/2 ; i++) {
				sumX += A[E[i]][0] - A[S[i]][0];
				sumY += A[E[i]][1] - A[S[i]][1];
			}
			ans = Math.min(ans, (long)sumX*sumX+(long)sumY*sumY);
			
			
			return;
		}
		
		for(int i = t; i<N ; i++) {
			
			dfs(i+1 , state | 1<<i);
		}
	}

}
