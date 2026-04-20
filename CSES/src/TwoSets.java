import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;

public class TwoSets {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        long sum = (long)N*(N+1)/2;
        if(sum %2 ==0){
            List<Integer> s1 = new ArrayList<>();
            List<Integer> s2 = new ArrayList<>();
            Long sum1 = 0L;

            for(int i = N ; i>=1 ; i--){
                if(sum/2 >= i+sum1){
                    sum1+=i;
                    s1.add(i);
                }else{
                    s2.add(i);
                }
            }

            // 출력
            sb.append("YES").append("\n");
            sb.append(s1.size()).append("\n");
            for(int i = s1.size()-1 ; i>=0 ; i--){
                sb.append(s1.get(i)).append(" ");
            }
            sb.append("\n");
            sb.append(s2.size()).append("\n");
            for(int i = s2.size()-1 ; i>=0 ; i--){
                sb.append(s2.get(i)).append(" ");
            }
        }else{
            sb.append("NO").append("\n");
        }


        System.out.print(sb);
    }
}
