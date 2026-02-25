package Tree.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class G3_16437 {
	
	static List<Integer>[] G;
	static char[] animals;
	static int[] nums;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); // 섬의 개수
		G = new ArrayList[N+1];
		animals = new char[N+1];
		nums =new int [N+1];
		for(int i = 1 ; i<=N ; i++) {
			G[i] = new ArrayList<>();
		}
		
		
		for(int i = 2 ; i <= N ; i++) {
			st = new StringTokenizer(br.readLine());
			animals[i] = st.nextToken().charAt(0);
			nums[i] = Integer.parseInt(st.nextToken());
			int adj = Integer.parseInt(st.nextToken());
			G[adj].add(i);
		}
		
		long ans = dfs(1,0);
		System.out.print(ans);
	}
	
	public static long dfs(int c , int p){
		
		char a = animals[c];
		long res = 0;
		for(int adj : G[c]) {
			if(adj != p) {
				res += dfs(adj , c);
			}
		}
		if(c == 1) return res;
		if(a=='S') return res+nums[c];
		if(a=='W') {
			res -= nums[c];
			return res<=0 ? 0 : res;
		}
		
		return 0;
	}


}
