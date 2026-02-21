package Graph.L3;

public class L3_경주로건설 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

// NxN => 0:빈칸 , 1 벽 
// (0,0) => (N-1,N-1)
// 직선도로는 100 ,코너는 500원 최소 비용계산 

import java.util.*;
class Solution {
	// 0,1,2,3 => 오, 아래 , 왼 , 위 
	int[] dy = {0,1,0,-1};
	int[] dx = {1,0,-1,0};
	
	int N;
	int[][][] D; 
	int[][] board;
	int MAX_V;
    public int solution(int[][] board) {
        int answer = 0;
        N = board.length;
        D = new int[N][N][4];
        MAX_V = 500*N*N;
        Arrays.fill(D, MAX_V);
        this.board = board;
        int c1 = dfs(0,0);
        int c2 = dfs(0,0);
        return answer;
        
    }
    
    public int dfs(int cur, int d) {
    	int cy = cur / N; int cx = cur % N;
    	
    	
    	//이미 방문한 경우
    	if(D[cy][cx][d] != MAX_V) return D[cy][cx][d];
    	
    	// 도착시 탐색 중지 
    	if(cy == N-1 && cx == N-1) {
    		return 0;
    	}
    	
    	// 현재 위치 방문 처리 
    	int res = MAX_V;
    	for(int nd = 0 ; nd<4 ; nd++) {
    		int ny = cy+dy[nd]; int nx = cx+dx[nd];
    		
    		if(ny>=0 && ny<N && nx>=0 && nx<N && board[ny][nx] == 0) {

				if(isCurve(d,nd)) {
    				res = Math.min(dfs(ny*N+nx , nd)+500 , res);
    			}else {
    				res = Math.min(dfs(ny*N+nx , nd)+100 , res);
    			}
    		}
    	}
    	D[cy][cx][d] = res;
    	return D[cy][cx][d];
    }
    
    public boolean isCurve(int d , int nd) {
    	// 현재 방향 , 다음 방향의 차이가 1이면 커브 발생. 
    	return Math.abs(d-nd) %2 == 1; 
    	
    	
    	
    }
}