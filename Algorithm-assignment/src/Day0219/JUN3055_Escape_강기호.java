package Day0219;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//마법 구슬로 홍수 발생
//고슴도치 1마리 -> 비버굴로 피신
//RxC 배열 , 
//.:빈 곳, *:물참 . X:돌
//D : 비버굴
//S : 고슴도치 위치
//
//매 분마다 고슴도치 상,하,좌,우 이동가능, 물도 매 분마다 확장(상,하좌우)
//물과 고슴도치는 돌을 통과 X , 고슴도치는 물이 차있는 구역 이동X , 물도 비버굴로 이동 X
//
//고슴도치가 비버굴로 이동하기 위한 최소 시간 구하라 

public class JUN3055_Escape_강기호 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		
		int s=0;
		int ey=0; int ex=0;
		int[] dy = {0,0,1,-1};
		int[] dx = {1,-1,0,0};
		char[][] V = new char[R][C];
		
		List<Integer> W = new ArrayList<>();
		for(int i = 0 ; i<R ; i++) {
			char[] line = br.readLine().toCharArray();
			
			for(int j = 0 ; j<C ; j++) {
				char c = line[j];
				map[i][j] = c;
				if(c == 'D') {
					ey = i;
					ex = j;
				}
				else if(c=='S') s = i*C+j;
				else if(c=='*') W.add(i*C+j);
			}
		}
			
		
		Queue<int[]> q = new ArrayDeque<>();
		
		
		// 물 먼저 이동
		for(int w : W) {
			q.offer(new int[] {w,0,0});
			V[w/C][w%C] = '*';
		}
		
		q.offer(new int[] {s,1,0});
		V[s/C][s%C] = 'S';
		
		int ans = -1;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cy = cur[0] / C ;
			int cx = cur[0] %C;
			int c = cur[1];
			int d = cur[2];
			if(c == 1 && cy == ey && cx == ex) {
				ans = d;
				break;
			}
			for(int i = 0 ; i<4 ; i++) {
				int ny = cy+dy[i];
				int nx = cx+dx[i];
				
				if(ny<R && ny>=0 && nx<C && nx>=0 && V[ny][nx]==0 && map[ny][nx] !='X') {
					if(c ==1 && V[ny][nx] != '*') { // 비버
						V[ny][nx] = 'S';
						q.offer(new int[] {ny*C+nx , c , d+1});
					}else if(c == 0 && map[ny][nx] !='D') { // 물
						V[ny][nx] = '*';
						q.offer(new int[] {ny*C+nx , c , d+1});
					}
				}
				
			}
				
			
		}
//		for(char[] c : map) {
//			System.out.println(Arrays.toString(c));
//		}
		if(ans == -1) {
			System.out.print("KAKTUS");
		}else {
			System.out.print(ans);
		}

		

	}
	
	
	
	

}
