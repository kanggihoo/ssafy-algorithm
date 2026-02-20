package Day0210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

//원자 움직임 시뮬레이션 
// 
//상 : y가 증가(아래방향) => 0
//하 : y가 감소(위로) => 1
//좌 : x 감소 => 2
//우 : x 증가  => 3
//모든 원자 이동속도 동일 (초당1칸)
//모든 원자는 동시에 이동
//2개 이상 원자 충돌시 각자 보유한 에너지 모두 방출후 소멸
//
//원자들의 처음 위치 [x, y] 는 -1,000 이상 1,000 이하의 정수로 주어진다. (-1,000≤x,y≤1,000)
//4. 원자들은 2차원 평면 위에서 움직이며 원자들이 움직일 수 있는 좌표의 범위에 제한은 없다. => 범위나가면 자연소멸하는걸로 
//
//
//원자들이 소멸되면서 방출하는 에너지의 총합구하라
//-> 이거 언제 영원히 충돌하지 않음을 판단하지 => 총 2000번 반복하면 되긴함. 알아서 밖으로 나가겠지


public class SW5648_AtomicExtinction_강기호 {
	static class A{
		int y;
		int x;
		int d;
		int k;
		boolean isL;
		
		public A(int x,int y,int d,int k) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.k = k;
		}
	}

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[] dy = {1,-1,0,0 };
		int[] dx = {0,0,-1,1};
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1 ; t<=T ; t++) {
			int[][] map = new int[2001][2001];
			int N = Integer.parseInt(br.readLine());
			A[] atoms = new A[N+1];
			for(int i = 1 ; i <= N ; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken())+1000;
				int y = Integer.parseInt(st.nextToken())+1000;
				int d = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				atoms[i] = new A(x,y,d,k);
				map[y][x] = i;
			}
			
			int ans =0;
			while(true) {
				
				// 이동
				for(int i = 1 ; i <= N ; i++) {
					A a = atoms[i];
					if(a.isL) continue;
					int ny = a.y+dy[a.d];
					int nx = a.x+dx[a.d];
					if(ny>=0 && ny<=2000 && nx>=0 && nx<=2000) {
						if(map[ny][nx]==0) {
							map[ny][nx] = i;
							map[a.y][a.x] = 0;
							a.y = ny;
							a.x = nx;
						}else {
							A nextA = atoms[map[ny][nx]];
							// 아직 안 움직인 경우 
							if(map[ny][nx] > i) {
								// 다음 원자 위치가 현재 원자랑 동일한경우
								if(a.y == nextA.y+dy[nextA.d] && a.x == nextA.x+dx[nextA.d]) {
									nextA.isL=true;
									a.isL = true;
									map[a.y][a.x] = 0;
									map[nextA.y][nextA.x] = 0;
									ans += (a.k+nextA.k);
								}else {
									map[ny][nx] = i;
									map[a.y][a.x] = 0;
									a.y = ny;
									a.x = nx;
								}
							}else { // 이미 움직였던경우 둘다 파괴
								a.isL = true;
						        ans += a.k;
						        map[a.y][a.x] = 0;
						        
						        // 이미 터진 놈이 아닐 때만 에너지를 더함 (중복 방지)
						        if (!nextA.isL) {
						            nextA.isL = true;
						            ans += nextA.k;
						        }
						        a.y = ny; a.x = nx;
							}
						}
					}else { // 범위 벗어난거처리
						a.isL = true;
						map[a.y][a.x] = 0;
					}
				}
				
				// 모든 원소가 벗어났는지 확인
				if(check(atoms,N))break;
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
	
	public static boolean check(A[] atoms, int N) {
		for(int i = 1; i<= N ; i++) {
			if(!atoms[i].isL) return false;
		}
		return true;
	}

}
