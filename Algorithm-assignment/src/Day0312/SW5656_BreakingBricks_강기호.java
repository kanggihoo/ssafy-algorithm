package Day0312;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * N번 구술 발사 가능 (HxW) 배열 
 * 구슬은 좌우로 이동가능하고 맨뒤 벽돌 부슴
 * 벽돌은 1~9로 표현되고, 구슬과 맞은 벽돌은 상하좌우로 벽돌숫자-1 칸 만큼 제거
 * 이때 제거되면서 부딛히는 모든 벽돌도 같이 상하좌우로 부서짐
 * 모든 블럭 부서지고 빈공간 있으면 나머지 벽돌은 아래로 떨어짐. 
 * 최대한 많은 벽돌 제거할때 남은 벽돌 개수 구하라. 
 * 
 */

import java.util.*;
public class SW5656_BreakingBricks_강기호 {
	static int[][] map;
	static int N;
	static int W;
	static int H;
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	static int ans;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T =Integer.parseInt(br.readLine());
		for(int t=1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			for(int i = 0 ; i<H ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < W ; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			ans = H*W+1;
			
			dfs(0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
	
	public static void dfs(int times) {
		if(times == N) {
			// 최종 계산
			int res = 0;
			for(int i = 0 ; i < H ; i++) {
				for(int j = 0 ; j < W ; j++) {
					if(map[i][j] != 0) res ++;
				}
			}
			ans = Math.min(res, ans);
			return;
		}
		for(int i = 0 ; i < W ; i++) { // 0~w 열 선택
			// 부순 벽돌 기록 
			List<int[]> hist = new ArrayList<>();
			int row = findRow(i);
			if(row == H) continue;
			blockBreak(row , i , hist);
			// 블록 아래로 내리기 
			List<int[]>hist2 = move();
			dfs(times+1);
			// 내린 블럭 올리기 
			for(int[]h :hist2) {
				int y = h[0]/W;
				int x = h[0]%W;
				map[y][x] = h[1];
			}
			// 부서진 벽돌 복구 
			for(int[] h : hist) {
				int y = h[0]/W;
				int x = h[0]%W;
				map[y][x] = h[1];
			}
		}
		dfs(N);
	}
	
	public static int findRow(int col) {
		for(int r = 0 ; r<H ; r++) {
			if(map[r][col] !=0) return r;
		}
		return H;
	}
	
	public static void blockBreak(int r , int c , List<int[]>h) {
		int v = map[r][c];
		h.add(new int[] {r*W+c , v});
		map[r][c] = 0;
		// v-1 번 상하좌우 부수기
		for(int d = 0 ; d<4 ; d++) {
			for(int t = 1 ; t<=v-1 ; t++) {
				int ny = r+dy[d]*t;
				int nx = c+dx[d]*t;
				if(ny<0 || ny>=H || nx<0 || nx>=W) break;
				if(map[ny][nx] ==0) continue;
				blockBreak(ny,nx,h);
			}
		}	
	}
	
	public static List<int[]> move() {
		List<int[]> h = new ArrayList<int[]>();
		for(int col = 0 ; col < W ; col++) {
			Queue<Integer> q = new ArrayDeque<Integer>();
			for(int row = H-1 ; row >=0 ; row--) {
				if(map[row][col] !=0) {
					q.offer(map[row][col]);
					h.add(new int[] {row*W+col , map[row][col]});
					map[row][col] = 0;
				}
			}
			
			int rowIdx = H-1;
			while(!q.isEmpty()) {
				map[rowIdx--][col] = q.poll();
			}			
		}
		return h;
	}
	

}
