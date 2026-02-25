import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/*
 * NxN에 최대한 긴 등산로 만들기 , 각 숫자는 지형의 높이를 말함. 
 * 등산로는 가장 높은 봉우리에서 시작 
 * 높은 지형에서 낮은 지형으로 상하좌우 연결되어야 함. 
 * 딱 한번 최대 K 깊이 만큼 지형을 깍는 공사 가능 
 * 
 * 1. 주어진 맵에서 가장 큰 등산로 위치 찾기 
 * 
 * 
 * K= 1~5 
 * 높이 1~20
 * 지형을 깍아 높이를 1보다 작게 만드는 것도 가능 (0되 되는듯? , 맨 마지막에 주변에 1이면 깍아서 0만들고 이동하는게 가능)
 * 
 * 
 */

public class P1949_등산로조성_모의 {
	
	static int[][]V ;
	static int vCnt=0;
	
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	static int N;
	static int K;
	static int[][] map;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        map = new int[8][8];
        
        for(int t = 1 ; t<=T ; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); 
            K = Integer.parseInt(st.nextToken());  // K 높이 깍기 가능
            
            // 지형 초기화
            List<Integer> highest = new ArrayList<>();
            int maxV = 0;
            for(int i = 0 ; i < N ; i++) {
            	st = new StringTokenizer(br.readLine());
            	for(int j = 0 ; j < N ; j++) {
            		map[i][j] = Integer.parseInt(st.nextToken());
            		if(maxV < map[i][j]) {
            			maxV = map[i][j];
            		}
            	}
            }
            
            // 가장 높은 산 봉우리 위치
            for(int i = 0 ; i < N ; i++) {
            	for(int j = 0 ; j < N ; j++) {
            		if(map[i][j] == maxV) highest.add(i*N+j);
            	}
            }
            
            // db 배열 초기화 및 탐색
            V = new int[N][N];
            int ans = 0;
            for(int h : highest) {
            	vCnt++;
            	ans = Math.max(ans, dfs(h/N , h%N , true));
            }
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }
	
	public static int dfs(int y, int x , boolean flag) { 
		
		// 현재 위치 방문 및 값 업데이트 
		int cnt = 1;
		V[y][x] = vCnt;
		
		for(int i = 0 ; i < 4 ; i ++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			if(ny < 0 || ny >=N || nx < 0 || nx >=N) continue;
			if(V[ny][nx] == vCnt) continue;
			
			// 다음 위치 높이가 현재보다 낮은 경우
			if(map[y][x] > map[ny][nx]) {
				cnt = Math.max(cnt, dfs(ny,nx,flag)+1);
			}else if(map[y][x] <= map[ny][nx] && flag && map[ny][nx]-K < map[y][x]) {
				int tmp = map[ny][nx]; 
				map[ny][nx] = map[y][x] -1;
				cnt = Math.max(cnt, dfs(ny,nx,false)+1);
				map[ny][nx] = tmp;
			}
		}
		V[y][x] = vCnt-1;	
		return cnt;
	}

	

}
