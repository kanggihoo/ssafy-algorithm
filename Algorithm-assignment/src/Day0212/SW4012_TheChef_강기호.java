package Day0212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//�� ���� �մ� , �ִ��� ����� ���� ���� �����
//N���� ����Ḧ N/2 �� ������  �丮 (N¦��)
//A,B ������ ���� ���̰� �ּҰ� �ǵ��� �й� 

public class SW4012_TheChef_강기호{
	static int[][] S = new int[16][16];

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1 ; t<=T ; t++) {
			int N = Integer.parseInt(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) S[i][j] = Integer.parseInt(st.nextToken());
			}
			
			int ans = Integer.MAX_VALUE;
			int maxRange = ((1<<N/2)-1) << N/2;
			for(int i= 1 ; i<=maxRange ; i++) {
				
				// 1���� N/2 �� ��� 
				if(Integer.bitCount(i) == N/2) {
					int side = (i^((1<<N)-1));
					// ���� ��� 
					int c1 = calculate(i,N);
					int c2 = calculate(side, N);
//					System.out.printf("%d %d %d %d\n" , i,c1 , side , c2);
					ans = Math.min(ans,Math.abs(c1-c2));
				}
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
	
	public static int calculate(int i , int N) {
		int c = 0;
		for(int j = 0 ; j < N ; j++) {
			for(int k = 0 ; k<N ; k++) {
				if(j == k) continue;
				if((i&(1<<j))!=0 && (i&(1<<k)) !=0) {
					c += S[j][k];
				}
			}
		}
		return c;
	}
	

}
