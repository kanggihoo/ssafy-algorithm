//package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;


public class Subordinates {
    static int[] D;
    static List<Integer>[] G;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        G = new ArrayList[N+1];
        for(int i = 1 ; i<=N ; i++)G[i] = new ArrayList<>();
        for(int i = 2 ; i <= N ; i++){
            int p = Integer.parseInt(st.nextToken());
            G[p].add(i);
        }
        D = new int[N+1];
        dfs(1);
        for(int i =1 ; i<=N ; i++){
            sb.append(D[i]).append(" ");
        }
        System.out.print(sb);
    }
    public static int dfs(int cur){
        int sub = 0;
        for(int adj : G[cur]){
            sub+= dfs(adj);
        }
        D[cur] = sub;
        return sub+1;

    }
}
