package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge {
    int adj;
    int w;

    public Edge(int adj, int w) {
        this.adj = adj;
        this.w = w;
    }
}

class Info implements Comparable<Info>{
    int node;
    long dist ;
    int k;

    public Info(int adj, long dist, int k) {
        this.node = adj;
        this.dist = dist;
        this.k = k;
    }

    @Override
    public int compareTo(Info o) {
        return Long.compare(this.dist , o.dist);
    }
}



public class ShortestRoutes1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Edge>[] G = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) G[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            G[n1].add(new Edge(n2, w));
        }

        long[][] dist = new long[N + 1][2];
        for(int i = 1 ; i <= N ; i++){
            dist[i][0] = Long.MAX_VALUE;
            dist[i][1] = Long.MAX_VALUE;
        }
        dist[1][0] = 0L;
        dist[1][1] = 0L;
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.offer(new Info(1, 0, 1));

        while (!pq.isEmpty()) {
            Info cur = pq.poll();

            if(cur.dist > dist[cur.node][cur.k]) continue;

            for(Edge edge : G[cur.node]){
                if(cur.k == 1){
                    if(dist[edge.adj][0])
                }


            }

        }

    }


}
