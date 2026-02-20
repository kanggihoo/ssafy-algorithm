package P24615;


import java.util.*;


class UserSolution
{
	private int[] dx = {0,0,1,-1};
	private int[] dy= {1,-1,0,0};
	private List<int[]>[] G = new ArrayList[201]; // 게이트 간의 거리 그래프 표현
	private int maxS;
	private int N;
	private int[][] M;
	private int[][] Gate;
	private boolean[] isActive;
	private int[][] V;
	private int visitedStamp=0;
	
	
	void init(int N, int mMaxStamina, int mMap[][])
	{
		maxS = mMaxStamina;
		this.N = N;
		M = new int[N][N];
		Gate = new int[N][N];
		isActive = new boolean[201];
		V = new int[N][N];
		for(int i = 0; i < N ; i++) {
			for(int j = 0 ; j <N ; j++) {
				M[i][j] = mMap[i][j];
			}
		}
		for(int i = 0 ; i<201; i++) {
			G[i] = new ArrayList<>();
		}
		
		return;
	}

	void addGate(int mGateID, int mRow, int mCol)
	{
		// 새로운 게이트 초기화시 BFS를 통해 최대 스테미나 까지 탐색해서 다른 게이트 간의 거리 계산
		isActive[mGateID] = true;
		Gate[mRow][mCol] = mGateID;
		bfs(mGateID , mRow , mCol);
		
		return;
	}

	void removeGate(int mGateID) // 해당 게이트 비활성화 
	{
		isActive[mGateID] = false;
		return;
	}

	int getMinTime(int mStartGateID, int mEndGateID) // 다익스트라 이용해서 최단거리 계산 및 반환 
	{
		// G에 담긴 게이트 간의 최단거리 정보를 이용해서 시작게이트 -> 목표 게이트 까지의 최단거리 구하기
		Queue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[1], b[1]));
		int[] D = new int[201];
		for(int i = 0 ; i <= 200; i++) D[i] = Integer.MAX_VALUE;
		
		// 출발 게이트 초기화
		D[mStartGateID] = 0;
		pq.offer(new int[] {mStartGateID , 0});
		
		// 다익스트라
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0];
			int d = cur[1];
			
			if(D[node] < d) continue;
			
			for(int[] edge : G[node]) {
				int adj = edge[0];
				int w = edge[1];
				if(!isActive[adj]) continue;
				
				if(D[adj] > D[node]+w) {
					D[adj] = D[node]+w;
					pq.offer(new int[] {adj,D[adj]});
				}
			}
			
		}
		return D[mEndGateID] == Integer.MAX_VALUE ? -1: D[mEndGateID];
	}
	
	void bfs(int id , int row , int col) {
		// 여기서 방문 배열 확인을 매번 초기화 안하고 누적값을 이용해서 좀더 최적화 가능??? 
		visitedStamp++;
		Queue<int[]> q = new ArrayDeque<>();
		V[row][col] = visitedStamp;
		q.offer(new int[] {row,col,0});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cy = cur[0] ; int cx = cur[1] ; int d = cur[2];
			
			// 동일한 엣지가 올수도 있다? 
			if(Gate[cy][cx] !=0 && Gate[cy][cx] != id) {
				int gateId = Gate[cy][cx] ;
				G[id].add(new int[] {gateId , d});
				G[gateId].add(new int[] {id , d});
			}
			if(d == maxS) continue;
			
			for(int i = 0 ; i < 4 ; i++) {
				int ny = dy[i]+cy;
				int nx = dx[i]+cx;
				if(ny>=0 && ny<N && nx>=0 && nx<N && M[ny][nx] == 0 && V[ny][nx]!=visitedStamp) { // 이동가능
					V[ny][nx] = visitedStamp;
					q.offer(new int[] {ny,nx,d+1});
				}
			}
				
		}
		
		return;
		
	}
}