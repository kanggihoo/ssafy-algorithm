package BinarySearch.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 현재 고속도로에 N개 휴개소 존재 => M개 더세우기 
 * 이미 지어진 곳에는 X , 고속도로 끝에도 X
 * 휴개소 M개 더 지어서 휴개소가 없는 구간의 길이를 최소로(반드시 M개 지어야함.)
 *   
 * 
 * 
 */
public class G4_1477 {
	static int[] P; 

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
				
		// 최솟값을 정하고, 그 값을 기준으로 구간이 M개 생기는지 확인 => 이 값을 최소로 만들어야함.
		P = new int[N+2];
		st = new StringTokenizer(br.readLine());
		for(int i =1 ; i <= N ; i++) {
			P[i] = Integer.parseInt(st.nextToken());
		}
		P[N+1] = L;
		Arrays.sort(P);
		
		int maxLange = 0;
		for(int i = 0 ; i <= N ; i++) {
			maxLange = Math.max(maxLange, P[i+1] - P[i]);
		}
		
		int l=  1;
		int r = maxLange;
		
		while(l<=r) {
			int mid = l+(r-l)/2;
			int res = check(mid);
			if(res <= M) { // M보다 작으면 더 늘려봐서 테스트 
				r = mid -1;
				
			}else { // M보다 많으면 범위를 늘려주어야함. 
				l = mid+1;
			}
		}
		
		System.out.print(l);
	}
	
	public static int check(int target) {
		int ans = 0 ;
		for(int i = 0 ; i < P.length-1 ; i++) {
			int dist = P[i+1] - P[i];
			if(dist <= target) continue;
			ans += (dist-1)/target;
		}
		return ans;
	}

}
