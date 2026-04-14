package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;


public class P5_1162 {

    static class Road implements Comparable<Road> {
        int idx;
        long w;
        int k;

        Road(int idx , long w,int k){
            this.idx = idx;
            this.w = w;
            this.k = k;
        }

        @Override
        public int compareTo(Road o) {
            return Long.compare(this.w , o.w);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 도시 수
        int M = Integer.parseInt(st.nextToken()); // 도로 수
        int K = Integer.parseInt(st.nextToken()); // 포장할 도로 수

        List<int[]>[] G = new ArrayList[N+1];
        for(int i =1 ; i <=N ; i++) G[i] = new ArrayList<>();
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            G[n1].add(new int[]{n2,w});
            G[n2].add(new int[]{n1,w});
        }

        long[][] D = new long[N+1][K+1];
        for(int i = 1 ; i<=N ; i++){
            for(int k = 0 ; k<=K ; k++){
                D[i][k] = Long.MAX_VALUE;
            }
        }

        // 1->N으로 출발
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.offer(new Road(1,0L,0));
        D[1][0] = 0L;
        long ans = 0;
        while(!pq.isEmpty()){
            Road cur = pq.poll();
            if(D[cur.idx][cur.k] < cur.w) continue;

            if(cur.idx == N) {
                ans = cur.w;
                break;
            }

            for(int[] adj : G[cur.idx]){
                if(cur.k < K && D[adj[0]][cur.k+1] > cur.w) { // 도로 포장 가능
                    D[adj[0]][cur.k+1] = cur.w;
                    pq.offer(new Road(adj[0] , cur.w , cur.k+1));
                }
                // 도로포장 못하는 경우 , 할 수 있는데 안하는경우
                if(D[adj[0]][cur.k] > cur.w+adj[1]){
                    D[adj[0]][cur.k] = cur.w+adj[1];
                    pq.offer(new Road(adj[0] , cur.w+adj[1] , cur.k));
                }
            }
        }

        System.out.print(ans);




    }
}
