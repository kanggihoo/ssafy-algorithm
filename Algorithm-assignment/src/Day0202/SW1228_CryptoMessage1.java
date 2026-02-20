package Day0202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
public class SW1228_CryptoMessage1 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int t = 1 ; t<=10 ; t++ ) {
			int N = Integer.parseInt(br.readLine());
			// 원본 암호문
			List<String> origin = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for(int i =0 ; i< N ; i++ ) {
				origin.add(st.nextToken());
			}
			
			int T =Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < T ; i++) {
				// 연산자
				String c = st.nextToken();
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				for(int j = 0 ; j<y ; j++) {
					if(c.equals("I")) origin.add(x+j, st.nextToken());
					else origin.remove(x); 
					
					
				}
			}
			
			// 10개만 출력
			sb.append("#").append(t).append(" ");
			for(int i = 0 ; i < 10 ; i++) {
				sb.append(origin.get(i)).append(" ");
			}
			sb.append("\n");
		}
		
		
		System.out.print(sb);
	}

}
