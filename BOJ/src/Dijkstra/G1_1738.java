package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge>{
    int adj;
    long dist;

    Edge(int adj , long weight){
        this.adj = adj;
        this.dist = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Long.compare(this.dist , o.dist);
    }
}

public class G1_1738 {

    static List<Edge>[] G;
    static int S;
    static int N;
    static int E;
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        //
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 교차로 개수
        int M = Integer.parseInt(st.nextToken()); // 골목개수
        S = Integer.parseInt(st.nextToken()); // 시작지점
        E = Integer.parseInt(st.nextToken()); // 도착지점
        long C = Long.parseLong(st.nextToken()); // 보유 금액

        G = new ArrayList[N+1];
        for(int i = 1 ; i <= N ; i++) G[i] = new ArrayList<>();

        // M개의 엣지 정보
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            G[n1].add(new Edge(n2,w));
            G[n2].add(new Edge(n1,w));
        }

        //이분탐색 (특정 이웃한 골목의 요금의 최대값의 최솟값구하기)
        int left = 1;
        int right = 1000000000;

        while(left <= right){
            int mid = left+(right-left)/2;
            long res = check(mid);
            if(res<=C){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        if(left >1000000000){
            System.out.print(-1);
        }else{
            System.out.print(left);
        }

    }

    public static long check(int limit){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        long[] dist = new long[N+1];
        for(int i = 1 ; i<=N ; i++) dist[i] = Long.MAX_VALUE;
        dist[S] = 0;
        pq.offer(new Edge(S,0L));

        while(!pq.isEmpty()){

            Edge cur = pq.poll();
            if(dist[cur.adj] < cur.dist) continue;

            for(Edge adj : G[cur.adj]){
                if(adj.dist > limit) continue;
                if(dist[adj.adj] > dist[cur.adj]+adj.dist ){
                    dist[adj.adj] = dist[cur.adj]+adj.dist;
                    pq.add(new Edge(adj.adj , dist[adj.adj]));
                }
            }
        }
        return dist[E];

    }
}
