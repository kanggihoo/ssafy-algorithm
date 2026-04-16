import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TwoKnights {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(long i = 1 ; i<=N ; i++){
            long ans = i*i*(i*i-1)/2 - 4*(i-1)*(i-2);
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
