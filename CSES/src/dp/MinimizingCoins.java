package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MinimizingCoins {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] coins = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            coins[i] = Integer.parseInt(st.nextToken());
        }
        int[] D = new int[K+1];
        // 초기화
        for(int i = 1 ; i <= K ; i++){
            D[i] = Integer.MAX_VALUE;
        }
        for(int c : coins){
            if(c <= K) D[c] = 1;
        }

        for(int i = 1 ; i <=K ; i++){
            for(int c : coins){
                if(i-c < 0 || D[i-c] == Integer.MAX_VALUE) continue;
                D[i] = Math.min(D[i] , D[i-c]+1);
            }
        }
        int ans = D[K] != Integer.MAX_VALUE ? D[K] : -1;
        System.out.print(ans);
    }
}
