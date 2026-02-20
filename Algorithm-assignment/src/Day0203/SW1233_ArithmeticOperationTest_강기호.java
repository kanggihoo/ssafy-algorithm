package Day0203;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
사칙연산 이진트리
정점에 연산자가 있으면 연산자의 왼쪽 서브 트리의 결과와, 오른쪽 서브 트리 결과를 사용해서 연산자 적용
양의정수, 사칙 연산자로 구성된 이진 트리 주어질때 적절한 식인지 확인 
가능하면 1, 불가능시 0 출력

10개 테스트 케이스 
완전 이진 트리 형식(위에서 부터 좌->우 방향으로)

=> 서브그래프 루트는 연산자, 자식은 숫자
리프노드는 무조건 숫자, 그외의 경우 모두 연산자 
*/

public class SW1233_ArithmeticOperationTest_강기호 {
	static boolean flag;

	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 입력
		for(int t=1 ; t<=10 ; t++) {
			
			int N = Integer.parseInt(br.readLine()); //
			boolean flag = false;
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				
				if(flag) continue; // 이미 답을 구한 경우 입력만 받고 pass 
				
				int node = Integer.parseInt(st.nextToken());
				char ch = st.nextToken().charAt(0);
						
				if(st.countTokens() == 2 && !checkSign(ch)) { // 자식 노드가 있는데 현재 노드 값이 연산자가 아닌경우
					flag = true;
				}else if(st.countTokens()==0 && checkSign(ch)) flag = true; // 리프 노드인데 연산자인 경우 
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
