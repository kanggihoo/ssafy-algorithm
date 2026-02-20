package Day0206;
//�Ÿ޴� ���� 
//1~N���� ��� , �����Ѵ�� ���� ������ ���Ÿ� ����� ������ ���� ��︮�� �ʴ� ������ �ֱ� ������ �̸� ���
//i�� j�� ������ �ȸ����� ���� ���� �� ����
//������ ���� �ʴ� ����� M�� ���� �־����� ���� �� �ִ� ���� ���� ���϶�
//���� ����Ǿ ���� ���ŷ� ��

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW3421_BurgerMaster_강기호 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		int[][] Info = new int[400][2];
		
		for(int t = 1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			// ��︮�� �ʴ� ����
			for(int i = 0 ; i < M ; i++) {
				st = new StringTokenizer(br.readLine());
				Info[i][0] = Integer.parseInt(st.nextToken())-1;
				Info[i][1] = Integer.parseInt(st.nextToken())-1;
			}
			
			// 
			int ans = 0;
			for(int i = 0 ; i < (1<<N) ; i++) {
				
				// i ���տ� ���� ��︮�� �ʴ°� �ִ��� Ȯ��
				boolean isValid=true;
				for(int j=0; j<M ; j++) {
					int n1 = Info[j][0];
					int n2 = Info[j][1];
					
					if((i&(1<<n1))!=0 && (i&(1<<n2))!=0) {
						isValid = false;
						break; 
					}
				}
				if(isValid) ans++;
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.print(sb);

	}

}
