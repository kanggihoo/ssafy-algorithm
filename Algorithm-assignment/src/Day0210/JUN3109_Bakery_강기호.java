package Day0210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//R*C 
//첫번째 열은 근처 빵집 가스관 마지막열은 빵집 
//가스관과 빵집을 연결하는 파이프를 설치.
//빵집과 가스관 사이에 건물이 있을 수 있고, 건물이 있으면 파이프 못놓음.
//모든 파이프는 첫째 열에서 시작 하고 마지막 열에 끝남. 
//파이프는 겹칠수도 접할 수도 없음. 각 칸을 지나는 파이프는 하나 
//첫열과 마지막 열은 항상 비어있음.


public class JUN3109_Bakery_강기호 {
	static int R;
	static int C;
	static char[][] A;
	static int[] dy = {-1,0,1};
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		A = new char[R][];
		for(int i = 0 ; i<R ; i++) {
			A[i] = br.readLine().toCharArray();
		}
		int ans=0;
		for(int i = 0 ; i<R ; i++) {
			if(dfs(i,0)) ans++;
		}
		System.out.print(ans);
		
		
	}
	public static boolean dfs(int r,int c) {
		if(c == C-1) {
			return true;
		}
		boolean res = false;
		for(int i = 0 ; i < 3 ; i++) {
			int nr = r+dy[i];
			int nc = c+1;
			if(nr>=0 && nr<R && A[nr][nc] =='.') {
				A[nr][nc] ='P';
				res = dfs(nr,nc);
				if(res) return res;
			}
		}
		return false;
	}

}
