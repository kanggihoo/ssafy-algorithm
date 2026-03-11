package UF.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * 섬으로 이루어진 나라, 모든 섬을 다리로 연결 (NxM) 
 * 다리는 바다에만 건설 가능. 다리의 길이는 격자에서 차지하는 칸의 수, 모든 섬을 연결 
 * 다리 연결시 다리 방향이 변경되면 안되고 다리 길이는 2이상 
 * 
 	1 ≤ N, M ≤ 10
	3 ≤ N×M ≤ 100
	2 ≤ 섬의 개수 ≤ 6
	
	=> 섬의 개수 -1개의 엣지가 있어야 하고
	=> 모든 조합의 수는 (n-1)! 개로 완탐가능. 
	=> (a,b)가 연결이 가능한지 판단 
		- 같은 x
 */
import java.util.*;
public class G1_17472 {
	static int[] P;

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[][] D = new int[6+1][6+1];
		for(int i = 1 ; i <=6 ; i++) {
			for(int j = 1 ; j<=6 ; j++) {
				D[i][j] = Integer.MAX_VALUE;
			}
		}
		
		// map 초기화
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j<M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] dy = {0,0,1,-1};
		int[] dx = {1,-1,0,0};
		
		// 그룹 탐색
		boolean[] V = new boolean[N*M];
		int[][] G = new int[N][M];
		int gIdx=1; 
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(map[i][j] ==1 && !V[i*M+j]) {
					V[i*M+j] = true;
					Queue<Integer> q = new ArrayDeque<Integer>();
					q.offer(i*M+j);
					while(!q.isEmpty()) {
						int cur = q.poll();
						int cy = cur/M; int cx = cur%M;
						G[cy][cx] = gIdx;
						for(int d=0 ; d<4 ; d++) {
							int ny = cy+dy[d];
							int nx = cx+dx[d];
							if(ny<0 || ny>=N || nx<0 || nx>=M) continue;
							if(map[ny][nx] ==1 && !V[ny*M+nx]) {
								q.offer(ny*M+nx);
								V[ny*M+nx] = true;
							}
						}
					}
					gIdx++;
				}
			}
		}
		int landCnt = gIdx - 1; // 실제 찾아낸 섬의 총 개수
		// 1인 모든 점에서 거리 탐색
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j <M; j++) {
				if(map[i][j] ==0) continue;
				// i,j에서 4방향으로 탐색
				int curG = G[i][j];
				for(int d = 0 ; d<4 ; d++) {
					int ny = i+dy[d];
					int nx = j+dx[d];
					int dist = 1;
					while(ny>=0 && ny<N && nx>=0 && nx<M ) {
						// 다음 위치가 1인 경우
						if(map[ny][nx] ==1) {
							// 거리가 2보다 크고, 서로 다른 지역인 경우
							int compG = G[ny][nx];
							if(curG!= compG && dist >2) {
								D[curG][compG] = Math.min(D[curG][compG] , dist-1);
								D[compG][curG] = Math.min(D[compG][curG] , dist-1);
							}
							break;
						}
						ny += dy[d];
						nx += dx[d];
						dist++;
					}	
				}
			}
		}
		

		
		// 최종 연산 (간선 집합을 가중치 기준으로 정렬 후 크루스칼 적용)
		List<int[]>edges = new ArrayList<>();
		for(int i = 1 ; i <=landCnt-1 ; i++) {
			for(int j = i+1 ; j <=landCnt ; j++) {
				if(D[i][j] == Integer.MAX_VALUE) continue;
				edges.add(new int[] {i,j,D[i][j]});
			}
		}
		edges.sort((e1,e2)->Integer.compare(e1[2], e2[2]));
		int eCnt = 0;
		int ans=0;
		P = new int[gIdx];
		for(int i = 1 ; i<=gIdx-1 ; i++) P[i] = i;
		
		for(int[] e : edges) {
			int n1 = e[0];
			int n2 = e[1];
			if(find(n1) != find(n2)) {
				union(n1, n2);
				eCnt++;
				ans+=e[2];
			}
			if(eCnt == landCnt-1) break;
		}
		// eCnt 개수가 gIdx -1이 안되는 경우 모든 섬 연결 불가능
		if(eCnt != landCnt-1) ans =-1;
		System.out.print(ans);
		
	}
	
	public static int find(int a) {
		if(P[a] != a) {
			P[a] = find(P[a]);
		}
		return P[a];
		
	}
	
	public static void union(int n1 , int n2) {
		int p1 = find(n1);
		int p2 = find(n2);
		if(p1 != p2) {
			if(p1<p2) {
				P[p2] = p1;
			}else {
				P[p1] = p2;
			}
		}
	}

}
