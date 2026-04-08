package Day0408;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
고객이 지갑을 두고감. 
차량 정비소 고객이 너무 많아 모두 전화 불가능
고객만족도 설문지에 고객이 이용한 접수 창구와 , 정비 창구번호 존재  => 이 정보 이용해서 전화할 고객 선정
N개의 접수 창구(1~N) , M개의 정비 창구(1~M)
 -> 접수 창구에서 고장 접수 후 정비 창구에서 차량 정비 
 -> 정비 끝나면 고객만족도 설문지 받음 

접수 창구 , 정비 창구 담당자 업무 능력이 달라 담당자 별 처리 시간 다름. 
접수 창구 i에서 고정 접수하는데 걸리는 시간 ai , 차량 정비는 bj

방문 고객은 K명
- 고객이 도착하는대로 1번 부터 번호표 
- k번 고객이 차량 정비소에 도착하는 시간은 tk 

접수 창구의 우선순위
1. 여러 고객이 기다리는 경우 고객번호 낮은 순서대로 
2. 빈 창구가 여러개면 접수번호가 작은 곳으로 이동 

정비 창구 우선순위
1. 먼저 기다리는 고객 우선 
2. 동시에 두명 고객이 이동 한 경우에는 접수 창구번호가 작은 고객 우선
3. 빈 창구 여러개면 작은 거를 먼저 

- 접수=>정비 창구 이동시간 0 
도착시간 tk , ai , bj 주어질때 지갑 분실한 고객과 같은 접수 , 정비 창구 이용한 고객의 고객번호 찾아 그 합을 출력 
없으면 -1출력  
*/

class Custom implements Comparable<Custom>{
	int idx;
	int rcIdx;
	int rpIdx;
	int time;
	
	public Custom(int idx , int time) {
		this.idx = idx;
		this.time =time;
	}

	@Override
	public int compareTo(Custom o) {
		if(this.time == o.time) return Integer.compare(this.idx, o.idx);
		return Integer.compare(this.time, o.time);
	}
	
}

public class SW2477_AutoRepairShop_강기호 {
	
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T =Integer.parseInt(br.readLine());
		for(int t=1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 접수 창구 개수
			int M = Integer.parseInt(st.nextToken()); // 정비 창구 개수 
			int K = Integer.parseInt(st.nextToken()); // 고객수 
			int tRCidx = Integer.parseInt(st.nextToken()); // 목표 고객 이용 창구번호
			int tRPidx = Integer.parseInt(st.nextToken()); // 목표 고객 이용 정비창구번호
			
			int[] rcT = new int [N+1];
			int [] rpT = new int[M+1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1 ; i <= N ; i++) rcT[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i = 1 ; i <= M ; i++) rpT[i] = Integer.parseInt(st.nextToken());

			// 방문 고객
			Custom[] customs = new Custom[K+1];
			Queue<Custom> dq = new PriorityQueue<>();
			
			st =new StringTokenizer(br.readLine());
			for(int i = 1 ; i <= K ; i++) {
				int arriveT = Integer.parseInt(st.nextToken());
				customs[i] = new Custom(i, arriveT);
				dq.add(customs[i]);
			}
			Queue<Custom> rcq = new PriorityQueue<>((o1,o2)-> {
				if(o1.time==o2.time) return Integer.compare(o1.rcIdx, o2.rcIdx);
				return Integer.compare(o1.time,o2.time);
			});
			Queue<Custom> rdq = new PriorityQueue<>((o1,o2)-> {
				if(o1.time==o2.time) {
					return Integer.compare(o1.rcIdx, o2.rcIdx);
				}
				return Integer.compare(o1.time,o2.time);
			});
			Queue<Custom> rpq = new PriorityQueue<>((o1,o2)-> {
				if(o1.time==o2.time) return Integer.compare(o1.rpIdx, o2.rpIdx);
				return Integer.compare(o1.time,o2.time);
			});
			
			boolean[] rcqInfo = new boolean[N+1];
			boolean[] rpqInfo = new boolean[M+1];
			while(!dq.isEmpty()) {
				Custom c1 = dq.poll();
				
				// 대기 중인 사람이 들어오는 시간보다 작은거 전부 빼야함.
				while(!rcq.isEmpty() && rcq.peek().time <= c1.time) {
					Custom cc = rcq.poll();
					rcqInfo[cc.rcIdx] = false; // 빈 접수처 
					rdq.add(cc);
				}
				
				// 이제 큐가 비어있거나, 무조건 현재 시간보다 큼. 
				if(rcq.size()==N) {
					// 꽉찬경우 
					Custom cc = rcq.poll();
					c1.rcIdx = cc.rcIdx;
					c1.time = cc.time+rcT[cc.rcIdx];
					rdq.add(cc);
				}else {
					// 접수처 찾기 
					int idx = N+1;
					for(int i = 1 ; i <=N ; i++) {
						if(!rcqInfo[i]) {
							idx = i;
							break;
						}
					}
					c1.rcIdx = idx;
					c1.time += rcT[idx];
					rcqInfo[idx] = true; 
				}
				// 작업 넣기 
				rcq.add(c1);
			}
			while(!rcq.isEmpty()) {
				rdq.add(rcq.poll());
			}
			
			// 정비소 
			while(!rdq.isEmpty()) {
				Custom c1 = rdq.poll();
				
				// 대기 중인 사람이 들어오는 시간보다 작은거 전부 빼야함.
				while(!rpq.isEmpty() && rpq.peek().time <= c1.time) {
					Custom cc = rpq.poll();
					rpqInfo[cc.rpIdx] = false; // 빈 접수처 
				}
				
				// 이제 큐가 비어있거나, 무조건 현재 시간보다 큼. 
				if(rpq.size()==M) {
					// 꽉찬경우 
					Custom cc = rpq.poll();
					c1.rpIdx = cc.rpIdx;
					c1.time = cc.time+rpT[cc.rpIdx];
				}else {
					// 접수처 찾기 
					int idx = M+1;
					for(int i = 1 ; i <=M ; i++) {
						if(!rpqInfo[i]) {
							idx = i;
							break;
						}
					}
					c1.rpIdx = idx;
					c1.time += rpT[idx];
					rpqInfo[idx] = true; 
				}
				// 작업 넣기 
				rpq.add(c1);
			}
			
			// 최종 확인
			int ans = 0;
			for(int i =1 ; i <=K ; i++) {
				Custom c = customs[i];
				if(c.rcIdx == tRCidx && c.rpIdx == tRPidx) {
					ans += c.idx;
				}
			}
			ans = ans == 0? -1 :ans;
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
	
	

}
