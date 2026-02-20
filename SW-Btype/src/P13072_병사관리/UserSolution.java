package P13072_병사관리;

class UserSolution {
	S[] members;
	LList[][] Smanager;
	
	

	class S {
		int mIdx;
		int mScore;
		int mTeam;
		boolean isFired;
		S next;
		S prev;

		S(int mIdx, int mTeam , int mScore) {
			this.mIdx = mIdx;
			this.mTeam = mTeam;
			this.mScore = mScore;
		}

		@Override
		public String toString() {
			return "S [mIdx=" + mIdx + ", mTeam=" + mTeam + ", mScore=" + mScore + "]";
		}
	}
	
	// 더미 있는 이중연결리스트로 변경
	class LList {
		S head = new S(-1,-1,-1);
		S tail = new S(-1,-1,-1);
		int size;

		public LList() {
			head.next = tail;
			tail.prev = head;
			tail.next = null;
			size = 0;
		}

		public void add_tail(S newNode) {
			S prevNode = tail.prev;
			prevNode.next = newNode;
			newNode.prev = prevNode;

			tail = newNode;
			newNode.next = null;
			size++;
		}
		
		public void add_LList(LList newLList) {
			S newhead = newLList.head;
			S newtail = newLList.tail;
			
			tail.next = newhead;
			newhead.prev = tail;
			
			// 현재 tail 위치 변경
			tail = newtail;
			tail.next = null;
		}

		public int search() {
			int ansIdx=0;
			int maxV=0;
			
			S cur = head.next;
			
			while(cur !=null) {
				if(cur.isFired) {
					cur = cur.next;
					continue;
				}
				if(cur.mScore > maxV) {
					maxV = cur.mScore;
					ansIdx = cur.mIdx;
				}else if(cur.mScore == maxV && ansIdx < cur.mIdx) ansIdx = cur.mIdx;
				cur = cur.next;
			}
			
			return ansIdx;
			
		}
//		
		public void remove(S s) { // 10^5번 호출이어서 ,
			S pre = s.prev ;
			S next = s.next;
			if(next == null) { // 맨 마지막 삭제하는 경우
				pre.next = next;
			}else {
				pre.next = next;
				next.prev = pre;
			}
			size--;
		}
	}

	public void init() {
		members = new S[100001];
		Smanager = new LList[6][6]; // 소속팀 , 평판점수
		for(int i = 1 ; i < 6; i++) {
			for(int j = 1 ; j<6 ; j++) {
				Smanager[i][j] = new LList();
			}
		}

	}

	public void hire(int mID, int mTeam, int mScore) {
		S newS = new S(mID, mTeam,mScore);
		members[mID] = newS;
		Smanager[mTeam][mScore].add_tail(newS);

	}

	public void fire(int mID) {
		S s = members[mID];
		Smanager[s.mTeam][s.mScore].remove(s);

	}

	public void updateSoldier(int mID, int mScore) {
		S s = members[mID]; 
		Smanager[s.mTeam][s.mScore].remove(s);
		Smanager[s.mTeam][mScore].add_tail(s);
		
	}

	public void updateTeam(int mTeam, int mChangeScore) {
		LList[] curTeam = Smanager[mTeam];
		
		for(int score = 1 ; score < 6 ; score++) {
			LList l = curTeam[score];
			if(l.size ==0) continue;
			
			int changedScore =score + mChangeScore; 
			if(changedScore>5) {
				curTeam[5].add_LList(l);
			}else if(changedScore < 1) {
				curTeam[1].add_LList(l);
				
			}else {
				curTeam[changedScore].add_LList(l);
			}
		}

	}

	public int bestSoldier(int mTeam) {
		for(int i = 5 ; i>=1 ; i--) {
			if(Smanager[mTeam][i].size <= 0) continue;
			
			return Smanager[mTeam][i].search();
		}
		return 0;
	}
}