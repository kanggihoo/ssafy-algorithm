//package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GridPaths1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int MOD = 1000000000+7;
        char[][] map = new char[N+1][N+1];
        int[][] dp = new int[N+1][N+1];
        for(int i = 1 ; i <= N ; i++){
            String input = br.readLine();
            for(int j = 1 ; j <= N ; j++){
                map[i][j] = input.charAt(j-1);
            }
        }
        if(map[1][1] =='*'){
            System.out.print(0);
        }else{
            dp[1][1] = 1;

            for(int i = 1 ; i <= N ; i++){
                for(int j = 1 ; j <= N ; j++){
                    if(i==1&& j==1) continue;
                    if(map[i][j] =='*') {
                        dp[i][j] = 0;
                        continue;
                    }
                    int left = dp[i][j-1]%MOD;
                    int top = dp[i-1][j]%MOD;
                    dp[i][j] = (left+top)%MOD;
                }
            }
            System.out.print(dp[N][N]);
        }


    }
}
