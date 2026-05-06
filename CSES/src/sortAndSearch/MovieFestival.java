package sortAndSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Festival implements Comparable<Festival>{
    int s;
    int e;

    public Festival(int s, int e) {
        this.e = e;
        this.s = s;
    }

    @Override
    public int compareTo(Festival o) {
        if(this.e == o.e) return Integer.compare(this.s , o.s);
        return Integer.compare(this.e , o.e);
    }
}

public class MovieFestival {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        Festival[] festivals = new Festival[N];
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            festivals[i] = new Festival(s,e);
        }
        Arrays.sort(festivals);

        int comp = 0;
        int ans = 0;
        for(Festival f : festivals){
            if(f.s >= comp){
                ans++;
                comp = f.e;
            }
        }
        System.out.print(ans);



    }
}
