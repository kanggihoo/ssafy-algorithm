package GS.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 불에 타기전에 탈출 여부, 얼마나 빨리 탈출 가능한지 계산
// 매분 4방향으로 이동 , 불도 4방향으로 확산
// 벽통과 X
public class G3_4179 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int S = 0;
        Queue<Integer> fires = new ArrayDeque<>();
        char[][] map = new char[N][M];
        int [][] dist = new int[N][M];
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++) dist[i][j] = N*M+1;
        }
        for(int i = 0 ; i < N ; i++){
            String line = br.readLine();
            for(int j = 0 ; j < M ; j++){
                map[i][j] = line.charAt(j);
                if(map[i][j] =='F') {
                    fires.offer(i*M+j);
                    dist[i][j] = 0;
                }
                else if(map[i][j] == 'J') S = i*M+j;
            }
        }

        // 불의 확산속도 계산
        int[] dy = new int[]{0,0,1,-1};
        int[] dx = new int[]{1,-1,0,0};
        while(!fires.isEmpty()){
            int cur = fires.poll();
            int cy = cur / M ; int cx = cur % M;
            for(int d = 0 ; d < 4 ; d++){
                int ny = cy+dy[d] ; int nx = cx+dx[d];
                if(ny < 0 || ny >= N || nx < 0 || nx >= M || map[ny][nx]=='#' || dist[ny][nx] !=N*M+1) continue;
                dist[ny][nx] = dist[cy][cx]+1;
                fires.offer(ny*M+nx);
            }
        }

        // 지훈이의 이동계산
        Queue<int[]> p = new ArrayDeque<>();
        boolean[][] V = new boolean[N][M];
        V[S/M][S%M] = true;
        p.offer(new int[]{S,0});
        int ans = -1;
        while(!p.isEmpty()){
            int[] curState = p.poll();
            int cy = curState[0] / M ; int cx = curState[0] % M; int d = curState[1];

            if(cy == 0 || cx == 0 || cy == N-1 || cx == M-1 && dist[cy][cx] > d){
                ans = d+1;
                break;
            }

            for(int i = 0 ; i< 4 ; i++){
                int ny = cy +dy[i] ; int nx = cx + dx[i];
                if(ny< 0 || ny >= N || nx < 0 || nx >= M || map[ny][nx] =='#' || V[ny][nx] || d+1 >= dist[ny][nx]) continue;
                V[ny][nx] = true;
                p.add(new int[]{ny*M+nx , d+1});
            }
        }

        if(ans == -1){
            System.out.print("IMPOSSIBLE");
        }else{
            System.out.print(ans);
        }
    }
}
