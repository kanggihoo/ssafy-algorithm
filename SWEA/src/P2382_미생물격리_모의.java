import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 정사각형 구역 안에 K개의 미생물 군집 (NxN)
// 미생물이 구역을 빠져나가지 못하게 바깥 가장자리는 약품 칠해져 있음. (NxN에 가장자리 포함해서)
// 최초 각 미생물 군집 위치, 군집내 미생물 수 , 이동방향 주어짐. (상,하,좌,우)
// 1시간마다 다음 셀로 이동 
// 이동 후 약품이 칠해진 셀에 도착시 군집 내 미생물 절반 죽고, 이동방향 반대 (홀수면 버림연산)
// 두 개 이상 군집이 한 셀에 모이는 경우 군집들은 합쳐짐. 이동방향은  미생물이 가장 많은 군집의 이동방향으로 
// -> 이때 합쳐지는 군집의 미생물 수가 같은 경우는 없음
// M시간 동안 후 남아 있는 미생물 수의 총합구하기 
// 상:1 , 하:2 , 좌:3 , 우:4
// 0,1,2,3
import java.util.*;
public class P2382_미생물격리_모의 {
	
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	public static class Group implements Comparable<Group>{
		int y;
		int x;
		int d;
		int n;
		boolean isDead;
		public Group(int y, int x, int n,int d) {
			this.y = y;
			this.x = x;
			this.n = n;
			this.d = d;
		}
		@Override
		public int compareTo(Group o) {
			// TODO Auto-generated method stub
			if(!this.isDead && o.isDead) return -1; 
			if(this.isDead && !o.isDead) return 1;
			
			if(this.y != o.y) return Integer.compare(this.y, o.y);
			if(this.x != o.x) return Integer.compare(this.x, o.x);
			return Integer.compare(o.n, this.n);
		}
		
		
		
		
	}

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        for(int t = 1 ; t<=T ; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 셀 개수 
            int M = Integer.parseInt(st.nextToken()); // 격리 시간
            int K = Integer.parseInt(st.nextToken());  // 군집 개수
            Group[] G = new Group[K];
            
            for(int i = 0 ; i < K ; i++) {
            	st = new StringTokenizer(br.readLine());
            	int y = Integer.parseInt(st.nextToken());
            	int x = Integer.parseInt(st.nextToken());
            	int n = Integer.parseInt(st.nextToken());
            	int d = Integer.parseInt(st.nextToken())-1;
            	G[i] = new Group(y,x,n,d);
            }
            
            // M 번 반복
            for(int i = 0 ; i < M ; i++) {
            	
            	// 군집 이동
            	for(int idx = 0 ; idx<K ; idx++) {
            		Group g = G[idx];
            		if(g.isDead) continue;
            		
            		int ny = g.y+dy[g.d];
            		int nx = g.x+dx[g.d];
            		
            		// 경계 자리 
            		if(ny ==0 || ny == N-1 || nx ==0 || nx ==N-1) {
            			// 방향 변환, 개체 감소
            			g.d = ((g.d)%2+1)%2+(g.d/2)*2;
            			g.n /=2;
            			if(g.n ==0) {
            				g.isDead = true;
            				continue;
            			}
            		}
            		g.y = ny;
            		g.x = nx;
            	}
            	
            	// 군집 이동 후 정렬
            	Arrays.sort(G);
            	
            	// 동일한 위치에 여러 군집 있는 경우 업데이트
            	Group g = G[0];
            	for(int j = 1 ; j<K ; i++) {
            		Group ng = G[j];
            		if(ng.isDead) break;
            		if(g.y == ng.y && g.x == ng.x) {
            			g.n += ng.n;
            			ng.isDead= true;
            		}else {
            			g = ng;
            		}
            	}
            }
            
            // 최종 남은 군집 카운팅
            int ans = 0;
            for(Group g : G) {
            	if(g.isDead) continue;
            	ans += g.n;
            }
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }

}
