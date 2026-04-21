package intro;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class KnightMovesGrid {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for(int i = 0 ; i < N ; i++) {
        	for(int j = 0 ; j < N ; j ++) map[i][j] =-1;
        }
        
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.offer(0);
        map[0][0] = 0;
        
        int[] dy = {-2 , -1 , 1 ,2 , 2 , 1 , -1 , -2};
        int[] dx = {1, 2 , 2 ,1 ,-1 , -2 ,-2 ,-1};
        
        while(!q.isEmpty()) {
        	int cur = q.poll();
        	int y = cur / N ; int x = cur %N; 
        	
        	for(int i = 0 ; i < 8 ; i++) {
        		int ny = y+dy[i]; int nx = x+dx[i];
        		if(ny < 0 || ny >= N || nx < 0 || nx >= N || map[ny][nx]!=-1) continue;
        		map[ny][nx] = map[y][x] +1;
        		q.offer(ny*N+nx);
        	}
        }
        
        for(int i = 0 ; i < N ; i++) {
        	for(int j = 0 ; j < N ; j++) {
        		sb.append(map[i][j]).append(" ");
        	}
        	sb.append("\n");
        }
        
        System.out.print(sb);
    }
}
