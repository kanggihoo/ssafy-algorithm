package Day0211;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//���� B ���� 
//�� ���� Ű�� Hi , ������ ž�� �׾Ƽ� ���� �� ���� ��� 
//ž�� ���̰� B �̻��� ��� ���� ���̰� ���� ���� ž�� ���ϱ� 


public class SW1486_TopShelf_강기호{
	static int ans;
	static int N;
	static int B;
	static int[] H;
	

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		H = new int[21];
		for(int t=1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				H[i] = Integer.parseInt(st.nextToken());
			}
			ans = 10000*N;
			dfs(0,0);
			
			sb.append("#").append(t).append(" ").append(ans-B).append("\n");
		}
		System.out.print(sb);

	}
	
	public static void dfs(int idx , int sum) {
		if(idx == N) {
			if(sum < B) return;
			
			ans = Math.min(ans , sum);
			return;
		}
		// ���� ���� �� ���ص� sum ���� ������ return
		int tmp = sum;
		for(int i = idx ; i<N;i++) tmp+=H[i];
		if(tmp < B) return;
		
		// �̹� ans ���� ũ�� return
		if(sum >= ans) return;
		
		dfs(idx+1 , sum+H[idx]); // ����
		dfs(idx+1 , sum); // ���� X
		
	}

}
