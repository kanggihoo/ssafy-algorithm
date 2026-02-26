import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;
import java.util.StringTokenizer;


public class P1953_탈주범검거_모의 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int[][] map = new int[50][50];
        int[][] V = new int[50][50];
        int vCnt = 0;
        int[][][] S = {{{}},
        		{{-1,0,1,0},{0,1,0,-1}},
        		{{-1,1} , {0,0}},
        		{{0,0},{1,-1}},
        		{{-1,0},{0,1}},
        		{{1,0},{0,1}},
        		{{0,1},{-1,0}},
        		{{-1,0},{0,-1}}
        };
        

     
        for(int t = 1 ; t<=T ; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); 
            int M = Integer.parseInt(st.nextToken()); 
            int R= Integer.parseInt(st.nextToken());
            int C= Integer.parseInt(st.nextToken());
            int L= Integer.parseInt(st.nextToken());
            boolean[][][] D = new boolean[N][M][4];
            for(int i  = 0 ; i < N ; i ++) {
            	st = new StringTokenizer(br.readLine());
            	for(int j = 0 ; j < M ; j++) {
            		int n = Integer.parseInt(st.nextToken());
            		map[i][j] = n;
            	}
            }
            vCnt++;
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[] {R*M+C,1});
            V[R][C] = vCnt;
            int ans = 0;
            while(!q.isEmpty()) {
            	int[] info = q.poll();
            	int y = info[0] / M ;
            	int x = info[0] % M;
            	int d = info[1];
            	ans++;
            	if(d == L) continue;
            	
            	int idx = map[y][x];
            	int[] dy = S[idx][0];
            	int[] dx = S[idx][1];
            	
            	for(int i = 0 ; i <dy.length ; i++) {
            		int ny = y+dy[i];
            		int nx = x+dx[i];
            		if(ny>=0 && ny<N && nx>=0 && nx<M && V[ny][nx] !=vCnt && map[ny][nx] !=0) {
            			// 현재 위치에서 다음 위치가 연결되어 있는지
            			int nIdx = map[ny][nx];
            			// 아래 방향 이동
            			if(dy[i] > 0 && (nIdx == 3 || nIdx == 5 || nIdx ==6)) continue;
            			// 위로 이동 
            			else if(dy[i] < 0 && (nIdx == 3 || nIdx == 4 || nIdx ==7)) continue;
            			// 오른쪽이동
            			else if(dx[i] > 0 && (nIdx == 2 || nIdx ==4 || nIdx == 5)) continue;
            			// 왼쪽 이동
            			else if(dx[i] < 0 && (nIdx == 2 || nIdx == 6 || nIdx == 7)) continue;
            			V[ny][nx] = vCnt;
            			q.offer(new int[] {ny*M+nx , d+1});
            		}
            	}
            }           
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }

}
