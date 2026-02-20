package Day0212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//N���� ���� , ���� Ű �־�����, �Ϸ翡 �ѳ����� ���� ��
//ù���� ���� ���� Ű+1 , Ȧ�� ��° ���� 1 , ¦���� 2
//��� ���� Ű�� ó�� ���� �Ǵ� ������ ���������� �ϴ� �ּ� ��¥ ���
//���� �� �ټ��� ����. 
//
//N= 100 , �ִ� 120

public class SW14510_TreeHeight_강기호 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		int[] A = new int[100];
		for(int t=1 ; t<=T ; t++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0 ; i < N ; i++) A[i] = Integer.parseInt(st.nextToken());
			
			int maxV = 0;
			int ans = 0 ;
			for(int i = 0 ; i < N ; i++)  if(maxV < A[i]) maxV = A[i];
			for(int i = 0 ; i < N ; i++) A[i] = maxV - A[i];
				
			// Ž��
			int oneCnt=0;
			int twoCnt=0;
			for(int i = 0  ; i < N ; i++) {
				if(A[i] == 0) continue;
				twoCnt += A[i] /2;
				if(A[i]%2 ==1) oneCnt ++;
				
			}
//			System.out.printf("%d %d %d \n",oneCnt , twoCnt,ans);
			while(twoCnt>oneCnt+1) {
				twoCnt -=1;
				oneCnt+=2;
			}
			if(twoCnt>oneCnt) {
				ans += twoCnt*2;
			}else if(twoCnt==oneCnt) {
				ans += oneCnt*2;
			}else {
				ans += twoCnt*2;
				oneCnt-= twoCnt;
				ans += oneCnt*2-1;
			}
			
//			System.out.printf("%d %d\n",oneCnt , twoCnt);
//			
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);

	}

}
