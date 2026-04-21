package intro;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AppleDivision {
    static int N;
    static int[] P;
    static long ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        P = new int[N];
        for(int i = 0 ; i < N ; i ++){
            P[i] = Integer.parseInt(st.nextToken());
        }
        ans = Integer.MAX_VALUE;
        dfs(0,0);
        System.out.print(ans);
    }

    public static void dfs(int i , int state){
        if(i==N){
            long s1 = 0L ; long s2 = 0L;
            for(int j = 0 ; j < N ; j++){
                if((state & 1<<j) !=0){
                    s1+=P[j];
                }else{
                    s2+=P[j];
                }
            }
            ans = Math.min(ans , Math.abs(s1-s2));
            return;
        }
        dfs(i+1 , state);
        dfs(i+1 , state | 1<<i);
    }

}
