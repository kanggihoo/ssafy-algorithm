package DP.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_19151 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] D = new int[N][M];
        int ans = 0;
        for(int i = 0 ; i < N ; i++){
            String line = br.readLine();
            for(int j = 0 ; j < M ; j++){
                D[i][j] = line.charAt(j) -'0';
                if(D[i][j] == 1) ans =1;
            }
        }


        for(int i = 1 ; i <N ; i++){
            for(int j = 1 ; j<M ; j++){
                if(D[i][j] ==0) continue;
                D[i][j] = Math.min(Math.min(D[i-1][j] , D[i][j-1]) , D[i-1][j-1])+1;
                ans = Math.max(ans , D[i][j]);
            }
        }
        System.out.print(ans*ans);

    }
}
