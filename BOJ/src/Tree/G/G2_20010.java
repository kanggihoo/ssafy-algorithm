package Tree.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class G2_20010 {
	static int treeDepth;
	static List<Node>[]G2;
	
	static class Node implements Comparable<Node>{
		int idx;
		int c;
		
		Node(int idx , int c){
			this.idx = idx ;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.c, o.c);
		}
		
		
	}

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<Node>[] G = new ArrayList[N];
		for(int i = 0 ; i < N ; i++) G[i] = new ArrayList<>();
		
		for(int i = 0 ; i < K ; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			G[n1].add(new Node(n2,c));
			G[n2].add(new Node(n1,c));
		}
		
		int[] D = new int[N];
		Arrays.fill(D, Integer.MAX_VALUE);
		boolean[] V = new boolean[N];
		Node[] parents = new Node[N];
		parents[0] = new Node(0,0);
		Queue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0,0));
		
		int ans =0;
		int count = 0;

		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(V[cur.idx]) continue;
			V[cur.idx] = true;
			ans += cur.c;
			count ++;
			if(count == N)break;
			
			for(Node node : G[cur.idx]) {
				if(!V[node.idx] && D[node.idx] > node.c) {
					parents[node.idx] = new Node(cur.idx , node.c);
					D[node.idx] = node.c;
					pq.offer(node);
				}
			}
		}
		
		System.out.println(ans);
		
		G2 = new ArrayList[N];
		for(int i =0 ; i < N ; i++) G2[i] = new ArrayList<>();
		
		
		for(int i = 1 ; i < N ; i++) G2[parents[i].idx].add(new Node(i,parents[i].c));
		treeDepth = 0;
		dfs(0);
		System.out.print(treeDepth);
	}
	
	static int dfs(int cur) {
		
		int first = 0;
		int second = 0;
		for(Node node : G2[cur]) {
			int res = dfs(node.idx)+node.c;
			
			if(res >= first) {
				second = first;
				first = res;
			}else if(res > second) {
				second = res;
			}
		}
		treeDepth = Math.max(treeDepth, first+second);
		
		return first;
		
	}

}
