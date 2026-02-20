package Day0203;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SW1233_ArithmeticOperationTest_강기호 {
	static boolean flag;

	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// �Է�
		for(int t=1 ; t<=10 ; t++) {
			
			int N = Integer.parseInt(br.readLine()); //
			boolean flag = false;
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				
				if(flag) continue; // �̹� ���� ���� ��� �Է¸� �ް� pass 
				
				int node = Integer.parseInt(st.nextToken());
				char ch = st.nextToken().charAt(0);
						
				if(st.countTokens() == 2 && !checkSign(ch)) { // �ڽ� ��尡 �ִµ� ���� ��� ���� �����ڰ� �ƴѰ��
					flag = true;
				}else if(st.countTokens()==0 && checkSign(ch)) flag = true; // ���� ����ε� �������� ��� 
			}
			sb.append("#").append(t).append(" ");
			if(flag) sb.append("0");
			else sb.append("1");
			sb.append("\n");
			

		}
		System.out.print(sb);
	}
	
	public static boolean checkSign(char i) {
		return (i =='+' || i =='-' || i=='*' || i=='/') ;
		
	}
	
	

}
