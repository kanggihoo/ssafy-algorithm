package Day0406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW1952_SwimmingPool_강기호 {
	static int[] price;
	static int[] plan;
	static int[] D;
	static int MAX = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T =Integer.parseInt(br.readLine());
		price = new int[4];
		plan = new int[12];
		D = new int[12];
 		for(int t=1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < 4; i++) price[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < 12 ; i++) plan[i] =  Integer.parseInt(st.nextToken());
			
			for(int i = 0 ; i < 12 ; i++) D[i] = -1;
			int ans = dfs(0);
			ans =Math.min(ans , price[3]);
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);

	}
	
	public static int dfs(int idx) {
		if(idx >=12) return 0;
		if(D[idx] != -1) return D[idx];
		
		D[idx] = MAX;
		
		// 1일
		int res1 = dfs(idx+1)+price[0]*plan[idx];
		
		
		// 1달
		int res2 = dfs(idx+1) + price[1];
		
		// 3달 
		int res3 = dfs(idx+3) + price[2];
		
		
		return D[idx] =Math.min( Math.min(res1, res2), res3); 
		
	}

}
