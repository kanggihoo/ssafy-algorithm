package Day0211;


import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

//�¿�� �ִ� N���� ���� ���� ����(Hi), ���� ��� �ٸ�
//� ���� [i,j] i<j ������ ���� ������ ���°� ������ ������ ��� ����
//
//i<k<j ���� �Ʒ��� �����ϴ� k�� �־����. 
//[i,k) �� ���������� ��������
//[k,j) ���������� ���� ���� 
//������ ���� ���϶�. 

public class SW4796_HighMoutain_강기호{

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);

		
		int T = sc.nextInt();
		int[] H = new int[50001];
		for(int t=1 ; t<=T ; t++) {
			
			int N = sc.nextInt();
			
			
			for(int i = 0 ; i < N ; i++) {
				H[i] = sc.nextInt();
			}
			
			int ans = 0;
			int i =0;
			while(i<N) {
				
				//���� ���� ���
				int k = i;
				while(k+1<N && H[k+1]> H[k]) k++;
				

				if(k>i && k<N) {
					// ���� �������
					int j = k;
					while(j+1<N && H[j+1]< H[j]) j++;
					if(j>k&&j<N) {
						ans+=(k-i)*(j-k);
						i =j;
					}else {
						i=j+1;
					}
				}else {
					i=k+1;
				}
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);

	}

}
