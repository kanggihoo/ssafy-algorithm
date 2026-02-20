package Day0213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//2�̻� ���� N 
//N��  N+1�� ���氡��
//sqrt N �� �����̸� N�� ��Ʈ N���� ���氡�� 
//N�� 2�� �����


public class SW6782_SquareRootGame_강기호{

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for(int t=1 ; t<=T ; t++) {
			long N = Long.parseLong(br.readLine());
			int ans = 0;
			// N ���� ū ��Ʈ������ ������ �Ǵ� ���� 
			
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
