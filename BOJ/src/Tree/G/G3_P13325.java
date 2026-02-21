package Tree.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 양수 가중치 부여된 높이 K포화이진트리 
public class G3_P13325 {
	static int N;
	static int[] T;
	static int add = 0;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int K = Integer.parseInt(br.readLine());
		N = (int)Math.pow(2, K+1)-1;
		T = new int[N+1];
		int ans = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 2 ; i<=N ; i++) {
			T[i] = Integer.parseInt(st.nextToken());
			ans += T[i];
		}
//		System.out.print(ans);
		dfs(1);
		System.out.print(ans+add);
		
	}
	
	public static int dfs(int idx) {
		if(idx*2 >= N) return 0;
		
		// 현재 노드까지 좌측 , 우측 가중치 합 
		int w1 = dfs(idx*2);
		int w2 = dfs(idx*2+1); 
		
		
		// 현재 노드 기준 자식, 부모 노드 값 비교
		int c1= T[idx*2];
		int c2 = T[idx*2+1];
		add += Math.abs(w1+c1 - (w2+c2));
		return Math.max(w1+c1, w2+c2);
		
		
		
		
	}

}
