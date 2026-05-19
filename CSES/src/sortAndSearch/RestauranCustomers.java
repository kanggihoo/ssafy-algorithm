package sortAndSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Info implements Comparable<Info>{
    int s;
    int e;

    Info(int s , int e){
        this.s=s;
        this.e=e;
    }

    @Override
    public int compareTo(Info o) {
        if(e==o.e){
            return Integer.compare(s , o.s);
        }
        return Integer.compare(e , o.e);
    }
}

public class RestauranCustomers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine()); // N개
        Info[] info = new Info[N];
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            info[i] = new Info(s,e);
        }
        Arrays.sort(info);
        int e = 0;
        int ans = 0;
        int cnt = 0;
        for(Info i : info){
            if(e < i.s){
                ans = Math.max(ans , cnt);
                cnt =1;
                e = i.e;
            }else{
                cnt++;
            }
        }
        ans = Math.max(ans , cnt);
        System.out.print(ans);

    }
}
