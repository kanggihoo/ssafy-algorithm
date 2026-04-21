package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Labyrinth {
	public static class Parent{
		int p;
		char d;
		public Parent(int p , char d){
			this.p = p;
			this.d = d; 
		}
	}
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] map = new boolean[N][M];
        int start=-1; int end=-1;
        for(int i = 0 ; i < N ; i++) {
        	String input = br.readLine();
        	
        	for(int j = 0 ; j < M ; j++) {
        		char c = input.charAt(j);
        		if(c!='#') map[i][j] = true;
        		if(c=='A')	start = i*M+j;
        		else if(c=='B') end = i*M+j;
        	}
        }
        
        // 탐색
        int ans = 0 ;
        int[] dy = {0,0,1,-1};
        int[] dx = {1,-1,0,0};
        char[] dirChar = {'R' , 'L' , 'D' , 'U'};
        
        boolean[] V = new boolean[N*M];
        Parent[] P = new Parent[N*M];
        
                
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(start);
		V[start] = true;
		int finalPos = -1;
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur == end) {
				finalPos = cur;
				break;
			}
			for(int d = 0 ; d <4 ; d++) {
				int ny = cur/M+dy[d] ; int nx = cur % M +dx[d];
				int next = ny*M+nx;
				if(ny < 0 || ny >=N || nx < 0 || nx>=M || !map[ny][nx] || V[next]) continue;	
				q.offer(next);
				V[next] = true;
				P[next] = new Parent(cur , dirChar[d]);
			}
		}
		
		if(finalPos == end) {
			// 역추적 
			StringBuilder sb = new StringBuilder();
			StringBuilder path = new StringBuilder();
			Parent cur = P[finalPos];
			while(cur.p != start) {
				path.append(cur.d);
				cur = P[cur.p];
			}
			path.append(cur.d);
			
			sb.append("YES").append("\n");
			sb.append(path.length()).append("\n");
			sb.append(path.reverse());
			System.out.print(sb);
		}else {
			System.out.println("NO");
		}
    }
	
}
