package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DiceCombinations {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] D = new int[N+1];
        int MOD = 1000000000+7;
        for(int i = 1 ; i <= Math.min(N,6) ; i++){
            D[i] = 1;
        }

        for(int i = 1 ; i<= N ; i++){
            for(int j = 1 ; j <=6 ; j++){
                if(i-j < 0) continue;
                D[i] += D[i-j]%MOD;
                D[i] %= MOD;
            }
        }
        System.out.print(D[N]);

    }
}
