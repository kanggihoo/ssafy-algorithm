package Day0414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SW1263_HumanNetwork2_강기호 {
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
      
        for(int t = 1 ; t<=T ; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            
            int[][] dist = new int[N][N];
            
            
            
            for(int i = 0 ; i < N ; i++) {
            	for(int j = 0 ; j < N ; j++) {
            		if(i==j) dist[i][j] = 0;
            		else dist[i][j] = Integer.MAX_VALUE;
            	}
            }
            
            for(int i = 0 ;  i < N ; i++) {
            	for(int j = 0 ; j < N ; j++) {
            		int w = Integer.parseInt(st.nextToken());
            		if(w==1) {
            			dist[i][j] = 1;
            		}
            	}
            }
            
            for(int k = 0 ; k<N ; k++) {
            	for(int a = 0 ; a < N; a++) {
            		if(k==a) continue;
            		if(dist[a][k] == Integer.MAX_VALUE) continue;
            		for(int b = 0 ; b < N ; b++) {
            			if(a==b || k==b) continue;
            			if(dist[k][b] == Integer.MAX_VALUE) continue;
            			if(dist[a][b] >  dist[a][k]+dist[k][b]) {
            				dist[a][b] = dist[a][k]+dist[k][b];
            			}
            		}
            	}
            }
            
            
            int ans = Integer.MAX_VALUE;
            for(int i = 0 ; i < N ; i++) {
            	int tmp = 0;
            	for(int j = 0 ; j < N ; j++) {
            		if(dist[i][j] == Integer.MAX_VALUE) continue;
            		tmp += dist[i][j];
            	}
            	ans = Math.min(ans, tmp);
            }

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
 
        System.out.print(sb);
	}

}
