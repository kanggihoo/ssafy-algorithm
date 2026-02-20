package Day0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

//햄스터 우리 N개 (1~N번호)
//각 우리에는 0마리~X마리 이하 
//
//M개의 기록을 남겼는데, l~r번의 우리 까지 햄스터 수는 s 마리
//위 기록을 만족하는 햄스터 수 배치 구하라 
//방법이 여러가지 인 경우 햄스터 수가 가장 많은것, 그래도 겹치면 사전순으로 가장 앞선 것을 출력 
//
//기록을 만족하는 햄스터 배치가 없으면 -1 출력


public class JUN15649_NandM_강기호 {
	
	static int N;
	static int M;
	static int[] cur;
	static boolean V[];
			
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		V = new boolean[N];
		cur = new int[M];
		
		
		dfs(0);
		
		System.out.print(sb);
	}
	
	public static void dfs(int idx) {
		if(idx == M) {
			for(int i = 0 ; i < M ; i++) {
				sb.append(cur[i]).append(" ");
			}
			sb.append("\n");
			
			return;
		}
		
		for(int i = 0; i <N ; i++) {
			if(!V[i]) {
				V[i] = true;
				cur[idx] = i+1;
				dfs(idx+1);
				V[i] = false;
			}
		}
		
	}
	
	
	
	

}
