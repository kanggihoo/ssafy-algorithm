package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P4_1854 {
    static class Node implements Comparable<Node>{
        int i;
        int w;

        Node(int i , int w){
            this.i = i;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w , o.w);
        }
    }

    static List<Node>[]G;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken()); // 도시 개수 (1~N) 1번 시작
        int M = Integer.parseInt(st.nextToken()); // 도로 개수
        int K = Integer.parseInt(st.nextToken());

        G=new ArrayList[N+1];
        for(int i = 1 ; i <=N ; i++) G[i] = new ArrayList<>();

        // m개 도로
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            G[a].add(new Node(b,c));
        }

        // 다익스트라 1번 도시에서 출발해서 각 도시 까지 K번쨰 최단거리 구하기
        int[] dist = dij(1,N,K);
        for(int i = 1 ; i <= N ; i++){
            sb.append(dist[i]).append("\n");
        }
        System.out.print(sb);
    }

    static int[] dij(int start , int N, int K){
        Queue<Integer>[] dist = new PriorityQueue[N+1];
        for(int i = 1 ; i <= N ; i++ ) {
            dist[i] = new PriorityQueue<>(Collections.reverseOrder());
        }
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start , 0));
        dist[start].offer(0);

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(dist[cur.i].size() >= K && dist[cur.i].peek() < cur.w) continue;

            for(Node adj : G[cur.i]){
                int newDist = cur.w + adj.w;
                if(dist[adj.i].size() < K ){
                    dist[adj.i].offer(newDist);
                    pq.offer(new Node(adj.i , newDist));
                }else if(dist[adj.i].size() == K && dist[adj.i].peek() > newDist){
                    dist[adj.i].poll();
                    dist[adj.i].offer(newDist);
                    pq.offer(new Node(adj.i , newDist));
                }
            }
        }
        // K번째 거리 반환
        int[] res = new int[N+1];
        for(int i = 1 ; i<=N ; i++){
            int kDist = dist[i].size() == K ? dist[i].peek() : -1;
            res[i] = kDist;
        }
        return res;
    }
}
