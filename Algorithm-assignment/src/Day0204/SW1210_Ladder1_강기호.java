package Day0204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//��� ��ٸ��� ���� X ǥ�ÿ� �����ϴ��� ���ϱ� 
//���������� 2�� ǥ�� 
//���� ���� ã�Ƽ� �Ųٷ� ���� �����Ǵ� ����ǥ ã���� �ɵ�?


public class SW1210_Ladder1_강기호 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		char A[][] = new char[100][102];
		for(int v = 0 ; v < 10 ; v++) {
			int T = Integer.parseInt(br.readLine());
			for(int i = 0 ; i < 100 ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < 100 ; j++) {
					A[i][j+1] = st.nextToken().charAt(0);
				}
				A[i][0] ='0';
				A[i][101] ='0';
			}
				
			// ���� �Ʒ����� 2ã��
			int col=0;
			for(int i = 1 ; i<=100 ; i++) {
				if(A[99][i] =='2') {
					col = i;
					break;
				}
			}
			
//			// start �������� ���� �ö󰡱�
			int row = 99;
			while(row > 0) {
				
				// ���� ��ġ���� �ٸ� �� �̵������Ҷ����� row ����
				while(row >0 && A[row][col-1]=='0' && A[row][col+1]=='0') {
					row--;
				}
				if(row ==0) break;
				// x�� �̵����� ���� �� 1ȸ �̵�
				int dx = A[row][col-1]=='1' ? -1 : 1;
				col += dx;
				
				// y�� 1�̸� �̵�
				while(A[row][col]=='1') col+=dx;
				col-=dx;
				row -=1;
			}
			sb.append("#").append(T).append(" ").append(col-1).append("\n");
			
		}
		System.out.print(sb);
		

	}

}
