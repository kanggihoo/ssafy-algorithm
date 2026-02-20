package Day0213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;


public class JUN1987_Alphabet_강기호 {
	
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int N;
	static int M;
	static char[][] C;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		C = new char[N][M];
		
		for(int i = 0 ; i < N ; i++) {
			C[i] = br.readLine().toCharArray();
		}	
		
		System.out.print(dfs(0,0 , new HashSet<>()));
	}
	
	public static int dfs(int y, int x , HashSet<Character>hist) {
		
		int res = 1;
		hist.add(C[y][x]);
		for(int i = 0 ; i < 4; i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			if(ny>=0 && ny<N && nx>=0 && nx<M && !hist.contains(C[ny][nx])) {
				hist.add(C[ny][nx]);
				res = Math.max(res, dfs(ny , nx , hist)+1);
				hist.remove(C[ny][nx]);
			}
		}
		
		
		return res;
	}

}
