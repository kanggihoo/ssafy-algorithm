package Day0202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

//16x16 미로 
//출발지로부터 도착지점 갈 수 있는지 프로그램 작성
//1:벽 , 0:길 , 2:출발 : 3:도착 
//도달 가능시 1, 못가면 0출력
public class SW1226_Maze1 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] dx = {0,0,1,-1};
		int[] dy = {-1,1,0,0};
		for(int t = 1 ; t <=10 ; t++) {
			int T = Integer.parseInt(br.readLine());
			char[][] map= new char[16][16];
			int sy=0; int sx=0;
			int ey=0; int ex=0;
			
			for(int i = 0 ; i < 16; i++) {
				String line = br.readLine();
				for(int j = 0 ; j < 16 ; j++) {
					char c = line.charAt(j);
					map[i][j] = c;
					if(c =='2') {
						sy=i; sx=j;
					}else if(c=='3') {
						ey=i; ex=j;
					}
				}
			}
			
			Queue<int[]> q = new ArrayDeque<>();
			map[sy][sx] = 4+'0';
			q.offer(new int[] {sy,sx});
			int ans = 0;
			
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				int cy=cur[0] ; int cx = cur[1];
				
				if(cy == ey && cx == ex) {
					ans = 1;
					break;
				}
				
				for(int i = 0 ; i<4 ; i++) {
					int ny = cy+dy[i];
					int nx = cx+dx[i];
					
					if(ny>=0 && ny<=15 && nx>=0 && nx<=15 && (map[ny][nx] =='0' || map[ny][nx] == '3')) {
						map[ny][nx] = 4+'0';
						q.offer(new int[] {ny,nx});
					}
				}
				
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
			
		}
		System.out.print(sb);
		
		
	}
	


}
