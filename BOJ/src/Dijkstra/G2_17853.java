package Dijkstra;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

class Node implements Comparable<Node>{
    int city;
    long dist;

    public Node(int city , long dist){
        this.city = city;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node o) {
        return Long.compare(this.dist , o.dist);
    }
}

public class G2_17853 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<int[]>[] G = new ArrayList[N+1];
        long[] D = new long[N+1];
        for(int i = 1 ; i <= N ; i++) D[i] = Long.MAX_VALUE;
        for(int i =1 ; i<=N ; i++) G[i] = new ArrayList<>();

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            G[n2].add(new int[]{n1,dist});
        }
        // 우선순위 큐 초기화
        Queue<Node> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < K ; i++){
            int k = Integer.parseInt(st.nextToken());
            D[k] = 0;
            pq.offer(new Node(k,0));
        }

        // 다익스트라
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(D[cur.city] < cur.dist) continue;

            for(int[] adjInfo : G[cur.city]){
                int adj = adjInfo[0]; int w = adjInfo[1];
                if(D[adj] > cur.dist+w){
                    D[adj] = cur.dist+w;
                    pq.offer(new Node(adj , D[adj]));
                }
            }
        }

        // 면접관과 가장 먼 도시 찾기
        int ans = 0;
        long res = 0;
        for(int i = 1 ; i<=N; i++){
            if(D[i] > res){
                ans = i;
                res = D[i];
            }
        }
        System.out.print(sb.append(ans).append("\n").append(res));
    }
}
