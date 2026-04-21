package intro;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GrayCode {

    static  StringBuilder sb = new StringBuilder();
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dfs(0,0);

        System.out.print(sb);
    }

    public static void dfs(int i,int state){
        if(i == N){
            for(int j = 0 ; j<N ; j++){
                int res = state & 1<<j;
                res >>= j;
                sb.append(res);
            }
            sb.append("\n");
            return;
        }
        dfs(i+1 , state);
        dfs(i+1 , state | 1<<i );

    }
}
