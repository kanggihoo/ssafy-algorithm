package Day0219;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW7733_CheeseThief_강기호 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N;
		int[][] map = new int[100][100];
		int[][] V = new int[100][100];
		int[] dy = {0,0,1,-1};
		int[] dx = {1,-1,0,0};
		
		int vCnt = 1;
		int T = Integer.parseInt(br.readLine());
		for(int t=1 ; t<=T ; t++) {
			N = Integer.parseInt(br.readLine());
			
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			int ans = 0;
			for(int f = 0; f<100 ; f++) {
				// f인 경우 0 처리
				for(int i = 0 ; i < N ; i++) {
					for(int j = 0 ; j < N ; j++) {
						if(map[i][j] == f) map[i][j] = 0;
					}
				}
				
				// 0이 아닌 지점에서 bfs 처리
				int cnt = 0;
				vCnt ++;
				for(int i = 0 ; i < N ; i++) {
					for(int j = 0 ; j < N ; j++) {
						if(map[i][j] !=0 && V[i][j] !=vCnt) {
							cnt ++;
							Queue<Integer> q = new ArrayDeque<>();
							V[i][j] = vCnt;
							q.offer(i*N+j);
							while(!q.isEmpty()) {
								int cur = q.poll();
								
								int y = cur / N ;
								int x = cur % N;
								
								for(int d = 0 ; d<4 ; d++) {
									int ny = y+dy[d]; int nx = x+dx[d];
									
									if(ny>=0 && ny<N && nx>=0 && nx<N && map[ny][nx] !=0 && V[ny][nx] !=vCnt) {
										V[ny][nx] = vCnt;
										q.offer(ny*N+nx);
									}
								}
							}
						}
					}
				}
				ans = Math.max(ans , cnt);
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}

}
