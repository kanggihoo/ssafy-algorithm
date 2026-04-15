import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WeirdAlgorithm {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(N !=1){
            sb.append(N).append(" ");
            if(N %2==0) N /=2;
            else N = 3*N+1;
        }
        sb.append(N);
        System.out.print(sb);
    }
}
