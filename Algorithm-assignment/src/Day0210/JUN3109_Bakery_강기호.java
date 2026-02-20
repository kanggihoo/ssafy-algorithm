package Day0210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//R*C 
//ù��° ���� ��ó ���� ������ ���������� ���� 
//�������� ������ �����ϴ� �������� ��ġ.
//������ ������ ���̿� �ǹ��� ���� �� �ְ�, �ǹ��� ������ ������ ������.
//��� �������� ù° ������ ���� �ϰ� ������ ���� ����. 
//�������� ��ĥ���� ���� ���� ����. �� ĭ�� ������ �������� �ϳ� 
//ù���� ������ ���� �׻� �������.


public class JUN3109_Bakery_강기호{
	static int R;
	static int C;
	static char[][] A;
	static int[] dy = {-1,0,1};
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		A = new char[R][];
		for(int i = 0 ; i<R ; i++) {
			A[i] = br.readLine().toCharArray();
		}
		int ans=0;
		for(int i = 0 ; i<R ; i++) {
			if(dfs(i,0)) ans++;
		}
		System.out.print(ans);
		
		
	}
	public static boolean dfs(int r,int c) {
		if(c == C-1) {
			return true;
		}
		boolean res = false;
		for(int i = 0 ; i < 3 ; i++) {
			int nr = r+dy[i];
			int nc = c+1;
			if(nr>=0 && nr<R && A[nr][nc] =='.') {
				A[nr][nc] ='P';
				res = dfs(nr,nc);
				if(res) return res;
			}
		}
		return false;
	}

}
