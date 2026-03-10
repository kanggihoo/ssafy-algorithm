package Day0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 냉장고 배달을 위해 N명의 고객을 방문하고 자기 집으로 돌아감. 
 * 회사, 집 , 고객 위치 제공 (거리는 맨해튼 거리로)
 * => 회사 , 집, 고객 위치 전부 다름 
 * 고객은 2~10명 
 * 
 * 회사에서 출발한 후 
 */
public class SW1247_OptimalRoute_강기호 {
	static int N;
	static int[] X;
	static int[] Y;
	static int[][]D;
	static int maxV;



	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T =Integer.parseInt(br.readLine());
		
		for(int t=1 ; t<=T ; t++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			X = new int[N+2];
			Y = new int[N+2];
			D = new int[N+2][1<<N+2];
			maxV = (N+2)*201;
//			for(int i = 0 ; i < N+2 ; i++) {
//				for(int j = 0 ; j<1<<N+2 ; j++) {
//					D[i][j] = maxV;
//				}
//			}
//			
			for(int i = 0 ; i < N+2 ; i++) {
				X[i] = Integer.parseInt(st.nextToken());
				Y[i] = Integer.parseInt(st.nextToken());
			}
			
			int ans = dfs(0,1);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);

	}
	
	public static int dfs(int idx , int state) {
		if(D[idx][state] != 0) return D[idx][state];
		if(state == (1<<N+2)-1) { // 모두 방문한 경우
			if(idx == 1) return 0;
		}
		D[idx][state] = maxV;
		for(int i = 1; i<N+2 ; i++) {
			if((state & (1<<i)) ==0) {
				
				D[idx][state] = Math.min(D[idx][state], dfs(i , state | 1<<i)+Math.abs(X[i]-X[idx])+Math.abs(Y[i]-Y[idx]));
				
			}
		}
		return D[idx][state];
	}

}
