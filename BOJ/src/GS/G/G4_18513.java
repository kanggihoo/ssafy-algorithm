package GS.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G4_18513 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        Queue<int[]> q = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        for(int i = 0 ; i < N ; i++){
            int water = Integer.parseInt(st.nextToken())+100000000;
            q.offer(new int[]{water , 0});
            set.add(water);
        }
        long ans = 0;
        int cnt = 0;
        int[] dx = new int[]{-1,1};
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int adj = cur[0]; int d = cur[1];
            cnt ++;
            ans += d;
            if(cnt ==K+N) break;
            for(int i = 0 ; i < 2 ; i++){
                int nx = dx[i]+adj;
                if(set.contains(nx)) continue;
                set.add(nx);
                q.offer(new int[]{nx,d+1});
            }
        }
        System.out.print(ans);
    }
}
