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
			
			// 이진 트리 값 저장 
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				int node = Integer.parseInt(st.nextToken());
				String str = st.nextToken();
				sign[node] = str.charAt(0);
			}
			
			// 1번 부터 순회
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
		// 현재 노드가 리프인지 확인
		if(idx*2 > N) { // 리프 노드=> 무조건 숫자
			if(isSign(sign[idx])) return false;
			return true;
			
		}else { // 리프 노드 아님
			if(!isSign(sign[idx])) return false;
			
			return dfs(idx*2) && dfs(idx*2+1);
		}
		
	
		
		
		

		
			
	}
	

}
