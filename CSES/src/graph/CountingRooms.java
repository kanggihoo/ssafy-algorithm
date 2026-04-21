package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class CountingRooms {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] map = new boolean[N][M];
        for(int i = 0 ; i < N ; i++) {
        	String input = br.readLine();
        	
        	for(int j = 0 ; j < M ; j++) {
        		char c = input.charAt(j);
        		if(c=='.') map[i][j] = true;
        	}
        }
        
        // 탐색
        int ans = 0 ;
        int[] dy = {0,0,1,-1};
        int[] dx = {1,-1,0,0};
        
        boolean[] V = new boolean[N*M];
        for(int i = 0 ; i < N ; i++) {
        	for(int j = 0 ; j < M ; j++) {
        		if(map[i][j] && !V[i*M+j]) {
        			Queue<Integer> q = new ArrayDeque<Integer>();
        			q.offer(i*M+j);
        			V[i*M+j] = true;

        			while(!q.isEmpty()) {
        				int cur = q.poll();
        				for(int d = 0 ; d <4 ; d++) {
        					int ny = cur/M+dy[d] ; int nx = cur % M +dx[d];
        					if(ny < 0 || ny >=N || nx < 0 || nx>=M || !map[ny][nx] || V[ny*M+nx]) continue;
        					V[ny*M+nx] = true;
        					q.offer(ny*M+nx);
        				}
        			}
        			ans++;
        		}
        	}
        }
        
        System.out.print(ans);
    }
}
