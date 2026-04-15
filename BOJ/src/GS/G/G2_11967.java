package GS.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class G2_11967 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        List<Integer>[] G = new ArrayList[N*N];
        for(int i = 0 ; i < N*N ; i++) G[i] = new ArrayList<Integer>();
        for(int i = 0 ; i < M ; i++) {
        	st = new StringTokenizer(br.readLine());
        	int y = Integer.parseInt(st.nextToken())-1;
        	int x = Integer.parseInt(st.nextToken())-1;
        	int sy = Integer.parseInt(st.nextToken())-1;
        	int sx = Integer.parseInt(st.nextToken())-1;
        	G[y*N+x].add(sy*N+sx);
        }
        
        // 출발 위치 = (0,0)
        boolean[] V = new boolean[N*N]; // 방문여부 
        boolean[] S = new boolean[N*N]; // 특정 방에 불이 켜져 있는지 
        V[0] = true; 
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.offer(0);
        int ans = 1;
        int[] dy = new int[] {0,0,1,-1};
        int[] dx = new int[] {1,-1,0,0};
        while(!q.isEmpty()) {
        	int cur = q.poll();
        	
        	// 현재 위치에서의 스위치 누르기
        	for(int sw : G[cur]) {
        		if(S[sw]) continue; // 이미 불켜져진경우
        		ans ++;
        		S[sw] = true;
        		// 불켜진 스위치의 인접 방이 이미 방분한적 있는 경우 큐에 넣어주기
        		for(int d = 0 ; d<4; d++) {
            		int ny = sw/N+dy[d]; int nx = sw%N+dx[d];
            		if(ny<0 || ny>=N | nx<0 || nx>=N) continue;
            		if(V[ny*N+nx]) {
            			V[sw] = true;
            			q.offer(sw);
            		}
            		
            		
        		}
        		
        	}
        	// 현재 위치에서 움직이기
        	for(int d = 0 ; d<4; d++) {
        		int ny = cur/N+dy[d]; int nx = cur%N+dx[d];
        		if(ny<0 || ny>=N | nx<0 || nx>=N || V[ny*N+nx] || !S[ny*N+nx]) continue;
        		V[ny*N+nx] = true;
        		q.offer(ny*N+nx);
    		}
        }
        System.out.print(ans);
        
        
        		
        
        
      
        
        
    }

}
