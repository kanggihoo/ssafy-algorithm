package Day0206;
//신메뉴 개발 
//1~N번의 재료 , 가능한대로 많은 종류의 버거를 만들고 싶지만 서로 어울리지 않는 재료들이 있기 때문에 이를 고려
//i와 j가 궁합이 안맞으면 버거 만들 수 없음
//궁합이 맞지 않는 재료의 M개 정보 주어질때 만들 수 있는 버거 종류 구하라
//순서 변경되어도 같은 버거로 봄

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW3421_BurgerMaster_강기호 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		int[][] Info = new int[400][2];
		
		for(int t = 1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			// 어울리지 않는 조합
			for(int i = 0 ; i < M ; i++) {
				st = new StringTokenizer(br.readLine());
				Info[i][0] = Integer.parseInt(st.nextToken())-1;
				Info[i][1] = Integer.parseInt(st.nextToken())-1;
			}
			
			// 
			int ans = 0;
			for(int i = 0 ; i < (1<<N) ; i++) {
				
				// i 조합에 대해 어울리지 않는게 있는지 확인
				boolean isValid=true;
				for(int j=0; j<M ; j++) {
					int n1 = Info[j][0];
					int n2 = Info[j][1];
					
					if((i&(1<<n1))!=0 && (i&(1<<n2))!=0) {
						isValid = false;
						break; 
					}
				}
				if(isValid) ans++;
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.print(sb);

	}

}
