package Day0312;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

// 4x4 격자 , 0~9 숫자 , 임의지역에서 시작해서 4방향으로 6번 이동한뒤 각 칸의 숫자 이어 붙이면 7자리 숫자 

public class SW2819_JoinNumbers_강기호 {
	
	static Set<String> set;
	static int[][] map;
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T =Integer.parseInt(br.readLine());
		map = new int[4][4];
		for(int t=1 ; t<=T ; t++) {
			for(int i =0  ; i <4  ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < 4 ; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			set = new HashSet<>();
			for(int i = 0 ; i < 4 ; i++) {
				for(int j =  0 ; j < 4 ; j++) {
					dfs(i*4+j,0,"");
				}
			}
			
			sb.append("#").append(t).append(" ").append(set.size()).append("\n");
		}
		System.out.print(sb);

	}
	
	public static void dfs(int i, int times , String s) {
		if(times == 7) {
			set.add(s);
			return;
		}
		int cy = i/4;
		int cx = i%4;
		int n = map[cy][cx];
		for(int d = 0; d<4 ; d++) {
			int ny = cy+dy[d];
			int nx = cx+dx[d];
			if(ny< 0 || ny >=4 || nx<0 || nx>=4) continue;
			
			dfs(ny*4+nx , times+1, s+String.valueOf(n));
		}
		
	}

}
