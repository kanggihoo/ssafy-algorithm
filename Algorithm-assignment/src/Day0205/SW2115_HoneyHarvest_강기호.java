package Day0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//NxN ���� �ȿ� ������
//
//������ ä���Ͽ� �ִ��� ���� ���� ��� 
//
//2�� �ϲ�, ���� ä���� �� �ִ� ������ �� M
//�� �ϲ��� ���η� ���ӵǵ��� M�� ���� ����, �� �ϲ��� ������ ���� ��ġ��ȵ�
//
//C : ���ϲ��� ä���� �� �ִ� ���� �ִ��
//���õ� �������� ���� 1���� ��⿡ ��ƾ��ϸ�, �������� ���� ��� �� ����.
//C�� �Ѿ ä�� �Ұ����ϸ� ���� ��� �� ����. (����)
//
//���� ��⿡ ���� ������ ������ ���� ���� ���� 
//�� �ϲߤ��� ���� ä���Ͽ� ��� ������ �ִ밪 ���϶�

public class SW2115_HoneyHarvest_강기호{
	static int N;
	static int M;
	static int C;
	static int[] hist = new int[2];
	static int ans;
	static int[][] A= new int[10][10];

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
			
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j<N ; j++) {
					A[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// ���
			ans = 0;
			dfs(0,0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.print(sb);

	}
	
	public static void dfs(int cnt, int idx) {
		if(cnt == 2) {
			int total = 0;
			for(int i = 0 ; i <2;i++) { // 2�� �ϲ۰��
				int r = hist[i]/N;
				int c = hist[i]%N;
				int maxV = 0;
				for(int j=1 ; j<(1<<M) ; j++) {
					
					int sum = 0;
					int profit = 0;
					for(int t = 0 ; t<M ; t++) {
						if((j&(1<<t))!=0) {
							sum += A[r][c+t];
							profit +=  A[r][c+t]* A[r][c+t];
						}
					}
					if(sum <= C && profit > maxV) maxV = profit;  
				}
				total += maxV;
			}
			ans = Math.max(ans, total);
			
			return;
		}
		
		for(int i =idx ; i<N*N ; i++) {
			// ������ �� �ִ�  i ���� Ȯ���� �ʿ���. 
			int row = i/N;
			int col = i%N;
			
			// ���� �� ���� ������ ��� 
			if(col+M<=N) {
				hist[cnt] = i;
				dfs(cnt+1 , i+M);	
			}
			
		}
	}

}
