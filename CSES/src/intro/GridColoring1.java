package intro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class GridColoring1 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        char[] chars = {' ','A','B','C','D'};
        
        int[][] map = new int[N+1][M+1];
        for(int i = 1 ; i <= N ; i++) {
        	String input = br.readLine();
        	for(int j = 1 ; j <= M ; j++) {
        		map[i][j] = input.charAt(j-1)-'A';
        	}
        }
        
        // 윗줄부터 변경
        for(int i = 1; i<=N ; i++) {
        	for(int j = 1 ; j<=M ; j++) {
        		int state = 0;
        		state |= 1<<map[i][j-1];
        		state |= 1<<map[i-1][j];
        		state |= 1<<map[i][j];
        		
        		for(int k = 1 ; k<=4 ; k++) {
        			if((state & 1<<k ) ==0) {
        				map[i][j] = k;
        				sb.append(chars[k]);
        				break;
        			}
        		}
        	}
        	sb.append("\n");
        }
        
        // 최종 출력
        System.out.print(sb);
        
    }
}
