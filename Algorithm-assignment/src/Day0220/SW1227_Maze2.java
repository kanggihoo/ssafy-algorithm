package Day0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

//100x100 행렬 
//시작점(1,1) 
//1:벽 , 0:길 , 2:출발 , 3:도착지
//미로 출발점 -> 도착지 이동가능한지 판단 
//도달 가능시 1 , 못가면 0

public class SW1227_Maze2 {
	static char[][] map;
	static int[][] V;
	static int Vcnt;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T =10;
		map = new char[100][100];
		V = new int[100][100];
		
		
		for(int t=1 ; t<=T ; t++) {
			int trash = Integer.parseInt(br.readLine());
			
			int s =0;
			for(int i = 0 ; i < 100 ; i++) {
				char[] line = br.readLine().toCharArray();
				for(int j = 0 ; j < 100 ; j++) {
					if(line[j] =='2') s = i*100*j;
					map[i][j] = line[j];
				}
			}
			
			// 탐색
			
			int ans = bfs(s);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
	
	public static int bfs(int s) {
		int cy = s/100;
		int cx = s%100;
		Vcnt++;
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(s);
		V[cy][cx] = Vcnt;
		
		while(!q.isEmpty()) {
			int c = q.poll();
			
			cy = c/100;
			cx = c% 100;
			if(map[cy][cx] =='3') return 1;
			
			for(int d = 0 ; d<4 ; d++) {
				int ny = cy+dy[d];
				int nx = cx+dx[d];
				if(ny < 0 || ny >=100 || nx<0 || nx>=100) continue;
				
				if(map[ny][nx] !='1' && V[ny][nx] != Vcnt) {
					V[ny][nx] = Vcnt;
					q.offer(ny*100+nx);
				}
			}
		}
		
		
		
		return 0;
		
	}

}
