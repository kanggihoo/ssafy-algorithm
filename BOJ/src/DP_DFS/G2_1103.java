package DP_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G2_1103 {
	static char[][] map;
	static int[][] D;
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	static boolean [][] V;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		for(int i = 0 ; i < N ; i++) {
			String line = br.readLine();
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		D = new int[N][M];
		V= new boolean[N][M];
		
		int ans = dfs(0,0); // 0,0에서 출발 	
		if(ans == Integer.MIN_VALUE) {
			System.out.print(-1);
		}else {
			System.out.print(ans);
		}
		
		

	}
	
	public static int dfs(int cy , int cx ) {
		
		// 이미 계산된 값인 경우 
		if(D[cy][cx] != 0) {
			return D[cy][cx];
		}
		
		int jump = map[cy][cx]-'0';
		D[cy][cx] = 1;
		V[cy][cx] = true;
		
		for(int d = 0 ; d<4; d++) {
			int ny = cy+dy[d]*jump;
			int nx = cx+dx[d]*jump;
			
			// 범위 벗어나는 경우 
			if(ny <0 || ny >=D.length || nx<0 || nx>=D[0].length) continue;
			// 다음 위치 구멍인 경우 
			if(map[ny][nx] =='H') continue;
			
			// 순환여부 확인
			if(V[ny][nx]) return Integer.MIN_VALUE;
			V[ny][nx] = true;
			int res = dfs(ny,nx);
			V[ny][nx] = false;
			
			if(res == Integer.MIN_VALUE) return res;
			D[cy][cx] = Math.max(res+1, D[cy][cx]);

		}
		
		
		V[cy][cx] = false;
		return D[cy][cx];
		
		
	}

}

