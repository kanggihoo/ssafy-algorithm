package Day0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class JUN15686_ChickenDelivery_강기호 {
	static int ans;
	static int M;
	static int N;
	static List<Integer> H;
	static List<Integer> C; 
	static int cCnt;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		H = new ArrayList<>();
		C = new ArrayList<>();
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) H.add(i*N+j);
				else if(map[i][j] ==2) C.add(i*N+j);
			}
		}
		
		// 
		

		ans = Integer.MAX_VALUE;
		cCnt = C.size();
		dfs(0,0);
		System.out.print(ans);

	}
	
	public static void dfs(int idx , int hist) {
		int cnt = Integer.bitCount(hist);
		if(cCnt-idx+ cnt < M) return;
		if(idx >C.size()) return;
		if(cnt == M) {
			// 계산 
			int res = 0;
			for(int h : H) {
				int hy = h/N; int hx = h%N;
				int minD = Integer.MAX_VALUE;
				for(int i =0 ; i< C.size() ; i++) {
					if((hist & (1<<i)) !=0) {
						int cy = C.get(i)/N;
						int cx = C.get(i)%N;
						minD = Math.min(minD, Math.abs(hy-cy)+Math.abs(hx-cx));
					}
				}
				res += minD;
			}
			ans = Math.min(res, ans);
			return;
		}
		
		dfs(idx+1 , hist | 1<<idx); // 선택
		dfs(idx+1 , hist); // 미선택
		
		
	}
	
	

}
