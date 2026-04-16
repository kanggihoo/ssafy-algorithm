package kakao;
import java.util.*;
public class L4_2019_무지의먹방라이브 {
	class Food implements Comparable<Food>{
		long t; int idx;
		Food(int t , int idx){
			this.t = t;
			this.idx = idx;
		}

		@Override
		public int compareTo(Food o) {
			// TODO Auto-generated method stub
			if(this.t == o.t) return Integer.compare(this.idx, o.idx);
			return Long.compare(this.t, o.t);

		}

	}

	public int solution(int[] food_times, long k) {
		PriorityQueue<Food> pq = new PriorityQueue<>();

		for(int i = 0 ; i < food_times.length ; i++) {
			pq.offer(new Food(food_times[i] , i+1));
		}

		// 큐가 비어 있으면 중단 , (큐에서 뺀 값의 음식 소비시간 - 현재 누적시간)*큐 size = 큐에서 빠질 음식을 전부 먹기 위해 필요한 시간
		long prevT = 0;
		Food last;
		while(!pq.isEmpty() && k >=(pq.peek().t-prevT)*pq.size()) {
			Food cur = pq.poll();
			if(pq.isEmpty()) last = cur;


			// 현재 반환된 음식을 먹기 위해 반복해야 하는 횟수 계산
			long cnt = cur.t - prevT;
			prevT = (long)cur.t;
			// k 업데이트
			k -= cnt*(pq.size()+1);
		}

		// 큐가 빈 경우 -1반환
		if(pq.isEmpty()) {
			return -1;
		}else{ // 큐에서 제거
			List<Food> res = new ArrayList<>();
			while(!pq.isEmpty()) res.add(pq.poll());

			// 리스트 정렬 (idx 기준으로)
			res.sort((e1,e2)->Integer.compare(e1.idx, e2.idx));

			// 남은 K가 0이되기 위해 움직여야 하는 거리 계산
			int ans = res.get((int) (k % res.size())).idx;
			return ans;

		}


	}

}
