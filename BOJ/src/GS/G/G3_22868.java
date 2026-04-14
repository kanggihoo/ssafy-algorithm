package GS.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
public class G3_22868 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Integer>[] G = new ArrayList[N+1];
        for(int i = 1 ; i<=N ; i++) G[i] = new ArrayList<>();

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            G[n1].add(n2);
            G[n2].add(n1);
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        // 그래프 정렬
        for(int i = 1 ; i <= N ; i++){
            G[i].sort(null);
        }

        // E에서 부터 bfs
        Queue<Integer> q = new ArrayDeque<>();
        int[] dist = new int[N+1];
        for(int i = 1 ; i <= N ; i++) dist[i] = -1;
        dist[E] = 0;
        q.offer(E);
        while(!q.isEmpty()){
            int cur  = q.poll();
            if(cur == S) break;
            for(int adj : G[cur]){
                if(dist[adj] != -1) continue;
                dist[adj] = dist[cur]+1;
                q.offer(adj);
            }
        }

        // 다시 S에서 출발
        int ans = dist[S];
        boolean[] V = new boolean[N+1];
        Queue<Integer> q2 = new ArrayDeque<>();
        q2.offer(S);
        while(!q2.isEmpty()){
            int cur = q2.poll();
            if(cur == E) break;
            for(int adj : G[cur]){
                if(dist[cur]-1 == dist[adj]){
                    V[adj] = true;
                    q2.offer(adj);
                    break;
                }
            }
        }

        // 다시 E->S 이동
        Queue<int[]> q3 = new ArrayDeque<>();
        q3.offer(new int[]{E,0});
        while(!q3.isEmpty()){
            int[] info = q3.poll();
            int cur = info[0] ; int d = info[1];
            if(cur == S){
                ans += d;
                break;
            }
            for(int adj : G[cur]){
                if(V[adj]) continue;
                q3.offer(new int[]{adj , d+1});
                V[adj] = true;
            }
        }

        System.out.print(ans);
    }
}
