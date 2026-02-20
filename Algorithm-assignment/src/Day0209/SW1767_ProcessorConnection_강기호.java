package Day0209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//NxN cell�� ����
//cell ���� core Ȥ�� ���� �� �� ����
//2���� �迭�� ���� �ڸ��� ������ �帧
//core�� ������ �����ϴ� ������ ������ ����, �ھ �̹� �����ڸ��� ������ ����� ������ ����  
//�������� ������ �ȵ� 
//�ִ��� ���� core�� ���� ���������� ���� ������ ���� �ּҸ� ���϶�
//1. 7 ��  N �� 12
//2. Core�� ������ �ּ� 1�� �̻� 12�� �����̴�.
//3. �ִ��� ���� Core�� ������ �����ص�, ������ ������� �ʴ� Core�� ������ �� �ִ�.
//0�� ��, 1�� core 

public class SW1767_ProcessorConnection_강기호{
	static int[][] map = new int[12][12];
	static int N;
	static int size;
	static int[][] C = new int[12][2];
	static int[] dy = {0,0,1,-1}; // �� , �� , �Ʒ� , �� 
	static int[] dx = {1,-1,0,0};
	static int maxCore;
	static int minLength;


	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
	
		int T = Integer.parseInt(br.readLine());
		for(int t= 1 ; t<=T ; t++) {
			int idx=0;
			N = Integer.parseInt(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j<N; j++) {
					
					int num = Integer.parseInt(st.nextToken());
					if(num==1) {
						if(!(i ==0 || j==0 || i==N-1 || j==N-1)) {
							C[idx][0] = i;
							C[idx][1] = j;
							idx++;
						}
						
					}
					map[i][j] = num;
				}
			}
			// Ž�� ���� ������Ʈ	
			minLength = N*N+1;
			maxCore = 0;
			size = idx;
			// Ž��
			dfs(0, 0 , 0);
			sb.append("#").append(t).append(" ").append(minLength).append("\n");
		}
		System.out.print(sb);

	}
	// idx , �ھ� ���� , ��������
	public static void dfs(int idx , int cCnt , int l) {
		
		if(idx == size) {
			if(cCnt > maxCore) {
				maxCore = cCnt;
				minLength = l;
			}
			if(maxCore == cCnt && minLength > l) {
				minLength = l;
//				System.out.printf("%d %d \n" ,cCnt , l);
			}
			return;
		}
		
		//����ġ��
		if(size-idx+cCnt < maxCore)return;
		
		// Ž��
		
		int cy = C[idx][0];
		int cx = C[idx][1];
		// ����
		for(int d = 0 ; d<4 ; d++) {
			if(check(cy,cx,d)) {
				int cnt = fill(cy,cx,d,2);
				dfs(idx+1 , cCnt+1 , l+cnt);
				fill(cy,cx,d,0);
			}
		}
		// ���� core ���� ���ϴ� ��� 
		dfs(idx+1 , cCnt, l);
		
		
		
	}
	public static boolean check(int y,int x ,int d) {
		int ny = y+dy[d];
		int nx = x+dx[d];
		while(ny>=0 && ny<N && nx>=0 && nx<N) {
			if(map[ny][nx] != 0)return false;
			ny +=dy[d];
			nx += dx[d];
		}
		return true;
	}
	
	
	
	public static int fill(int y , int x , int d,int v) {
		int cnt = 0;
		int ny = y+dy[d];
		int nx = x+dx[d];
		while(ny>=0 && ny<N && nx>=0 && nx<N) {
			map[ny][nx] = v;
			ny+=dy[d];
			nx+=dx[d];
			cnt++;
		}
		
		return cnt;
	}
	


}
