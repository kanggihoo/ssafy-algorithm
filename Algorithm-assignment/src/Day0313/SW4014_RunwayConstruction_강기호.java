package Day0313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
/*
 * NxN 활주로 건설 셀의 숫자는 그 지형의 높이 
 * 가로 또는 세로 방향으로 건설 여부 가능성 확인 
 * 활주로는 높이가 동일한 구간에서 건설 가능. 높이가 다르면 경사로 설치해야됨. (경사로 높이는1)
 * 
 * 한쪽 방향으로 이동하면서 다른 값 나오기 전까지 이동, 이때 다른값인데 1보다 크게 나면 안됨.
 * 1차이 나는 경우에는 경사로를 어느 방향으로 놓는지 진행방향기준 올라가는 경우, 내려가는 경우 체크 필요. 
 * 올라가야 하는 경에는 현재위치 기준 -x+1이 범위 벗어나지 않는지 확인 
 * 내려가는 경우에는 현재위치 + x가 범위보다 작아야 하고 그때의 높이가 모두 같아야함. 
 * 특정 열 , 행 검토후에는 다시 되돌리는 작업 해주어야함. => 아니면 원본 변경하지 않고도 가능하지 않나? 
 */
public class SW4014_RunwayConstruction_강기호 {
	static int[][] map;
	static boolean[][] V;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T =Integer.parseInt(br.readLine());
		
		for(int t=1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			V = new boolean[N][N];
			for(int i = 0 ; i< N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
				
			// 가로 방향 확인 (왼쪽 -> 오른쪽)
			int ans = 0;
			for(int i = 0 ; i < N ; i++) {	
				boolean flag=true;
				for(int j = 1; j<N ; j++) {
					if(map[i][j] == map[i][j-1]) continue;
					
					if(Math.abs(map[i][j] - map[i][j-1]) > 1) {
						flag=false;
						break;
					}
					if(map[i][j-1] < map[i][j]) { // 오르막길 
						if(j-X <0) {
							flag=false;
							break; 
						}
						int comp = map[i][j-1];
						if(!check(comp , i ,j-2,X-1 , -1 ,0)) {
							flag=false;
							break;   
						}else {
							V[i][j-1] = true;
						}
					}else if(map[i][j-1] > map[i][j]) { // 내리막길
						if(j+X-1>=N) {
							flag=false;
							break;
						}
						int comp = map[i][j];
						if(!check(comp , i , j+1 , X-1 , 1,0)) {
							flag=false;
							break; 
						}else {
							V[i][j] = true;
						}
					}
				}
				if(flag) {
					ans++;
//					System.out.println(i);
				}
				
			}
			
			// 아래 방향 확인
			V = new boolean[N][N];
			int[] tmp = new int[N];
			for(int i = 0 ; i < N ; i++) {	
				boolean flag=true;
				for(int j = 1; j<N ; j++) {
					if(map[j][i] == map[j-1][i]) continue;
					
					if(Math.abs(map[j][i] - map[j-1][i]) > 1) {
						flag=false;
						break;
					}
					if(map[j-1][i] < map[j][i]) { // 오르막길 
						if(j-X <0) {
							flag=false;
							break; 
						}
						int comp = map[j-1][i];
						if(!check(comp , i , j-2,X-1 , -1 ,1)) {
							flag=false;
							break;   
						}else {
							V[j-1][i] = true;
						}
					}else if(map[j-1][i] > map[j][i]) { // 내리막길
						if(j+X-1>=N) {
							flag=false;
							break;
						}
						int comp = map[j][i];
						if(!check(comp , i , j+1 , X-1 , 1 ,1)) {
							flag=false;
							break; 
						}else {
							V[j][i] = true;
						}
					}
				}
				if(flag) {
					ans++;
//					System.out.println(i);
				}
				
			}
			
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);

	}
	
	public static boolean check(int comp , int o, int s , int times , int v , int d) {
		if(d==0) { // 가로 방향
			for(int i = 0 ; i < times ; i++) {
				if(map[o][s+i*v] != comp || V[o][s+i*v]) return false;
			}
			for(int i = 0 ; i < times ; i++) {
				V[o][s+i*v] = true;
			}
			return true;
		}else { // 세로 방향
			for(int i = 0 ; i < times ; i++) {
				if(map[s+i*v][o] != comp || V[s+i*v][o]) return false;
			}
			for(int i = 0 ; i < times ; i++) {
				V[s+i*v][o] = true;
			}
			return true;
		}
	}
	
	

}
