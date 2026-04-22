package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class RoundTrip {
	static boolean[] V;
	static int[] P;
	static List<Integer>[] G;
	static int cycle;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        G = new ArrayList[N+1];
        for(int i = 1; i <= N ; i++) {
        	G[i] = new ArrayList<>();
        }
        
        for(int i = 0 ; i < M ; i ++) {
        	st = new StringTokenizer(br.readLine());
        	int n1 = Integer.parseInt(st.nextToken());
        	int n2 = Integer.parseInt(st.nextToken());
        	
        	G[n1].add(n2);
        	G[n2].add(n1);
        }
        
        // 그래프 탐색
        V = new boolean[N+1];
        P = new int[N+1];
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for(int i = 1 ; i <= N ; i++) {
        	if(!V[i]) {
        		if(dfs(i,-1)) {
        			// 출력
        			List<Integer> hist = new ArrayList<>();
        			int start = P[cycle];
        			hist.add(cycle);
        			while(start != cycle) {
        				hist.add(start);
        				start = P[start];
        			}
        			hist.add(cycle);
        			// 뒤집기
        			sb.append(hist.size()).append("\n");
        			for(int j = hist.size()-1 ; j >=0 ;j --) {
        				sb.append(hist.get(j)).append(" ");
        			}
        			flag = true;
        			break;
        		}
        	}
        }
        if(!flag) {
        	sb.append("IMPOSSIBLE");
        }
        System.out.print(sb);
        
	}
	
	public static boolean dfs(int cur , int pre) {
		V[cur] = true;
		for(int adj : G[cur]) {
			if(adj == pre) continue;
			P[adj] = cur;
			if(adj != pre && V[adj]) { // 처음 순환발생
				cycle = adj;
				return true;
			}
			boolean res = dfs(adj , cur);
			if(res) {
				
				return true;
			}
		}
		
		return false;
	}
	
}
