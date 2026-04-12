package GS.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4_5547 {
    static int N;
    static int M;
    static int[][] dy;
    static int[][] dx;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        dy = new int[2][];
        dx = new int[2][];
        dy[1] = new int[]{-1,-1,0,1,1,0};
        dx[1] = new int[]{0,1,1,1,0,-1};
        dy[0] = new int[]{-1,0,1,1,0,-1};
        dx[0] = new int[]{0,1,0,-1,-1,-1};

        map = new int[N+2][M+2];
        for(int i = 1 ; i <= N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j <= M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] V = new boolean[N+2][M+2];
        for(int i = 0 ; i < M+2 ; i++){
            if(map[0][i] == 0 && !V[0][i]){
                bfs(0,i,V);
            }
            if(map[N+1][i] == 0 && !V[N+1][i]){
                bfs(N+1,i,V);
            }
        }

        for(int i = 1 ; i < N+1 ; i++){
            if(map[i][0] == 0 && !V[i][0]){
                bfs(i,0,V);
            }

            if(map[i][M+1] == 0 && !V[i][M+1]){
                bfs(i,M+1,V);
            }
        }

        // 벽면 길이 1인 것의 외각 조사
        int ans = 0;
        for(int i = 1; i <= N ; i++){
            for(int j = 1 ; j <=M ; j++){
                if(map[i][j] ==0)continue;
                ans += check(i,j,V);
            }
        }
        System.out.print(ans);
    }

    public static void bfs(int y , int x , boolean[][]V){
        V[y][x] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(y*(M+2)+x);

        while(!q.isEmpty()){
            int cur = q.poll();
            int cy = cur / (M+2); int cx = cur %(M+2);
            for(int d = 0 ; d<6; d++){
                int ny = cy+dy[cy%2][d]; int nx = cx+dx[cy%2][d];
                if(ny < 0 || ny >=N+2 || nx<0 || nx >=M+2 || map[ny][nx] == 1 || V[ny][nx]==true)continue;
                V[ny][nx] = true;
                q.offer(ny*(M+2)+nx);
            }
        }
    }

    public static int check(int y , int x , boolean[][] V){
        int res = 0;
        for(int d = 0 ; d<6; d++) {
            int ny = y + dy[y % 2][d];
            int nx = x + dx[y % 2][d];
            if(ny < 0 || ny >=N+2 || nx<0 || nx >=M+2 || !V[ny][nx])continue;
            res++;
        }
        return res;
    }
}
