package Day0211;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN2839_SugarDelivery_강기호{
	
	static int r;
	static int c;
	static int N;
	static int cnt;
	static int ans;
	
	static int[] dy= {0,0,1,1};
	static int[] dx = {0,1,0,1};
	static boolean flag;
	

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		cnt = 0;
		dfs(0,0,(int)Math.pow(2, N));
		
		System.out.print(ans);
		
	}
	
	public static void dfs(int y , int x , int size) {
		if(flag) return;
		if(!( y<=r && r<y+size && x<=c && c<x+size)) {
			cnt += size*size;
			return;
		}
		if(size == 1){
			if(y==r && x ==c) {
				ans = cnt;
				flag= true;
			}else cnt++;
			return;
		}
		
		dfs(y ,x,size/2 );
		dfs(y,x+size/2 , size/2);
		dfs(y+size/2 , x , size/2);
		dfs(y+size/2 , x+size/2 , size/2);
	}
	
	
}
