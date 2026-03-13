package Day0313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 
 * 스킬은 상하좌우 인접 영역 부식시키며 확장 
 * "지은" 이름을 가진 여신은 해당 스킬로 부터 자유로움 , 수연이는 살아남기 위해 여신님이 있는 곳으로 가야함. 
 * N,M 지도 내에 1초 동안 인접 4칸 이동가능 
 * 돌이 있는 지역 이동 안되며, 스킬도 확장되지 않음 
 * 최소 시간으로 이동하기 위한 최소 시간 구하기 
 */

import java.util.*;
public class P7793_나의여신님 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] dy = {0,0,1,-1};
		int[] dx = {1,-1,0,0};
		
		int T =Integer.parseInt(br.readLine());
		for(int t=1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			char[][] map = new char[N][M];
			int cy=0;int cx=0;
			int ty=0;int tx=0;
			List<Integer> devils = new ArrayList<Integer>(); 
			for(int i = 0 ; i < N; i++) {
				String line = br.readLine();
				for(int j = 0 ; j<M ; j++) {
					map[i][j] = line.charAt(j);
					if(map[i][j] == '*') {
						devils.add(i*M+j);
					}else if(map[i][j] == 'D') {
						ty =i; tx = j;
					}else if(map[i][j] == 'S') {
						cy =i; cx = j;
					}
				}
			}
			
			int[][] D = new int[N][M];
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < M ; j++) D[i][j] = N*M+1;
			}
			Queue<int[]> q = new ArrayDeque<int[]>();
			for(int devil : devils) {
				q.add(new int[] {devil , 0});
				D[devil/M][devil%M] = 0;
			}
			
			while(!q.isEmpty()) {
				int[] info = q.poll();
				int y = info[0] /M;
				int x = info[0] % M;
				int d = info[1];
				
				for(int i =0 ; i < 4 ; i++) {
					int ny = y+dy[i];
					int nx = x+dx[i];
					if(ny<0 || ny >=N || nx<0 || nx>=M) continue;
					if(map[ny][nx] == 'X' || map[ny][nx] =='D' || D[ny][nx] != N*M+1 ) continue;
					D[ny][nx] = d+1;
					q.offer(new int[] {ny*M+nx , d+1});
				}
			}
			
			// 나 이동 
			q.offer(new int[] {cy*M+cx , 0});
			int ans = 0;
			while(!q.isEmpty()) {
				int[] info = q.poll();
				int y = info[0] /M;
				int x = info[0] % M;
				int d = info[1];
				
				if(y==ty && x ==tx) {
					ans = d;
					break;
				}
				
				for(int i =0 ; i < 4 ; i++) {
					int ny = y+dy[i];
					int nx = x+dx[i];
					if(ny<0 || ny >=N || nx<0 || nx>=M) continue;
					if((map[ny][nx] != 'X' && D[ny][nx] > d+1) || (ny==ty && nx==tx)) {
						D[ny][nx] = d;
						q.offer(new int[] {ny*M+nx , d+1});
					}
				}
			}
			if(ans !=0) {
				sb.append("#").append(t).append(" ").append(ans).append("\n");
			}else {
				sb.append("#").append(t).append(" ").append("GAME OVER").append("\n");
			}
			
		}
		System.out.print(sb);
	}

}
