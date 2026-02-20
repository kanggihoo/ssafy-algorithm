package P19457_신소재케이블2;

import java.util.*;
class UserSolution
{
	public Map<Integer, ArrayList<int[]>> G; 
	public Map<Integer , Integer> V;
	public int vCnt;
	public int maxDistNodeIdx;
	
	// 이거 
	
	public void init(int mDevice)
	{
		G = new HashMap<>();
		G.put(mDevice, new ArrayList<>());
		V = new HashMap<>();
		vCnt = 0;
		
	}
	
	
	public void connect(int mOldDevice, int mNewDevice, int mLatency)
	{
		G.get(mOldDevice).add(new int[] {mNewDevice , mLatency});
		G.computeIfAbsent(mNewDevice, k->new ArrayList<>()).add(new int[] {mOldDevice , mLatency});
		
		V.put(mOldDevice , -1);
		V.put(mNewDevice , -1);
		
	}
	// mDevice1 -> mDevice2  까지의 거리 계산
	public int measure(int mDevice1, int mDevice2)
	{
		
		vCnt++;
		return getD(mDevice1 , mDevice2 , 0);
	}
	
	public int test(int mDevice)
	{
		vCnt++;
		// mDeivce로 부터 가장 멀리 떨어진 노드 idx 찾기
		int[] res = dfs(mDevice , 0);
		vCnt++;
		// 찾은 노드로 부터 가장 멀리 떨어진 거리 찾기. => 해당 경로에서 mDevice를 무조건 포함해야되 
		res = dfs2(res[0] , mDevice  , 0 , 0);
		
		return res[1];
	}
	
	public int getD(int cur , int target , int d) {
		if(cur == target) {
			return d;
		}
		
		// 방문처리
		V.put(cur, vCnt);
		for(int[] adj : G.get(cur)) {
			if(V.get(adj[0]) != vCnt) {
				int tmp = getD(adj[0], target, d+adj[1]);
				if(tmp != -1) return tmp;
			}
		}
		return -1;
	}
	
	public int[] dfs(int cur , int d) {
		
		// 방문처리
		V.put(cur, vCnt);
		int[] maxInfo = {cur , d};
		for(int[] adj : G.get(cur)) {
			if(V.get(adj[0]) != vCnt) {
				int[] tmp = dfs(adj[0], d+ adj[1]);
				if(tmp[1] > maxInfo[1]) {
					maxInfo[0] = tmp[0];
					maxInfo[1] = tmp[1];
				}
			}
		}
		return maxInfo;
	}
	
	public int[] dfs2(int cur , int target , int d , int flag) {
		// 방문처리
		
		V.put(cur, vCnt);
		int[] maxInfo = {cur , d , flag};
		for(int[] adj : G.get(cur)) {
			if(V.get(adj[0]) != vCnt) {
				int[] tmp = dfs2(adj[0],target, d+ adj[1] , target==adj[0] ? flag+1:flag);
				if(tmp[2]==1 && tmp[1] > maxInfo[1]) {
					maxInfo[0] = tmp[0];
					maxInfo[1] = tmp[1];
					maxInfo[2] = tmp[2];
				}
			}
		}
		return maxInfo;
	}
}