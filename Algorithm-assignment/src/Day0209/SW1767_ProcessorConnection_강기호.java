package Day0209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//NxN cell로 구성
//cell 에는 core 혹은 전선 올 수 있음
//2차원 배열이 가장 자리는 전원이 흐름
//core와 전원을 연결하는 전선은 직선만 가능, 코어가 이미 가장자리에 있으면 연결된 것으로 간주  
//직선간의 교차는 안됨 
//최대한 많은 core에 전원 연결했을때 전선 길이의 합의 최소를 구하라
//1. 7 ≤  N ≤ 12
//2. Core의 개수는 최소 1개 이상 12개 이하이다.
//3. 최대한 많은 Core에 전원을 연결해도, 전원이 연결되지 않는 Core가 존재할 수 있다.
//0은 빈셀, 1은 core 

public class SW1767_ProcessorConnection_강기호 {
	static int[][] map = new int[12][12];
	static int N;
	static int size;
	static int[][] C = new int[12][2];
	static int[] dy = {0,0,1,-1}; // 오 , 왼 , 아래 , 위 
	static int[] dx = {1,-1,0,0};
	static int maxCore;
	static int minLength;


	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
	
		int T = Integer.parseInt(br.readLine());
		for(int t= 1 ; t<=T ; t++) {
			int idx=0;
			N = Integer.parseInt(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j<N; j++) {
					
					int num = Integer.parseInt(st.nextToken());
					if(num==1) {
						if(!(i ==0 || j==0 || i==N-1 || j==N-1)) {
							C[idx][0] = i;
							C[idx][1] = j;
							idx++;
						}
						
					}
					map[i][j] = num;
				}
			}
			// 탐색 개수 업데이트	
			minLength = N*N+1;
			maxCore = 0;
			size = idx;
			// 탐색
			dfs(0, 0 , 0);
			sb.append("#").append(t).append(" ").append(minLength).append("\n");
		}
		System.out.print(sb);

	}
	// idx , 코어 개수 , 전설길이
	public static void dfs(int idx , int cCnt , int l) {
		
		if(idx == size) {
			if(cCnt > maxCore) {
				maxCore = cCnt;
				minLength = l;
			}
			if(maxCore == cCnt && minLength > l) {
				minLength = l;
//				System.out.printf("%d %d \n" ,cCnt , l);
			}
			return;
		}
		
		//가지치기
		if(size-idx+cCnt < maxCore)return;
		
		// 탐색
		
		int cy = C[idx][0];
		int cx = C[idx][1];
		// 방향
		for(int d = 0 ; d<4 ; d++) {
			if(check(cy,cx,d)) {
				int cnt = fill(cy,cx,d,2);
				dfs(idx+1 , cCnt+1 , l+cnt);
				fill(cy,cx,d,0);
			}
		}
		// 현재 core 연결 안하는 경우 
		dfs(idx+1 , cCnt, l);
		
		
		
	}
	public static boolean check(int y,int x ,int d) {
		int ny = y+dy[d];
		int nx = x+dx[d];
		while(ny>=0 && ny<N && nx>=0 && nx<N) {
			if(map[ny][nx] != 0)return false;
			ny +=dy[d];
			nx += dx[d];
		}
		return true;
	}
	
	
	
	public static int fill(int y , int x , int d,int v) {
		int cnt = 0;
		int ny = y+dy[d];
		int nx = x+dx[d];
		while(ny>=0 && ny<N && nx>=0 && nx<N) {
			map[ny][nx] = v;
			ny+=dy[d];
			nx+=dx[d];
			cnt++;
		}
		
		return cnt;
	}
	


}
