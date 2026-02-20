package P14596_섬지키기;

import java.util.ArrayList;
import java.util.List;

class UserSolution
{
	class Structer{
		int[] r;
		int[] c;
		int[] h;
		
		public Structer(int M) {
			r = new int[M];
			c = new int[M];
			h = new int[M];
		}
			
	}
	public int[][] map = new int[21][21];
	public int N;
	public int[][] V;
	public int vCnt;
	public int[] dy= {0,1,0,1,-1};
	public int[] dx= {1,0,-1,0};
	public void init(int N, int mMap[][])
	{
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j <N ; j++) map[i][j] = mMap[i][j];
		}
		this.N = N;
		V = new int[N][N];
		
	}
	// 구조물이 모두 동일하거나 회전해도 동일한지 확인이 필요해.회전하는게 의미없는 경우 중복계산될 수도 있음. 
	public int numberOfCandidate(int M, int mStructure[])
	{
		Structer[] Shape = new Structer[4];
		for(int i = 0 ; i < 4 ; i++) Shape[i] = new Structer(M);
		
		for(int j = 0 ; j<M ; j++) {
			Shape[0].r[j] = 0;
			Shape[0].c[j] = j;
			Shape[0].h[j] = mStructure[j];
			
			Shape[1].r[j] = j;
			Shape[1].c[j] = 0;
			Shape[1].h[j] = mStructure[j];
			
			Shape[2].r[j] = 0;
			Shape[2].c[j] = j;
			Shape[2].h[j] = mStructure[M-1-j];
			
			Shape[3].r[j] = j;
			Shape[3].c[j] = 0;
			Shape[3].h[j] = mStructure[M-1-j];
		}
		
		// 만든 모양에서 중복 제거 후 최종 반환하는 Structer[] 를 반환하는것을 함수화 하기 
		
		
			
		return 0;
	}

	public int maxArea(int M, int mStructure[], int mSeaLevel)
	{
		// 구조물을 놓고 해수면 상승 시켜야 하는데 
		// 가로 방향 
	
		// 세로 방향 
		
		
		
		// 양 사이드 조사 
		vCnt ++;
		for(int i = 0 ; i < N ; i++) {
			if(map[0][i] > mSeaLevel-1) continue;
			
			dfs(0,i,mSeaLevel-1);
		}
		
		for(int j = 1 ; j<N ; j++) {
			
		}
		
		
		
		
		return 0;
	}
	
	public boolean check(int r,int c, int d , int[] S ) {
		int M = S.length -1;
		int ref = map[r][c]+S[0];
		int ny = r+dy[d];
		int nx = c+dx[d];
		if(d >= 2) {
			ny = r-dy[d]*(M);
			nx = c-dx[d]*(M);
			ref = map[ny][nx] + S[0];
			ny +=dy[d];
			nx +=dx[d];
		}
		for(int i = 0 ;  i<M; i++) {
			if(map[ny][nx]+S[i] != ref) return false;
			ny+=dy[d];
			nx+=dx[d];
		}
		return true;
	}
	
//	public void place( )
	public void dfs(int r , int c , int t) {
		
	}
}