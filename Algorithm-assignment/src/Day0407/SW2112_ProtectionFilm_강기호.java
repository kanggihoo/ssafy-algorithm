package Day0407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

/*
 * 보호 필름은 투명막을 D장 쌓음. 
 * 하나의 막은 동일 크기의 셀들을 W개 붙혀서 
 * => 두께 D인 가로 사이즈 W 보호필름
 * 
 * 보호 필름 성능은 셀들의 배치에 따라 달라짐. 각 셀들은 특성 A or B
 *  
 * 
 * D,W, K 주어질때 약품 투입을 최소로 하는 통과방법 찾고,이때의 약품 투입 횟수 출력 (약품 안넣어도 되면0 출력)
 * 
 */

public class SW2112_ProtectionFilm_강기호 {
	static int[][] map;
	static int D;
	static int W;
	static int K;
	static int ans;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T =Integer.parseInt(br.readLine());
		for(int t=1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			ans = D;
			map = new int[D][W];
			for(int i = 0 ; i < D ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < W ; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0,0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
	
	public static void dfs(int idx,int cnt) {
		if(cnt >= ans)return;
		if(idx == D) {
			// 현재 상태 check
			if(check()) {
				ans = Math.min(ans, cnt);
			}
			return;
		}
		int[] origin = new int[W];
		for(int i = 0 ; i < W ; i++) origin[i] = map[idx][i];
		
		if(check()) {
			ans = Math.min(ans, cnt);
			return;
		}
		dfs(idx+1 ,cnt);
		
		// idx번 층에 A,B약품 칠하기
		for(int i = 0 ; i < 2 ; i++) {
			color(idx,i);
			dfs(idx+1,cnt+1);
			reset(idx,origin);	
		}
	}
	
	public static void color(int row , int v) {
		for(int i = 0 ; i < W ; i++) map[row][i] = v;
	}
	public static void reset(int row , int[] origin) {
		for(int i = 0 ; i < W ; i++) map[row][i] = origin[i];
	}
	
	public static boolean check() {
		if(K==1) return true;
		for(int col = 0 ; col < W ; col++) { // 확인할 열 선택
			boolean flag = false;
			int cnt = 1;
			for(int row = 0 ; row < D-1 ; row++) {
				if(map[row][col] == map[row+1][col]) {
					cnt++;
					if(cnt >=K) {
						flag=true;
						break;
					}
				}else {
					cnt =1;
				}
			}
			if(!flag) return false;
		}
		return true;
	}

}
