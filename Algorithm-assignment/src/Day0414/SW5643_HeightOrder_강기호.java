package Day0414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SW5643_HeightOrder_강기호 {
	
	static int N;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
      
        for(int t = 1 ; t<=T ; t++){
            
            N = Integer.parseInt(br.readLine()); // 학생수 (1~N)
            int M = Integer.parseInt(br.readLine()); // 관계 수
            List<Integer>[] G1 = new ArrayList[N+1];
            List<Integer>[] G2 = new ArrayList[N+1];
            for(int i = 1 ; i <= N ; i++) {
            	G1[i] = new ArrayList<>();
            	G2[i] = new ArrayList<>();
            }
            for(int i = 0 ; i < M ; i++) {
            	st = new StringTokenizer(br.readLine());
            	int n1 = Integer.parseInt(st.nextToken());
            	int n2 = Integer.parseInt(st.nextToken());
            	G1[n1].add(n2);
            	G2[n2].add(n1);
            }
            int ans = 0;
            for(int i = 1 ; i <= N ; i++) {
            	int res1 = find(i,G1);
            	int res2 = find(i,G2);
            	if(res1-1+res2 == N) ans++;
            	
            }
            

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
 
        System.out.print(sb);
	}
	
	public static int find(int t , List<Integer>[]G) {
		
		boolean[] v = new boolean[N+1];
		return dfs(t , v, G);  
	}
	
	public static int dfs(int t , boolean[] v , List<Integer>[]G) {
		v[t] = true;
		int cnt = 1;
		for(int adj : G[t]) {
			if(!v[adj]) {
				cnt += dfs(adj , v , G);
			}
		}
		return cnt;
	}
	
	

}
