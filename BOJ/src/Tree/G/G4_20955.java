package Tree.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


// 모든 노드를 하나의 트리 형태로 연결하기 이때 두 노드 연결, 혹은 끊는 연산 가능 
// 모든 뉴런을 하나의 트리 형태로 연결하기 위해 필요한 최소 연산 횟수 구하라. 

public class G4_20955 {
	
	static boolean[] V;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] G = new ArrayList[N+1];
		for(int i = 1 ; i<=N ; i++) G[i] = new ArrayList<>();
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			G[n1].add(n2);
			G[n2].add(n1);
		}
		
		// 그룹화를 하고, 해당 그룹의 노드, 엣지 개수가 몇개인지 알면됨.
		int groupCnt = 0;
		int ans=0;
		V = new boolean[N+1];
		for(int i = 1 ; i<=N ; i++) {
			if(V[i]) continue;
			int[] info = dfs(i,G);
			int eCnt = info[1] /2;
			int nCnt = info[0];
			groupCnt++;
			ans += (eCnt-(nCnt-1));
		}
		ans += (groupCnt-1);
		System.out.print(ans);
	}
	
	public static int[] dfs(int i , List<Integer>[] G) {
		V[i] = true;
		int cnt = 0;
		int nodeCnt = 1;
		for(int adj : G[i]) {
			if(!V[adj]) {
				int[] tmp = dfs(adj , G);
				nodeCnt += tmp[0];
				cnt+=tmp[1];
			}
		}
		cnt += G[i].size();
		return new int[] {nodeCnt , cnt};
	}

}
