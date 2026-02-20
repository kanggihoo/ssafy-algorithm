package P13072_병사관리;

class UserSolution {
	S[] members;
	LList[][] Smanager;
	
	
	static class S {
		int mIdx;
		int mTeam;
		S next;
		S prev;

		S(int mIdx, int mTeam) {
			this.mIdx = mIdx;
			this.mTeam = mTeam;
		}
	}
	static class LList {
		S head; 
		S tail;
		
		public static void remove(S s) {
			s.next.prev = s.prev;
			s.prev.next = s.next;
		}

		public LList() {
			head = new S(-1,-1);
			tail = new S(-1,-1);
			head.next = tail;
			tail.prev = head;
		}

		public void add_tail(S newNode) {
			S prevNode = tail.prev;
			prevNode.next = newNode;
			newNode.prev = prevNode;

			newNode.next = tail;
			tail.prev = newNode;
		}
		
		public void add_LList(LList newLList) {
			S newhead = newLList.head;
			S newtail = newLList.tail;
			if(newhead.next == newtail) return;
			
			tail.prev.next = newhead.next;
			newhead.next.prev = tail.prev;
			
			
			tail.prev = newtail.prev;
			newtail.prev.next = tail;
			
			
			// 기존 노드 초기화 
			newhead.next = newtail;
			newtail.prev = newhead;
		}
		

	}

	public void init() {
		members = new S[100001];
		Smanager = new LList[6][6]; // �Ҽ��� , ��������
		for(int i = 1 ; i < 6; i++) {
			for(int j = 1 ; j<6 ; j++) {
				Smanager[i][j] = new LList();
			}
		}

	}

	public void hire(int mID, int mTeam, int mScore) {
		S newS = new S(mID, mTeam);
		members[mID] = newS;
		Smanager[mTeam][mScore].add_tail(newS);

	}

	public void fire(int mID) {
		S s = members[mID];
		LList.remove(s);

	}

	public void updateSoldier(int mID, int mScore) {
		S s = members[mID]; 
		LList.remove(s);
		Smanager[s.mTeam][mScore].add_tail(s);
		
	}

	public void updateTeam(int mTeam, int mChangeScore) {
		LList[] curTeam = Smanager[mTeam];
		if(mChangeScore == 0) return;
		// 양수 이면 맨 상위점수부터 
		if(mChangeScore > 0) {
			for(int score = 5 ; score >= 1 ; score--) {
				LList l = curTeam[score];
				int k = score+mChangeScore > 5 ? 5 : score+mChangeScore;
				if(l.head.next == l.tail ||score ==k) continue;
				curTeam[k].add_LList(curTeam[score]);
			}
		}else {
			for(int score = 1 ; score < 6 ; score++) {
				LList l = curTeam[score];
				int k = score+mChangeScore < 1 ? 1 : score+mChangeScore;
				if(l.head.next == l.tail || score ==k) continue;
				curTeam[k].add_LList(curTeam[score]);
			}
		}

	}

	public int bestSoldier(int mTeam) {
		int res=0;
		for(int i = 5 ; i>=1 ; i--) {
			LList cur = Smanager[mTeam][i];
			
			if(cur.head.next == cur.tail) continue;
			
			S curS = cur.head.next;
			S curTail = cur.tail;
			while(curS != curTail) {
				if(curS.mIdx > res) res = curS.mIdx;
				curS = curS.next;
			}
			if(res != 0) return res;
		}
		return 0;
	}
}


