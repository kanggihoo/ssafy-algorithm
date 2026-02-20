package Day0213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//2이상 정수 N 
//N을  N+1로 변경가능
//sqrt N 이 정수이면 N을 루트 N으로 변경가능 
//N을 2로 만들기


public class SW6782_SquareRootGame_강기호 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for(int t=1 ; t<=T ; t++) {
			long N = Long.parseLong(br.readLine());
			int ans = 0;
			// N 보다 큰 루트했을때 정수가 되는 숫자 
			
			while(N!=2) {
				long sqrted =(long)Math.sqrt(N);
				if(N == sqrted*sqrted) {
					N = sqrted;
					ans +=1;
				}else {
					long next = (long)Math.pow(sqrted+1, 2);
					ans += next - N;
					N = next;
				}
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}

}
