package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TreeMatching {
	
	static int[] V;
	static int vState=1;
	static int sState=2;
	static int ans=0;
    static List<Integer>[] G;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        G = new ArrayList[N+1];
        for(int i = 1 ; i<=N ; i++)G[i] = new ArrayList<>();
        for(int i = 0 ; i < N-1 ; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            G[n1].add(n2);
            G[n2].add(n1);
        }
        V = new int[N+1];
        dfs(1);
        System.out.print(ans);
    }
    public static void dfs(int cur){
        V[cur]=vState;
        for(int adj : G[cur]){
        	if(V[adj] != 0)continue;
            dfs(adj);
            if(V[adj] != sState && V[cur] != sState) {
            	ans++;
            	V[adj] = sState; V[cur] = sState;
            }
        }
    }

}
