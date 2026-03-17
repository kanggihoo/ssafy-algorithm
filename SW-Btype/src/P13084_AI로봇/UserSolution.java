package P13084_AI로봇;




 
/*
 * 1~N 번 로봇 고유번호 
 * 센터에 있을때 로봇 지능지수 1씩 증가 
 * => 작업에 투입 하는 경우 
 * 		1. 높은 지능 우선 , 지능 동일시 로봇 번호 가장 작은걸로 투입 
 * 		2. 낮은 지능 우선 , 지능 동일시 로봇 번호 가장 작은걸로 투입
 * 작업 중인 로봇은 고장날 수 있고, 고장이 나면 바로 센터 복귀 후 수리 완료 전까지 작업 투입X
 * 수리 완료시 로봇의 지능은 0이 됨. 
 */
import java.util.*; 
class Robot implements Comparable<Robot>{
	int idx;
	int baseScore;
	
	public Robot(int idx , int score) {
		this.idx = idx;
		this.baseScore = score;
	}

	@Override
	public int compareTo(Robot o) {
		// TODO Auto-generated method stub
		if(this.baseScore != o.baseScore) return Integer.compare(this.baseScore, o.baseScore);
		return Integer.compare(this.idx , o.idx);
	}	
	
}
class UserSolution
{
	
	int[] status; // 로봇 상태  => 0: 작업대기 ; , 1~50000: 작업 중 , -1:고장   
	Robot[][] tasks;
	int N; // 로봇 개수
	TreeSet<Robot> tree;

	
	// 로봇의 개수 . 모든 로봇은 센터에 있고 지능지수 0
	//  N : 로봇의 대수 (10 ≤ N ≤ 50,000)
	public void init(int N)
	{
		this.N = N;
		status = new int[N+1];
		tree = new TreeSet<>();
		for(int i = 1 ; i <=N ; i++) {
			tree.add(new Robot(i, 0));
		}
		tasks = new Robot[50001][];
		
	}
	
	// 현재 시각이 cTime이다. ID가 wID인 작업에 mNum대의 로봇을 투입, 해당 작업에 투입된 로봇들의 고유 번호를 모두 합한 값을 반환
	//mOpt = 0인 경우, 높은 지능 우선 방식으로 로봇을 투입한다.
	//mOpt = 1인 경우, 낮은 지능 우선 방식으로 로봇을 투입
	// wID : 작업 ID (1 ≤ wID ≤ 50,000)
	public int callJob(int cTime, int wID, int mNum, int mOpt)
	{
		tasks[wID] = new Robot[mNum];
		int idx = 0;
		int res = 0;
		while(idx < mNum) {
			Robot r= null;
			if(mOpt == 0) { // 높은 지능 우선 => 대기 시간이 작은걸로 
				r = tree.pollFirst();
			}else { // 낮은 지능 우선 => 대기 시간이 큰 순서대로 
				r = tree.pollLast();
			}
			// 대기 중인 로봇만 진행
			if(status[r.idx] != 0) continue;
			// 상태 변경 (작업중) 
			status[r.idx] = wID; 
			res += r.idx;
			
			r.baseScore = cTime-r.baseScore;
			tasks[wID][idx++] = r;
			idx++;
		}
		return res;
	}

	public void returnJob(int cTime, int wID)
	{
		for(Robot r: tasks[wID]) {
			int idx = r.idx;
			// 작업중 고장 난 로봇 예외 처리 
			if(status[idx] == -1) continue;
			status[r.idx] = 0;
			r.baseScore -= cTime;
			tree.add(r);
		}
	}

	public void broken(int cTime, int rID)
	{
		// 작업 중인 로봇이 고장난 경우만 상태 변경 
		if(status[rID] >= 1) {
			status[rID] = -1;
		}
	}

	public void repair(int cTime, int rID)
	{
		status[rID] = 0;
		
	}

	public int check(int cTime, int rID)
	{
		
		return 1;
	}
}