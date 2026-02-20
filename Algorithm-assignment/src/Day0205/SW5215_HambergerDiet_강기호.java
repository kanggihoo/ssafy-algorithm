package Day0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//�ܹ��� ���� �����ϸ鼭 ������ Į�θ� ���� �ʴ� �ܹ��� �ֹ�
//�ܹ��� ���Դ� ���� ���ϴ� �������� �ܹ��� �������
//
//
//�ڽ��� �Ծ��� ��ῡ ���� ���� ����ȭ 
//���� , ���Կ��� �����ϴ� ��ῡ ���� Į�θ� �־����� 
//������ Į�θ� ���� ���� �� ���� ��ȣ�ϴ� �ܹ��� ���� 
//
//���� ��� ������ ���Ұ�, 

public class SW5215_HambergerDiet_강기호 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		int[][] Info = new int[20][2]; 
		
		for(int t = 1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // ����
			int L = Integer.parseInt(st.nextToken()); // ���� Į�θ� 
			
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				Info[i][0] = Integer.parseInt(st.nextToken()); // ����
				Info[i][1] = Integer.parseInt(st.nextToken()); // Į�θ� 
			}
			
			int[] D = new int[L+1];
			for(int i = 1 ; i <= N ; i++) {
				int cS = Info[i-1][0];
				int cK = Info[i-1][1];
				for(int j = L; j >=cK ; j--) {
					
					D[j] = Math.max(D[j] , D[j-cK]+cS);
					
				}
			}
			
			int ans = D[L];
			
//			int[][] D = new int[N+1][L+1];
//			for(int i = 1 ; i <= N ; i++) {
//				for(int j = 1; j <=L ; j++) {
//					int cS = Info[i-1][0];
//					int cK = Info[i-1][1];
//					if(j < cK) D[i][j] =D[i-1][j]; 
//					else {
//						D[i][j] = Math.max(D[i-1][j] , D[i-1][j-cK]+cS);
//					}
//				}
//			}
//			
//			int ans = D[N][L];

			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.print(sb);
		

	}

}
