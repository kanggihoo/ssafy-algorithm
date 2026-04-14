package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G4_22865 {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        List<int[]>[] G = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) G[i] = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        int[] dist = new int[N + 1];
        for (int i = 1; i <= N; i++) dist[i] = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            int p = Integer.parseInt(st.nextToken());
            pq.add(new int[]{p, 0});
            dist[p] = 0;
        }
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            G[n1].add(new int[]{n2, L});
            G[n2].add(new int[]{n1, L});
        }

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int cP = cur[0]; int cD = cur[1];
            if(dist[cP] < cD) continue;

            for(int[] adj : G[cP]){
                if(dist[adj[0]] > dist[cP]+adj[1]){
                    dist[adj[0]] = dist[cP]+adj[1];
                    pq.add(new int[]{adj[0] , dist[adj[0]]});
                }
            }
        }
        int ans = 0;
        int maxV = 0;
        for(int i = 1 ; i <= N ; i++){
            if(dist[i] == Integer.MAX_VALUE) continue;
            if(maxV < dist[i]){
                maxV = dist[i];
                ans = i;
            }

        }
        System.out.print(ans);



    }
}
