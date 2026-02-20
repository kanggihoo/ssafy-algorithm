package Day0203;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SW1233_ArithmeticOperationTest2_강기호 {
	static boolean ans;
	static char[] sign;
	static int N;
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int t=1 ; t<=10 ; t++) {
			
			N = Integer.parseInt(br.readLine());
			sign = new char[N+1];
			
			// ���� Ʈ�� �� ���� 
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				int node = Integer.parseInt(st.nextToken());
				String str = st.nextToken();
				sign[node] = str.charAt(0);
			}
			
			// 1�� ���� ��ȸ
			sb.append("#").append(t).append(" ");
			if(dfs(1)) sb.append("1");
			else sb.append("0");
			sb.append("\n");
			
		}
		System.out.print(sb);
	}
	
	public static boolean isSign(char i) {
		return (i =='+' || i =='-' ||  i== '*' || i=='/') ;
		
	}
	public static boolean dfs(int idx) {
		// ���� ��尡 �������� Ȯ��
		if(idx*2 > N) { // ���� ���=> ������ ����
			if(isSign(sign[idx])) return false;
			return true;
			
		}else { // ���� ��� �ƴ�
			if(!isSign(sign[idx])) return false;
			
			return dfs(idx*2) && dfs(idx*2+1);
		}
		
	
		
		
		

		
			
	}
	

}
