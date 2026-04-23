//package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CoinCombinations1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] coins = new int[N];
        int[] D = new int[K+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++) coins[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(coins);
        int MOD = 1000000000+7;
        for(int c : coins){
            if(c>K) break;
            D[c] = 1;
        }

        for(int i = 1 ; i<= K ; i++){
            for(int c : coins){
                if(i<c) break;
                D[i] += D[i-c]%MOD;
                D[i] %= MOD;
            }
        }
        System.out.print(D[K]);

    }
}
