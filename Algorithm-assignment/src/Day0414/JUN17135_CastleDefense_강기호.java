package Day0414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * NxM 격자판 (각 칸에 적은 최대 1명)
 * N번 행의 아래 모든 칸은 성이 존재 
 * 
 * 성을 지키기 위한 궁수 3명 배치 => 성에만 배치하고 최대 1명 배치가능
 * 각 턴마다  적 하나 공격 가능하고 모든 궁수 동시에 공격
 * 궁수가 공격하는 적은 거리가 D 이하인 적 중에서 가장 가가운 적, 여러명 있으면 가장 왼쪽에 있는 적을 공격 
 * 궁수가 동일한 적을 동시에 공격 가능, 공격받은 적은 제외 , 
 * 궁수 공격 종료시 적 이동  => 아래로 1칸 
 * 성이 있는 칸으로 이동한 경우 게임에서 제외, 모든 적이 제외되면 게임 종료 
 * 
 * 궁수의 공격으로 제거가능한 적의 최대 수를 계산 
 */
import java.util.*;
public class JUN17135_CastleDefense_강기호 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 궁수 배치 (M개 중에서 3개 선택)
		List<Integer> P = new ArrayList<>();
		int ans = 0;
		for(int i = 7 ; i < 1<<M ; i++) {
			if(Integer.bitCount(i) != 3) continue;
			
			int idx = 0;
			for(int j = 0 ; j < M ; j++) {
				if((i & 1<<j) !=0) P.add(j);
			}
			
			// 궁수 위치가지고 시뮬레이션
			ans = Math.max(ans, simul(N,M,D,P , map));
			P.clear();
		}
		System.out.print(ans);
		
	}
	
	public static int simul(int n , int m , int d , List<Integer>P , int[][]mapO) {
		int [][] map = new int[n][m];
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m ; j++) {
				map[i][j] = mapO[i][j];
			}
		}
		
		
		
		// t번 반복
		int cnt = 0;
		for(int t = 0 ; t<n ; t++) {
			// 궁수 사격
			Set<Integer> set = new HashSet<>();
			for(int i = 0 ; i < 3 ; i++) {
				
				int p = P.get(i);
				int minD = d+1;
				int minC = m;
				int target =-1;
				for(int c = p-d ; c<= p+d ; c++) {
					for(int r = n-1 ; r > n-1-d ; r--) {
						if(c < 0 || c>= m || r <0) continue;
						if(map[r][c] != 1) continue;
						int dist = n-r+Math.abs(p-c);
						if(dist > d) continue;
						if(dist < minD) {
							target = r*m+c;
							minD = dist;
							minC = c;
						}else if(dist == minD && c < minC) {
							minC = c;
							target = r*m+c;
						}
					}
				}
				
				if(target !=-1) set.add(target);
			}
			
			cnt += set.size();
			for(int e : set) {
				map[e/m][e%m] = 0;
			}
			
			// 적 이동
			for(int col = 0 ; col<m ; col++) {
				for(int row = n-2 ; row >=0  ; row--) {
					map[row+1][col] = map[row][col];
				}
				map[0][col] = 0;
			}
		}
		
		return cnt;
	}

}
