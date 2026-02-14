package P13084_AI로봇;

import java.util.PriorityQueue;
import java.util.*;

// 25�� �� 20�� �׽�Ʈ ��� ������ �ð��ʰ� 

class UserSolution
{
	R[] robots;
	Queue<R> maxHQ; 
	Queue<R> minHQ; 
	boolean[][] works;
	int N;
	List<R> stayR;
	Comparator<R> maxC = (r1, r2) ->{
		if(r1.score == r2.score) return r1.idx - r2.idx;
		return Integer.compare(r2.score, r1.score);
	};
	
	Comparator<R> minC = (r1, r2) ->{
		if(r1.score == r2.score) return r1.idx - r2.idx;
		return Integer.compare(r1.score, r2.score);
	};
	
	
	class R{
		int idx;
		int state; // 0: ��� , 1: ������ , 2: �۾���
		int score;
		int stayT; // ���� ���Խð� 
		public int wID;
		
		public R(int idx , int state , int score , int stayT) {
			this.idx = idx;
			this.state = state;
			this.score = score;
			this.stayT = stayT;
		}
		
		public void setW(int wID) {
			this.wID = wID;
		}
	}
	public void init(int N)
	{
		this.N = N;
		
		// �κ� �ʱ�ȭ 
		robots = new R[N+1];
		for(int i = 1 ; i<=N ; i++) robots[i] = new R(i,0,0,0);
		
		// ������� �κ� ����
		stayR = new ArrayList<>();
		for(int i = 0; i <N ; i++) stayR.add(robots[i]); 
		
		
	}

	public int callJob(int cTime, int wID, int mNum, int mOpt)
	{
		int res = 0;
		List<R> stayR = new ArrayList<>();
		for(int i = 1 ; i <=N ; i++) {
			R r = robots[i];
			if(r.state==0) {
				r.score += (cTime-r.stayT);
				r.stayT =cTime;
				stayR.add(r);
			}
		}
		
		if(mOpt == 0) stayR.sort(maxC); 
		else stayR.sort(minC);
		
		// mNum���� ���� ��ȯ
		for(int i = 0 ; i < mNum ; i++) {
			R r = stayR.get(i);
			r.state = 2;
			r.setW(wID);
			res += r.idx;
		}
		
		return res;
	}

	public void returnJob(int cTime, int wID)
	{
		for(int i = 1;  i<=N ; i++) {
			R r = robots[i];
			if(r.wID == wID && r.state==2) {
				r.stayT = cTime;
				r.state = 0;
			}
		}
	}

	public void broken(int cTime, int rID)
	{
		R r = robots[rID];
		if(r.state == 2) {
			r.state = 1;
		}
	}

	public void repair(int cTime, int rID)
	{
		R r = robots[rID];
		if(r.state==1) {
			r.state=0;
			r.score=0;
			r.stayT = cTime;
		}
	}

	public int check(int cTime, int rID)
	{
		R r = robots[rID];
		if(r.state == 1) return 0;
		else if(r.state == 2) return r.wID*(-1);
		else {
			return r.score + cTime-r.stayT;
		}
	
	}
}