package Day0309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW1868_MineGame_강기호 {
	
	static int[] P;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] dy = {-1,-1,0,1,1,1,0,-1};
		int[] dx = {0,1,1,1,0,-1,-1,-1};
		
		int T =Integer.parseInt(br.readLine());
		
		for(int t=1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			char[][] map = new char[N][N];
			int[][] info = new int[N][N];
			P = new int[N*N];
			
			
			for(int i = 0 ; i<N*N ; i++) {
				P[i] = i;
			}
			
			
			for(int i = 0 ; i < N ; i++) {
				String line = br.readLine();
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			
			
			// 모든 위치의 8방향 지뢰개수 카운팅 
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					if(map[i][j] == '*') continue;
					for(int d = 0 ; d<8 ; d++) {
						int ny = i+dy[d];
						int nx = j+dx[d];
						if(ny<0 || ny>=N || nx<0 || nx>=N) continue;
						if(map[ny][nx] =='*') info[i][j]++;	
					}
				}
			}
			
			
			// 인접한 0끼리 union 연산 
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					if(map[i][j] == '.' && info[i][j] == 0) {
						for(int d = 0 ; d<8 ; d+=1) {
							int ny = i+dy[d];
							int nx = j+dx[d];
							if(ny<0 || ny>=N || nx<0 || nx>=N) continue;
							if(map[ny][nx] != '*' && info[ny][nx] ==0) {		
								union(i*N+j , ny*N+nx);
							}
						}
					}
				}
			}
			
			int ans = 0;
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					if(map[i][j] == '*') continue;
					
					if(info[i][j] == 0 && P[i*N+j]==i*N+j) ans++;
					else if(info[i][j] != 0) {
						boolean flag = false;
						for(int k = 0 ; k < 8 ; k++) {
							int ny = i+dy[k];
							int nx = j+dx[k];
							if(ny<0 || ny>=N || nx<0 || nx>=N) continue;
							if(map[ny][nx] != '*' && info[ny][nx] == 0) {
								flag=true;
								break;
							}
						}
						if(!flag) {
							ans++;
						}
					}
					
					
					
				}
			}
			
			
			
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);

	}
	
	public static void union(int a , int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa != pb) {
			if(pa < pb) {
				P[pb] = pa;
			}else {
				P[pa] = pb;
			}
		}
		
	}
	
	public static int find(int a) {
		if(P[a] != a) {
			P[a] = find(P[a]);
		}
		return P[a];
	}

}
