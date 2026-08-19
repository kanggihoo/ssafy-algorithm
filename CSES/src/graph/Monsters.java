package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Monsters {
    static int[] dx = {0,0,1,-1}; // 아래 , 위 , 오른쪽 , 왼쪽
    static int[] dy = {1,-1,0,0};
    static int[][] dist;
    static char[][] map;
    static int[][] p;
    static int[][] direction;
    static boolean[][] V;
    static boolean flag;
    static int M;
    static int N;
    static int finalP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) dist[i][j] = N * M + 1;
        }
        Queue<Integer> q = new ArrayDeque<>();
        int start = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                map[i][j] = c;
                if (c == 'A') {
                    start = i * M + j;
                    map[i][j] = '.';
                } else if (c == 'M') {
                    q.offer(i * M + j);
                    dist[i][j] = 0;
                }
            }
        }

        // 몬스터 거리 측정

        while (!q.isEmpty()) {
            int cur = q.poll();
            int y = cur / M;
            int x = cur % M;
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if (map[ny][nx] == '#' || dist[ny][nx] != N * M + 1) continue;
                dist[ny][nx] = dist[y][x] + 1;
                q.offer(ny * M + nx);
            }
        }

        // 사람 이동
        int[][] humanDist = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(humanDist[i], -1);
        }

        Queue<Integer> aq = new ArrayDeque<>();
        aq.offer(start);
        humanDist[start / M][start % M] = 0;

        p = new int[N][M];
        direction = new int[N][M];

        while (!aq.isEmpty()) {
            int cur = aq.poll();
            int y = cur / M;
            int x = cur % M;

            if (y == 0 || y == N - 1 || x == 0 || x == M - 1) {
                flag = true;
                finalP = cur;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if (map[ny][nx] == '#') continue;
                if (humanDist[ny][nx] != -1) continue;

                int nd = humanDist[y][x] + 1;

                if (dist[ny][nx] <= nd) continue;

                humanDist[ny][nx] = nd;
                p[ny][nx] = cur;
                direction[ny][nx] = i;
                aq.offer(ny * M + nx);
            }
        }
        if(flag){
            sb.append("YES").append("\n");
            StringBuilder hist = new StringBuilder();
            // 역추적
            int tmp = finalP;
            Map<Integer , Character> m = new HashMap<>();
            m.put(0 , 'D');
            m.put(1 , 'U');
            m.put(2 , 'R');
            m.put(3 , 'L');
            while(tmp != start){
                hist.append(m.get(direction[tmp/M][tmp%M]));
                tmp = p[tmp/M][tmp%M];
            }
            sb.append(hist.length()).append("\n");
            sb.append(hist.reverse());
        }else{
            sb.append("NO");
        }
        System.out.print(sb);



    }

}
