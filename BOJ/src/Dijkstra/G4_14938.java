package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//낙하산에서 떨어질때 낙하지역 중심 수색 범위 m 이내의
//모든 아이템획득 가능할때 최대 아이템 개수 구하기
import java.util.*;
public class G4_14938 {

    static List<int[]>[]G;
    static int[] A;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        A = new int[N+1];

        G = new ArrayList[N+1];

        for(int i = 1 ; i <=N ; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            G[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < R ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            G[a].add(new int[]{b,w});
            G[b].add(new int[]{a,w});
        }

        // n번 다익스트라
        int ans = 0;
        for(int i = 1 ; i <=N ; i++){
            ans = Math.max(ans ,  dijstra(i , M));
        }

        System.out.print(ans);
    }

    public static int dijstra(int s , int m){
        int[] D = new int[A.length];
        for(int i = 1; i < A.length ; i++) D[i] = Integer.MAX_VALUE;
        D[s] = 0;
        Queue<int[]>pq = new PriorityQueue<>((e1,e2)->Integer.compare(e1[1] , e2[1]));
        pq.offer(new int[]{s , 0});

        while(!pq.isEmpty()){
            int[] info = pq.poll();
            int cur = info[0]; int d = info[1];

            if(D[cur] < d) continue;

            for(int[] nextInfo : G[cur]){
                int adj = nextInfo[0];
                int w = nextInfo[1];

                if(D[adj] > D[cur]+w){
                    D[adj] = d+w;
                    pq.offer(new int[]{adj , D[adj]});
                }
            }
        }

        int ans = 0;
        for(int i = 1 ; i < A.length ; i++){
            if(D[i] <=m) ans+=A[i];
        }

        return ans;



    }
}
