package Day0210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import sun.util.locale.StringTokenIterator;


//내년 1년 동안 각 달의 이용 계획 수립하고 가장 적은 비용으로 수영장 이용할 방법
//이용권은 4종류
//1일 , 1달 , 3달(연속), 1년 이용권

//- 3달 이용권은 연속된 3달이며 11월, 12월에도 3달 이용권 가능. 
//=> 걍 완탐인가? 그리디 되지 않나? db나 ? 
public class SW1952_SwimmingPool_강기호 {
	static int N;
	static int[] P;
	static int[] M;
	static int ans;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		P = new int[4];
		M = new int[12];
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i<4; i ++) P[i] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i <12; i++) M[i] =Integer.parseInt(st.nextToken());
			
			ans = Integer.MAX_VALUE; 
			dfs(0,0);
			// 1년 이용 계산
			ans = Math.min(ans, P[3]);
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
	
	public static void dfs(int idx , int total) {
		if(idx >= 12) {
			ans = Math.min(ans , total);
			return;
		}
		// 가지치기
		if(total >= ans) return;
		
		if(M[idx] == 0) {
			dfs(idx+1,total);
		}else {
			// 1일, 1달, 3달 요금선택
			for(int i = 0 ; i<3; i ++) {
				if(i == 0) dfs(idx+1 , total+P[i]*M[idx]);
				else if(i==1) dfs(idx+1 , total+P[i]);
				else dfs(idx+3,total+P[i]);
			}
		}
	}

}
