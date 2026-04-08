package DP.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_2785 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t = 0 ; t<T ; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            long[][] D = new long[n+1][m+1];

            // 초기화
            for(int i = 1 ; i <=m ; i++){
                D[1][i] = 1;
            }

            for(int i = 2 ; i <=n ; i++){
                for(int j = 1 ; j<=m ; j++){
                    for(int k = 1 ; k<=j/2 ; k++){
                        D[i][j] += D[i-1][k];
                    }
                }
            }
            long ans=0;
            for(int i = 1; i<=m ; i++) ans += D[n][i];
            sb.append(ans).append("\n");
        }
        System.out.print(sb);

    }
}
