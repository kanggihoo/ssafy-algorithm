import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BitStrings {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int ans = 1;
        int MOD = 1000000007;
        for(int i = 0 ; i < N ; i++){
            ans *= 2;
            ans %=MOD;
        }



        System.out.print(ans);
    }
}
