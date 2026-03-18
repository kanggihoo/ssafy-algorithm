package P14614_긴사다리게임;

import java.util.*;

class Node implements Comparable<Node> {
	int x;
	int y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.y,	o.y);
	}
	
}

class UserSolution {
	TreeSet<Node>[] connInfo;
	int MAX_VALUE = 1000000000;

	public void init() {
		connInfo = new TreeSet[102];
		for (int i = 0; i < 102; i++) {
			connInfo[i] = new TreeSet<>();
			connInfo[i].add(new Node(i, 0));
			connInfo[i].add(new Node(i, MAX_VALUE));
		}
	}

	// (mX, mY)와 (mX+1, mY)를 잇는 가로줄을 추가
	// mX : X 좌표 (1 ≤ mX ≤ 99)
	// mY : Y 좌표 (1 ≤ mY ≤ 999,999,999)
	public void add(int mX, int mY) {
		connInfo[mX].add(new Node(mX + 1, mY));
		connInfo[mX + 1].add(new Node(mX, mY));
	}

	public void remove(int mX, int mY) {
		connInfo[mX].remove(new Node(mX + 1, mY));
		connInfo[mX + 1].remove(new Node(mX, mY));
	}

	public int numberOfCross(int mID)
	{
		int xIdx = mID;
		int ans = 0;
		Node cur = new Node(-1 , 1);
		while(cur.y < MAX_VALUE) {
			Node res = connInfo[xIdx].ceiling(cur);
			xIdx = res.x;
			if(res.y == MAX_VALUE) break;
			cur.y = res.y+1;
			ans ++;
		}
		return ans;
	}
	
	// 현재 맵에서 사다리 게임을 진행했을 때, (mX, mY)를 지나게 되는 참가자의 번호를 반환
	public int participant(int mX, int mY) {
		int xIdx = mX;
		Node cur = new Node(-1 , mY);
		while(cur.y > 0) {
			Node res = connInfo[xIdx].floor(cur);
			cur.y = res.y -1;
			xIdx = res.x;
		}
		
		return xIdx;
	}
}