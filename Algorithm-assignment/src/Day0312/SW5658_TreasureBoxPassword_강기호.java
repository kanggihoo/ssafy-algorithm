package Day0312;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



/*
 * 16진수 숫자 0~A,B,C,D,E,F
 * 뚜껑 시계방향 회전 시 숫자도 시계방향으로 1칸 회전  
 * 만들 수 있는 숫자중 중복제거해서 내림차순 정렬한 후 K번째 큰 10진수가 정답 
 * N은 4의 배수 (8 ≤ N ≤ 28) 
 * 
 * 
 */
public class SW5658_TreasureBoxPassword_강기호 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T =Integer.parseInt(br.readLine());
		
		for(int t=1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			String s = br.readLine();
			Set<String> set = new HashSet<>();
			// 반복횟수 
			int times = N/4;
			String comp =s+s;
			for(int i = 0 ; i < times ; i++) {
				
				for(int j = 0 ; j < 4 ; j++) {
					set.add(comp.substring(i+times*j, i+times*(j+1)));
				}
			}
			List<String> list = new ArrayList<String>(set);
			list.sort(Comparator.reverseOrder());
			String k = list.get(K-1);
			int ans = k.charAt(0);
			if(ans >'9') {
				ans -= 55;
			}else {
				ans -= '0';
			}
			
			for(int i = 1 ; i<k.length() ; i++) {
				int c = k.charAt(i);
				if(c > '9') {
					c -= 55;
				}else {
					c -='0';
				}
				ans = ans*16+c;
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);

	}

}
