package GS.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.*;
import java.util.StringTokenizer;

// 0은 빈 공간, 1은 마법의 벽, 2는 그람이 놓여있는 공간
public class G5_17836 {
	static int N;
	static int M;
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        
        int[][] map = new int[N][M];
        int shord = 0;
        for(int i = 0 ; i < N ; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0 ; j < M ; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		if(map[i][j] == 2) shord = i*M+j;
        	}
        }
        int res1 =bfs(N*M,T); // (0,0)->(N-1, M-1) 로 이동 
        int res2 = bfs(shord,T);
        
        int ans = Math.min(res1, res2);
        
        
    }
	
	public static int bfs(int target , int limit) {
		Queue<Integer> q = new ArrayDeque<>();
		int[][] dist = new int[N][M];
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) dist[i][j] =-1;
		}
		dist[0][0] = 0;
		int ty = target / M;
		int tx = target % M;
		int ans = limit+1;
		q.offer(0);
		while(!q.isEmpty()) {
			int cur = q.poll();
			int cy = cur / M ; int cx = cur %M;
			if(dist[cy][cx] > limit) {
				ans = limit+1;
				break;
			}
			if(cy == ty && cx == tx) {
				ans = dist[cy][cx];
				break;
			}
			
			for(int d = 0 ; d < 4 ; d++) {
				int ny = cy+dy[d]; int nx = cx+dx[d];
				if(ny <0 || ny >= N || nx < 0 || nx >=M) continue;
				if(dist[ny][nx] != -1 || dist[ny][nx]==1) continue;
				dist[ny][nx] = dist[cy][cx]+1;
				q.offer(ny*M+nx);
			}
			
		}
		
		return ans;
		
	}

    

}
