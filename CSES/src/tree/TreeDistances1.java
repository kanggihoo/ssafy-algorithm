package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class TreeDistances1 {
    static List<Integer>[] G;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        G = new ArrayList[N+1];
        for(int i = 0 ; i <= N ; i++) G[i] = new ArrayList<>();

        for(int i = 0 ; i < N-1 ; i++){
            st= new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            G[n1].add(n2);
            G[n2].add(n1);
        }

        int[] dist1 = bfs(1);
        int d = -1;
        int d1 = -1;
        for(int i = 1; i<= N ; i++){
            if(d < dist1[i]){
                d = dist1[i]; d1 = i;
            }
        }
        int[] dist2 = bfs(d1);
        d = -1;
        int d2 = -1;
        for(int i = 1; i<= N ; i++){
            if(d < dist2[i]){
                d = dist2[i]; d2 = i;
            }
        }
        int[] dist3 = bfs(d2);
        for(int i = 1 ; i <=N ; i++){
            sb.append(Math.max(dist2[i] , dist3[i])).append(" ");
        }
        System.out.print(sb);
    }

    public static int[] bfs(int start){
        int[] dist = new int[N+1];
        for(int i =1 ; i <=N ; i++) dist[i] = N+1;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        dist[start] = 0;

        while(!q.isEmpty()){
            int cur = q.poll();
            for(int adj : G[cur]){
                if(dist[adj] !=N+1) continue;
                dist[adj] = dist[cur] +1;
                q.offer(adj);
            }
        }

        return dist;

    }

}
