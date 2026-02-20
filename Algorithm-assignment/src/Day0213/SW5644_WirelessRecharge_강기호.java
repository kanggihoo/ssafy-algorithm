package Day0213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//10*10 지도에 배터리 설치(위치 , 충전 범위 , 성능)
//- (1,1) 부터 시작
//충전기의 충전 범위가 주어지면 맨헤튼 거리범위내의 영역 충전가능. (자기 위치 포함해서 범위계산)
//
//사용자 A,B 이동궤적이 주어질때 (초당) , 매초마다 충전 범위 들어오면 BC에 접속가능. 이때 접속한 BC의 성능 만큼 충전 가능. 
//BC에 두명의 사용자가 접속하면 사용자수만큼 충전 양을 분배 
//이동궤적 주어질때 모든 사용자가 충전한 양의 최댓값 

//사용자 초기 위치는(0,0) , (9,9)
// BC개수는  1~ 8개 
// 성능은 짝수 
// 초기 위치부터 충전가능 
// 같은 위치에 2개 설치된경우 없음, 사용자 A,B 동시 같은 위치는 가능 
public class SW5644_WirelessRecharge_강기호 {
	

	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		


		int[]u1 = new int[101]; // 원점
		int[]u2 = new int[101]; // 끝점
		int[][] bcInfo = new int[8][4]; 
		int[] dy = {0,-1,0,1,0};
		int[] dx = {0,0,1,0,-1};

		int T = Integer.parseInt(br.readLine());
		for(int t=1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			
			int totalT = Integer.parseInt(st.nextToken());
			int bcCnt =  Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i =0;i<totalT ;i++) {
				u1[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i =0;i<totalT ;i++) {
				u2[i] = Integer.parseInt(st.nextToken());
			}
			
			// BC 정보  (y,x,범위,파워)
			for(int i=0 ; i < bcCnt ; i++) {
				st = new StringTokenizer(br.readLine());
				int bx = Integer.parseInt(st.nextToken())-1;
				int by = Integer.parseInt(st.nextToken())-1;
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bcInfo[i][0] = by;
				bcInfo[i][1] = bx;
				bcInfo[i][2] = c;
				bcInfo[i][3] = p;
			}
			
			// bc 정보를 바탕으로 map 초기화
			int[][] bcState = new int[10][10];
			int[][] map = new int[10][10];
			for(int i = 0 ; i< bcCnt ; i++) {
				int[] bc = bcInfo[i];
				int y = bc[0];
				int x = bc[1];
				int range = bc[2];
				int p = bc[3];
				for(int ddy = -range ; ddy<=range ; ddy++) {
					for(int ddx = -range ; ddx<=range ;ddx++) {
						int ny = y+ddy;
						int nx = x+ddx;
						if(Math.abs(ddy)+Math.abs(ddx) > range) continue;
						if(ny>=0 && ny<10 && nx>=0 && nx<10) {
							map[ny][nx] += p;
							bcState[ny][nx] = bcState[ny][nx] | (1<<i); 
						}
					}
				}
			}
			
			
			// 사람 이동
			int u1y=0; int u1x = 0;
			int u2y=9; int u2x=9;
			int ans=0;	
			for(int step = 0 ; step < totalT+1 ; step++) {
				
				// 현재 위치에서 가능한 조합 계산
				int u1State = bcState[u1y][u1x];
				int u2State = bcState[u2y][u2x];
				int maxV=0;
				if(u1State == 0) {
					for(int i = 0 ; i < bcCnt ; i++) {
						if((u2State & 1<<i) !=0  && maxV < bcInfo[i][3]) maxV = bcInfo[i][3]; 
					}
				}else if(u2State ==0 ) {
					for(int i = 0 ; i < bcCnt ; i++) {
						if((u1State & 1<<i) !=0  && maxV < bcInfo[i][3]) maxV = bcInfo[i][3]; 
					}
				}else {
					for(int i=0 ; i<bcCnt ; i++) {
						for(int j = 0 ; j<bcCnt ; j++) {
							int tmp=0;
							if((u1State & 1<<i) !=0 && (u2State & 1<< j) !=0) {
								if(i==j) { // 동일한 bc 고른 경우 
									tmp += bcInfo[i][3];
								}else tmp += bcInfo[i][3]+ bcInfo[j][3];
							}
							if(maxV < tmp) maxV = tmp;
						}
					}
					
				}
				
				ans += maxV;
				// 다음 위치 업데이트 
				if(step==totalT)break;
				int u1d = u1[step];
				int u2d = u2[step];

				u1y +=dy[u1d];
				u1x +=dx[u1d];
				
				u2y +=dy[u2d];
				u2x +=dx[u2d];
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
	

}
