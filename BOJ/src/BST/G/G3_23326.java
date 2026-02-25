package BST.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

// 홍익 투어리스트 

/*

*/
public class G3_23326 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int[] A = new int[N];
		st = new StringTokenizer(br.readLine());
		
		// 1이면 관광명소 , 0이면 아님
		TreeSet<Integer> T = new TreeSet<>();
		
		for(int i = 0 ; i < N ; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			if(A[i] == 1) T.add(i);
		} 
		
		int cur = 0; // 초기 위치 
		// 쿼리 
		for(int i = 0 ; i < Q ; i++) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			if(q == 1) {
				int n = Integer.parseInt(st.nextToken())-1;			 
				A[n] ^= 1;
				if(A[n] ==1) T.add(n);
				else T.remove(n);
			}
			else if(q==2) {
				int n = Integer.parseInt(st.nextToken());		
				n %= N;
				cur = (cur+n) %N;
			}else if(q==3) { // 시계방향으로 최소 몇 칸 움직이는지 출력
				if(T.size() == 0) {
					sb.append(-1).append("\n");
					continue;
				}
				Integer case1 = T.ceiling(cur);
				if(case1 == null) {
					sb.append(T.first()+N-cur).append("\n");
				}else {
					sb.append(case1-cur).append("\n"); 
				}
			}
					
		}
		System.out.print(sb);
	}

}
