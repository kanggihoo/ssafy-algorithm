package Day0210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW2806_Nqueen_강기호 {
	static int ans;
	static int N;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1 ; t<=T ; t++) {
			N = Integer.parseInt(br.readLine());
			
			
			ans = 0;
			dfs(0,0,0,0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.print(sb);

	}
	
	public static void dfs(int r,  int c , int d1 , int d2) {
		// d1 : ����� �밢��
		// d2 : ���ϴ� �밢��
		if(r == N) {
			ans++;
			return;
		}
		// �� ����  
		for(int i = 0 ; i < N ; i++) {
			// ���˻� , ����� �밢�� , ���ϴ� �밢�� �˻�
			int shift = N-1 + r-i;
			if((c&(1<<i)) ==0 && (d1&(1<<r+i)) ==0 && (d2&(1<<shift)) ==0) {
				// r,i ���� �� ���	
				dfs(r+1 , c|(1<<i) , d1|(1<<r+i) , d2|(1<<shift));
				
			}
		
		}
	}

}
