package UF.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
public class G3_1939 {
    static Queue<int[]> edges;
    static int[] p;
    static int[] size;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        edges = new PriorityQueue<>((o1,o2)->Integer.compare(o2[2] , o1[2]));
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.offer(new int[]{n1,n2,c});
        }
        st = new StringTokenizer(br.readLine());
        int f1 = Integer.parseInt(st.nextToken());
        int f2 = Integer.parseInt(st.nextToken());
        p = new int[N+1];
        size = new int[N+1];
        for(int i = 1 ; i <= N ; i++) p[i] = i;
        int ans = 0;
        while(!edges.isEmpty()){
            int[] cur = edges.poll();

            if(find(cur[0]) != find(cur[1])){
                union(cur[0] , cur[1]);
                ans = cur[2];
                if(find(f1) == find(f2))break;
            }
        }
        System.out.print(ans);
    }

    public static int find(int i){
        if(p[i] != i) p[i] = find(p[i]);
        return p[i];
    }

    public static void union(int i , int j){
        int p1 = find(i);
        int p2 = find(j);
        if(size[p1] > size[p2]){ // p1으로 결합
            p[p2] = p1;
        }else if(size[p1] < size[p2]){
            p[p1] = p2;
        }else{ // 크기 동일 p1에게 합치기
            p[p2] = p1;
            size[p1]++;
        }
    }


}
