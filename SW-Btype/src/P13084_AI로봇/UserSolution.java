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
class Robot{
	int idx;
	int baseScore;
	int time;
	int status; // 로봇 상태  => 0: 작업대기 ; , 1~50000: 작업 중 , -1:고장
	int wid;
	int v;
	
	public Robot(int idx , int score, int time , int v) {
		this.idx = idx;
		this.baseScore = score;
		this.time = time;
		this.status = 0;
		this.v = v;
	}
	
}


class UserSolution
{
	
   
	Robot[][] tasks;
	Robot[] robots;
	int[] robotVersion;
	int N; // 로봇 개수
	Queue<Robot> minQ;
	Queue<Robot> maxQ;
	
	// 로봇의 개수 . 모든 로봇은 센터에 있고 지능지수 0
	//  N : 로봇의 대수 (10 ≤ N ≤ 50,000)
	public void init(int N)
	{
		this.N = N;
		robots = new Robot[N+1];
		minQ = new PriorityQueue<Robot>((o1,o2)-> {
			int c1 = -o1.time +o1.baseScore;
			int c2 = -o2.time+ o2.baseScore;
			if(c1==c2) {
				return Integer.compare(o1.idx, o2.idx);
			}
			return Integer.compare(c1,c2);
		});
			
		maxQ = new PriorityQueue<Robot>((o1,o2)->{
			int c1 = -o1.time +o1.baseScore;
			int c2 = -o2.time+ o2.baseScore;
			if(c1==c2) {
				return Integer.compare(o1.idx, o2.idx);
			}
			return Integer.compare(c2,c1);
		});
		
		robotVersion = new int[N+1];
		
		for(int i = 1 ; i <=N ; i++) {
			robots[i] = new Robot(i , 0,0,1);
			// min , max 큐에 넣기 
			robotVersion[i]++;
			minQ.add(robots[i]);
			maxQ.add(robots[i]);
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
				r = maxQ.poll();
			}else { // 낮은 지능 우선 => 대기 시간이 큰 순서대로 
				r = minQ.poll();
			}
			//버전 다른거 제거
			if(robotVersion[r.idx] != r.v ) continue;
			// 대기 중인 로봇만 진행
			if(r.status != 0 ) continue;
			// 상태 변경 (작업중) 
			r.status = wID;
			r.baseScore += (cTime-r.time);
			r.time = cTime;
			r.wid = wID;
			tasks[wID][idx++] = r;
			
			// 정답 업데이트 
			res += r.idx;
		}
		return res;
	}

	public void returnJob(int cTime, int wID)
	{
		for(Robot r: tasks[wID]) {
			// 작업중 고장 난 로봇 예외 처리 
			if(r.status == -1 || r.wid != wID) continue;
			r.status = 0;
			r.time = cTime;
			r.wid = -1;
			r.v++;
			robotVersion[r.idx]++;
			maxQ.add(r);
			minQ.add(r);
		}
	}

	public void broken(int cTime, int rID)
	{
		// 작업 중인 로봇이 고장난 경우만 상태 변경 
		Robot r = robots[rID];
		if(r.status >= 1) {
			r.status = -1;
		}
	}

	public void repair(int cTime, int rID)
	{
		Robot r = robots[rID];
		if(r.status ==-1) {
			r.status = 0;
			r.baseScore= 0;
			r.time = cTime;
			r.v++;
			robotVersion[r.idx]++;
			maxQ.add(r);
			minQ.add(r);
		}
		
	}

	public int check(int cTime, int rID)
	{
		Robot r = robots[rID];
		if(r.status == -1) return 0; // 고장 
		else if(r.status >= 1) return r.wid*-1; // 작업 중 
		return (cTime - r.time) + r.baseScore;
	}
}