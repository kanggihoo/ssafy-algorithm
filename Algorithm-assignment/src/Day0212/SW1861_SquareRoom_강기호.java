package Day0212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//NxN �� , (i,j) �濡�� 1~N^2 ���� ���� ��� �ٸ� 
//�����¿쿡 �̵�����. �̶� ���� �濡 ���� ���ں��� ��Ȯ�� 1Ŀ����. 
//� �濡 �־�� ���� ���� ���� ���� �̵��������� �ۼ�.

public class SW1861_SquareRoom_강기호 {
	static int[][] A;
	static int[][] D;
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	static int N;
	static int maxV;
	static int ans;
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1 ; t<=T ; t++) {
			N = Integer.parseInt(br.readLine());
			A = new int[N][N];
			D = new int[N][N];
			ans = N*N+1;
			maxV=0;
			
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) A[i][j] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					int res = dfs(i,j);
					if(res > maxV) {
						maxV = res;
						ans = A[i][j];
					}
					else if(res == maxV && A[i][j] < ans) {
						ans = A[i][j];
					}
					
				}
			}
			
			sb.append("#").append(t).append(" ").append(ans).append(" ").append(maxV).append("\n");
		}
		System.out.print(sb);
	}
	public static int dfs(int y,  int x) {
		if(D[y][x] !=0) return D[y][x];
		
		D[y][x] = 1;
		
		for(int d = 0 ; d<4 ; d++) {
			int ny = y+dy[d];
			int nx = x+dx[d];
			if(ny>=0 && ny<N && nx>=0 && nx<N) {
				if(A[ny][nx] == A[y][x]+1) {
					int res = dfs(ny,nx)+1;
					if(D[y][x] < res) D[y][x] = res;
				}
			}
		}
		return D[y][x];
	}

}
