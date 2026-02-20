package Day0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//NxN 디저트 카페 
//각 영역의 숫자는 팔고 있는 디저트 종류 
//카페들 사이에는 대각선으로 이동가능 
//한 카페에서 출발해서 대각선 방향으로 움직이고 사각형 모양을 그리며 다시 돌아와야함. 
//그리고 같은 디저트를 다시 방문하지 않도록 
//1개 카페 방문도 안됨
//갔던 길 다시 돌아오는것도 안됨. 
//
//디저트를 가장 많이 먹을 수 있는 경로를 찾고, 그 때의 수를 출력
//
//먹을 수 없는 경우 -1 출력 


public class SW2105_DessertCafe {
	
	static int[] dy = {-1,1,1,-1};
	static int[] dx = {1,1,-1,-1};
	static int[][] map;
	
	

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		map = new int[20][20];
		
		for(int t=1 ; t<=T ; t++) {
			int N = Integer.parseInt(br.readLine());
			for(int i =0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 모든 위치에서
			int ans = -1;
			for(int i= 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					
					// 위로 u , 아래로 d 만큼 
					for(int u = 1 ; u<=N-2 ; u++) {
						for(int d = 1 ; d<=N-2 ; d++) {
							// 범위 벗어나는지 확인
							if(check(i,j,u,d,N)) {
								int result = search(i,j,u,d);
								if(result != -1) ans = Math.max(ans, result);
							}
						}
					}
				}
			}	
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
	
	public static boolean check(int y , int x , int u , int d , int N) {
		// 위로 올라가는거 확인 
		if(y-u < 0 || x+u >=N) return false;
		// 아래 내려 가는거 확인
		if(y+d >=N || x+d >=N) return false;
		
		// 마지막 점 위치 확인 
		if(y-u+d>=N) return false;
		if(x+u+d>=N) return false;
		
		return true;
	}
	
	public static int search(int y , int x , int up , int down) {
		Set<Integer> hist = new HashSet<>();
		
		int cy = y;
		int cx = x;
		int ny=y; int nx=x;
		for(int d = 0 ; d <4 ; d++) {
			if(d == 0 || d == 2) {
				for(int u=1 ; u<=up ; u++) {
					ny = cy+dy[d]*u;
					nx = cx+dx[d]*u;
					if(hist.contains(map[ny][nx])) return -1;
					hist.add(map[ny][nx]);
				}
				
			}else {
				for(int dow=1 ; dow<=down ; dow++) {
					ny = cy+dy[d]*dow;
					nx = cx+dx[d]*dow;
					if(hist.contains(map[ny][nx])) return -1;
					hist.add(map[ny][nx]);
				}
				
			}
			cy = ny;
			cx = nx;
		}
		return hist.size();
		
	}

}
