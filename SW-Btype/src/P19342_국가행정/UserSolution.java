package P19342_국가행정;

class Road {
	int maxIdx;
	int maxTime; // 구간내 가장 큰 값 
	int rangeSum; // 구간내 합 
	
	Road(int a , int c , int d){
		this.maxIdx=a;
		this.maxTime = c;
		this.rangeSum = d;
	}
	
}


class UserSolution
{	
	int K;
	Road[] roadSeg;
	int[] roadCnt;
	int[] population;
	int[] cityAcc;
	
	// N개의 도시 고유번호는 0~N-1, 
	// 초기에 모두 하나의 차선으로 연결 
	void init(int N, int mPopulation[])
	{
		roadCnt = new int[N-1];
		population = mPopulation.clone();
		
		// k 값 구하기 
		K = 1;
		while(K < N) K <<=1;
		roadSeg = new Road[K<<1];
		
		
		// N-1개 도로 값 , i번 도시의 도로 개수 업데이트
		for(int i = 0 ; i < N-1 ; i++) {
			int time = population[i]+population[i+1];
			roadSeg[K+i] = new Road(i,time, time);
			roadCnt[i]++;
		}
		
		// 나머지 리프노드 초기화 
		for(int i = K+N-1 ; i<K<<1 ; i++) {
			roadSeg[i] = new Road(-1,0,0);
		}
		
		// 거꾸로 seg 트리 업데이트 
		for(int i =K-1; i>=1 ; i--) {
			Road r1 = roadSeg[i*2];
			Road r2 = roadSeg[i*2+1];
			int maxIdx = -1;
			int maxV =0;
			if(r1.maxTime >= r2.maxTime) {
				maxIdx = r1.maxIdx;
				maxV = r1.maxTime;
			}else {
				maxIdx = r2.maxIdx;
				maxV = r2.maxTime;
			}
			
			roadSeg[i] = new Road(maxIdx , maxV , r1.rangeSum+r2.rangeSum);
		}
		
		// 각 도시별 누적합 계산 
		cityAcc = new int[N+1];
		for(int i = 1 ; i <=N ; i++) {
			cityAcc[i] = cityAcc[i-1] + population[i-1];
		}
		
		
		return;
	}
	
	// 이웃한 도시 간의 이동 시간이 가장 긴 도로를 한 차선 확장을 M번 반복 (1~3)
	// 이동 시간이 같은게 여러개면 고유 번호가 가장 도시 선택 
	int expand(int M)
	{
		// 구간내 가장 큰 값은 1번 인덱스에 존재
		int ans =0;
		for(int step = 0 ; step < M ; step++) {
			Road maxR = roadSeg[1];
			int maxIdx = maxR.maxIdx;
			// maxIdx가 거쳐간 노드 업데이트
			roadCnt[maxIdx]++;
			int cur = K+maxIdx;
			int updatedV = (population[maxIdx] + population[maxIdx+1])/ roadCnt[maxIdx];
			roadSeg[cur].maxTime =  updatedV;
			roadSeg[cur].rangeSum = updatedV;
			cur /=2;
			while(cur >=1) {
				Road r1 = roadSeg[cur*2];
				Road r2 = roadSeg[cur*2+1];
				int Idx = -1;
				int maxV =0;
				if(r1.maxTime >= r2.maxTime) {
					Idx = r1.maxIdx;
					maxV = r1.maxTime;
				}else {
					Idx = r2.maxIdx;
					maxV = r2.maxTime;
				}
				roadSeg[cur].maxIdx = Idx;
				roadSeg[cur].maxTime = maxV;
				roadSeg[cur].rangeSum = r1.rangeSum+r2.rangeSum;
				cur /=2;
			}
			
			ans = updatedV;
		}
		return ans;
	}
	
	int calculate(int mFrom, int mTo)
	{
		int sIdx = Math.min(mFrom, mTo)+K;
		int eIdx = Math.max(mFrom, mTo)-1+K;
		int ans = 0;
		while(sIdx <= eIdx) {
			if(sIdx%2 == 1) {
				ans += roadSeg[sIdx].rangeSum;
				sIdx++;
			}
			sIdx /=2;
			
			if(eIdx %2 ==0) {
				ans += roadSeg[eIdx].rangeSum;
				eIdx--;
			}
			eIdx /=2;
		}
		return ans;
	}
	
	// mFromt 부터 mTO 까지 도시에 대해 K개 선거구 결정 
	
	int divide(int mFrom, int mTo, int K)
	{
		int sIdx = mFrom+1;
		int eIdx = mTo+1;
		
		// 이분탐색에 사용될 최소, 최대 범위 
		int R = cityAcc[eIdx] - cityAcc[sIdx-1];
		int L = 0;
		for(int i = mFrom ; i<=mTo ; i++) {
			L = Math.max(L, population[i]);
		}

		while(L<=R) {
			int mid = L + (R-L)/2;
			int res = check(mid,sIdx, eIdx);
			if(res <= K) { // mid 값으로 돌렸을때 선거구가 K보다 작은 경우는 기준이 너무 널널하니 줄이고
				R = mid-1;
			}else { // K보다 크면 기준이 빡빡한거니 늘리고 
				L = mid+1;
			}
		}
				
		return R+1;
	}
	
	int check(int t , int s , int e) {
		// s~e 구간내에서 구간합이 t보다 작아지는 개수 몇개인지 
		int L = s;
		int ans=0;
		while(L<=e) { // L이 e보다 커질때까지 반복
			
			int l1 =L;
			int r1 = e;
			while(l1 <= r1) {
				int mid = (l1+r1)/2;
				int res =cityAcc[mid] - cityAcc[L-1];  
				if(res > t) { // 너무 크니 범위 줄이기
					r1 = mid-1;
				}else { // 작은 경우에는 범위 늘리기
					l1 = mid+1;
				}
			}
			ans++;
			L = l1;
		}
		
		return ans;
	}
}