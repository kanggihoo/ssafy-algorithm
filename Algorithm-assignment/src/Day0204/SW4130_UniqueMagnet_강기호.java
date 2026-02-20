package Day0204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

//4개 자석, 8개의 날, 각 날마다 N,S 가짐
//빨간색화살표에 날 하나가 오도록 배치 
//임의 자석 회전시 옆의 자석과 붙어 있는 라과 자성이 다를 경우 반대 방향으로 1칸 회전
//
//무작위로 자석돌리고 모든 회전끝난 후 아래와 같은 방법으로 점수 계산
//- 1 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 1 점을 획득한다.
//
//- 2 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 2 점을 획득한다.
//
//- 3 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 4 점을 획득한다.
//
//- 4 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 8 점을 획득한다.

//N극이 0 , S극이 1
//자성정보는 빨간색부터 시계방향순으로 
//1은 시계방향 , -1 반시계 방향

public class SW4130_UniqueMagnet_강기호 {
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <=T ; t++) {
			Deque<Integer>[] M = new ArrayDeque[4];
			for(int  i = 0 ; i < 4 ; i++) M[i] = new ArrayDeque<>();
			
			int K = Integer.parseInt(br.readLine());
			
			// 4개 자석 입력
			for(int i = 0 ; i < 4 ; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0 ; j <8 ; j++) {
					M[i].offer(Integer.parseInt(st.nextToken()));
				}
			}
			
			// K번 회전 
			for(int r = 0 ; r<K ; r++) {
				st = new StringTokenizer(br.readLine());
				int mIdx = Integer.parseInt(st.nextToken())-1;
				int d = Integer.parseInt(st.nextToken());
				
				// mIdx 자석 d로 회전(1:시계방향, -1: 반시계)
				List<int[]> tmp = new ArrayList<>();
				tmp.add(new int[] {mIdx, d});
				

				int[] originLR = getLRSign(M[mIdx]);
				int[] origin = new int[] {originLR[0] , originLR[1] , d};
				// 왼쪽방향으로 돌 수 있는 자석
				int[] comp = origin.clone();
				for(int i =mIdx-1 ; i>=0 ; i--) {
					int[] cur = getLRSign(M[i]);
					if(cur[1] == comp[0]) break;
					// 둘이 다른경우
					tmp.add(new int[] {i,comp[2]*-1});
					comp[0] = cur[0];
					comp[1] = cur[1];
					comp[2] = comp[2]*-1;
				}
				
				// 오른쪽 방향으로 돌 수 있는 자석
				comp = origin.clone();
				for(int i = mIdx+1 ; i<4 ; i++ ) {
					int[] cur = getLRSign(M[i]);
					if(cur[0] == comp[1]) break;
					// 둘이 다른경우
					tmp.add(new int[] {i,comp[2]*-1});
					comp[0] = cur[0];
					comp[1] = cur[1];
					comp[2] = comp[2]*-1;
				}
				
				// 돌릴 수 있는 것들 돌리기 
				for(int[] info : tmp) {
					rotate(M[info[0]], info[1]);
					
				}
			}
			// 최종 점수 계산
			int score = 0;
			for(int i = 0 ; i < 4 ; i++) {
				if(M[i].peekFirst() == 1) score += Math.pow(2, i);

			}
			sb.append("#").append(t).append(" ").append(score).append("\n");
		}
		
		System.out.print(sb);
		

	}
	
	public static int[] getLRSign(Deque<Integer> dq) {
		int cnt = 0;
		int l=0;
		int r=0;
		for(int i : dq) {
			
			if(cnt == 2) {
				r = i;
			}
			else if(cnt == 6) {
				l = i;
				break;
			}
			cnt++;
			
		}
		
		return new int[] {l,r};
		
	}
	
	public static void rotate(Deque<Integer> dq , int d) {
		if(d == 1) dq.offerFirst(dq.pollLast()); // 시계방향 
		else dq.offer(dq.poll());
	}

}
