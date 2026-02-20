package Day0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW2115_HoneyHarvest_DP_강기호 {
	static int N;
	static int M;
	static int C;
	static int ans;
	

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T= Integer.parseInt(br.readLine());
		
		
		for(int t = 1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			int[][] A = new int[N][N];
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j<N ; j++) {
					A[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// �� ��ġ������ dp ���
			int[][] maxProfit = new int[N][N];
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j<N-M+1 ; j++) {
					maxProfit[i][j] = calculate(i,j, A);
				}
			}
			
			// �ϲ� Ž��
			int ans = 0;
			for(int i=0;i<N*N ; i++ ) {
				int w1R = i/N;
				int w1C = i%N;
				
				int p1 = maxProfit[w1R][w1C];
				for(int j = i+M ; j<N*N ; j++) {
					int w2R = j/N;
					int w2C = j%N;
					int p2 = maxProfit[w2R][w2C];
					
					if(p1+p2 > ans) ans = p1+p2;
				}
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
	
	public static int calculate(int r , int c , int[][]A) {
		int[] D = new int[C+1];
		
		for(int i = 0 ; i<M ; i++) {
			int curH = A[r][c+i];
			int curP = curH*curH;
			for(int j = C ; j>=curH ; j--) {
				D[j] = Math.max(D[j], D[j-curH]+curP);
			}
		}
		return D[C];
		
	}
			

	
}
