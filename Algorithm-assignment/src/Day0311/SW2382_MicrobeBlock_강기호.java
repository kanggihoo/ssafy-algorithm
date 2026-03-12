package Day0311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class SW2382_MicrobeBlock_강기호 {

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
            	for(int j = 1 ; j<K ; j++) {
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
