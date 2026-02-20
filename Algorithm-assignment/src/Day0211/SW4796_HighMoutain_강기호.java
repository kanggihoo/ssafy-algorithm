package Day0211;


import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

//좌우로 있는 N개의 산의 높이 측정(Hi), 높이 모두 다름
//어떤 지점 [i,j] i<j 구간에 대해 높이의 형태가 다음과 같으면 우뚝 선산
//
//i<k<j 에서 아래를 만족하는 k가 있어야함. 
//[i,k) 의 구간에서는 증가수열
//[k,j) 구간에서는 감소 수열 
//구간의 개수 구하라. 

public class SW4796_HighMoutain_강기호 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);

		
		int T = sc.nextInt();
		int[] H = new int[50001];
		for(int t=1 ; t<=T ; t++) {
			
			int N = sc.nextInt();
			
			
			for(int i = 0 ; i < N ; i++) {
				H[i] = sc.nextInt();
			}
			
			int ans = 0;
			int i =0;
			while(i<N) {
				
				//증가 구간 계산
				int k = i;
				while(k+1<N && H[k+1]> H[k]) k++;
				

				if(k>i && k<N) {
					// 감소 구간계산
					int j = k;
					while(j+1<N && H[j+1]< H[j]) j++;
					if(j>k&&j<N) {
						ans+=(k-i)*(j-k);
						i =j;
					}else {
						i=j+1;
					}
				}else {
					i=k+1;
				}
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);

	}

}
