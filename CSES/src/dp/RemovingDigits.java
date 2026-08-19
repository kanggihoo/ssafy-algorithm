package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RemovingDigits {

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int ans = 0;
        while(N > 0){
            int num =findMaxNum(N);
            N  -= num;
            ans ++;

        }
        System.out.print(ans);

    }

    public static int findMaxNum(int num){
        int maxV = 0;
        while(num > 0){
            maxV = Math.max(maxV , num%10);
            num /=10;
        }
        return  maxV;
    }
}
