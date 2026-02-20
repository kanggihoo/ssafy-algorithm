package Day0209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//N개의 숫자가 적혀있는 게임판, 사칙연산자 카드를 숫자에 끼워 넣어 다양한 값을 구하기
//연산자의 우선순위 고려하지 않고 왼쪽 -> 오른쪽으로 계산
//그 결과가 최대가 되는 수식과, 최소가 되는 수식을 찾고 두 값의 차이 출력


public class SW4008_MakingNumber_강기호 {
	
	static int N;
	static int minV;
	static int maxV;
	static int[] signCnt;
	static int[] numbers;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		numbers = new int[12]; 
		signCnt = new int[4];
		for(int t=1 ; t<=T ; t++) {
			N = Integer.parseInt(br.readLine());
			
			
			// + , - , * , / 순서
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i< 4 ; i++) {
				signCnt[i] = Integer.parseInt(st.nextToken());
			}
			
			// 숫자 업데이트
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			// 계산
			minV = Integer.MAX_VALUE;
			maxV = Integer.MIN_VALUE;
			dfs(0,numbers[0]);
			sb.append("#").append(t).append(" ").append(maxV - minV).append("\n");
		}
		System.out.print(sb);
			

	}
	
	public static void dfs(int time , int sum) {
		if(time == N-1) {
			if(sum < minV) minV = sum;
			if(sum > maxV) maxV = sum;
			return;
		}
		
		// 연산자 선택되고 계산
		for(int i = 0 ; i<4 ; i++) {
			if(signCnt[i] <= 0) continue;
			signCnt[i] -=1;
			if(i == 0) { // + 연산
				dfs(time+1 , sum+numbers[time+1]);
			}else if(i==1) { // - 연산
				dfs(time+1 , sum-numbers[time+1]);
			}else if(i==2) { // * 연산 
				dfs(time+1 , sum*numbers[time+1]);
			}else { // / 연산 
				dfs(time+1 , sum/numbers[time+1]);
			}
			signCnt[i] +=1;
		}
	}


}
