package P14596_섬지키기;

import java.util.ArrayList;
import java.util.List;

class UserSolution
{
	class Structer{
		int dx;
		int dy;
		int d;
		int[] v;
		
		// y:1이면 세로방향 , x:1이면 가로 방향블록 , d:0이면 정방향, 1이면 반대방향
		public Structer(int M , int y , int x , int d , int[] S) {
			this.dy = y;
			this.dx = x;
			this.d = d;
			v = new int[M];
			if(d==0) {
				for(int i = 0 ; i < M ; i++) v[i] = S[i];
			}else {
				for(int i = 0 ; i < M ; i++) v[i] = S[M-1-i];
			}
		}
			
	}
	public int[][] map = new int[21][21];
	public int N;
	public int[][] V;
	public int vCnt;
	public int[] dy= {0,1,0,-1};
	public int[] dx= {1,0,-1,0};
	public void init(int N, int mMap[][])
	{
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j <N ; j++) map[i][j] = mMap[i][j];
		}
		this.N = N;
		V = new int[N][N];
		
	}
	//  
	public int numberOfCandidate(int M, int mStructure[])
	{
		 
		List<Structer> Shape = generateShape(M , mStructure);
		
		// 설치 할 수 있는 구조물 개수 구하기 
		int ans =0;
		
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				for(Structer s : Shape) {
					
					if(s.dy == 1 && i+M>N) continue; // 세로 방향 구조물 놓을 수 있는지
					else if(s.dx == 1 && j+M>N) continue; // 가로 방향 구조물 놓을 수 있는지
					// 현재 구조물 놓을 수 있고 합이 동일한지 
					if(isPlace(i,j,s)) ans++;
				}
			}
		}
		
		return ans;
	}
	

	// 해수면이 mSeaLevl 만큼 상승해도 바다에 잠기지 않고 남는 지역 개수 최대가 되도록 구조물 설치
	public int maxArea(int M, int mStructure[], int mSeaLevel)
	{
		List<Structer> Shape = generateShape(M , mStructure);
		
		// 설치 할 수 있는 구조물 개수 구하기 
		int cnt =0;
		int ans = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				for(Structer s : Shape) {
					if(s.dy == 1 && i+M>N) continue; // 세로 방향 구조물 놓을 수 있는지
					else if(s.dx == 1 && j+M>N) continue; // 가로 방향 구조물 놓을 수 있는지
					// 현재 구조물 놓을 수 있고 합이 동일한지 
					if(isPlace(i,j,s)) {
						cnt ++;
						place(i,j,s,1);
						ans = Math.max(ans , getArea(mSeaLevel));
						place(i,j,s,-1);
					}
				}
			}
		}
		if(cnt == 0) return -1;
		
		
		return ans;
	}
	
	public List<Structer> generateShape(int M , int[] mStructure){
		// 현재 구조물이 대칭인지 확인 후 가능한 조합 확인 
		List<Structer> Shape = new ArrayList<>();
		if(M==1) {
			Shape.add(new Structer(M, 1, 0, 0, mStructure));
		}
		else if(isSame(M , mStructure)) {
			Shape.add(new Structer(M, 0, 1, 0, mStructure));
			Shape.add(new Structer(M, 1, 0, 0, mStructure));
		}else {
			Shape.add(new Structer(M, 0, 1, 0, mStructure));
			Shape.add(new Structer(M, 1, 0, 0, mStructure));
			
			Shape.add(new Structer(M, 0, 1, 1, mStructure));
			Shape.add(new Structer(M, 1, 0, 1, mStructure));
		}
		
		return Shape;
	}
	
	public boolean isPlace(int y , int x , Structer s) {
		int[] sV = s.v;
		int res = s.v[0]+map[y][x];
		if(s.dy == 1) { // 세로방향
			for(int i = 1 ; i <sV.length ; i++) {
				if(res != s.v[i]+map[y+i][x]) return false;
			}
		}else if(s.dx == 1) { // 가로 방향
			for(int i = 1 ; i <sV.length ; i++) {
				if(res != s.v[i]+map[y][x+i]) return false;
			}
		}
		return true;
	}
	
	public boolean isSame(int size , int[] S) {
		for(int i = 0 ; i < size/2 ; i++) {
			if(S[i] != S[size-1-i]) return false;
		}
		return true;
	}
	
	public void place(int y, int x , Structer s , int val ) {
		if(s.dy ==1) { // 세로방향
			for(int i = 0 ; i < s.v.length ; i++) {
				map[y+i][x] += val*s.v[i];
			}
		}else { // 가로 방향
			for(int i = 0 ; i < s.v.length ; i++) {
				map[y][x+i] += val*s.v[i];
			}
		}
		
	}
	public int getArea(int level) {
		// 양 사이드 조사해서 dfs 돌리기
		int y = 0; 
		int x = 0;
		int d = 0;
		
		vCnt++;
		int ans = N*N;
		for(int i = 0 ; i < 4*N-4 ; i++) {
			// 현재 위치에서 진행하고
			if(map[y][x] < level && V[y][x] != vCnt) {
				ans -= dfs(y,x,level);
			}
			
			// 다음 위치 업데이트
			int ny = y+dy[d];
			int nx = x+dx[d];
			
			if(ny<0 || ny>=N || nx<0 || nx>=N) {
				d++;
				ny = y+dy[d];
				nx = x+dx[d];
			}
			
			y = ny;
			x = nx;
		}
		
		
		return ans;
	}
	
	public int dfs(int y, int x , int level) {
		
		V[y][x] = vCnt;
		int res = 1;
		for(int i = 0 ; i < 4 ; i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			if(ny<0 || ny >=N || nx<0 || nx>=N) continue;
			if(map[ny][nx]< level && V[ny][nx] != vCnt) {
				res += dfs(ny,nx,level);
			}
		}
		return res;
		
	}
}