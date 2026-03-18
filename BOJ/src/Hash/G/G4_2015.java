package Hash.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class G4_2015 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		
		Map<Long, Long> hashmap = new HashMap<Long, Long>();
		hashmap.put(0L, 1L);
		st = new StringTokenizer(br.readLine());
		long accSum = 0;
		long ans = 0;
		// 누적 합 공식 [i,j]  
		// S[j] - S[i-1] = K 이므로 S[j]-K의 가능한 모든 경우의 수를 미리 저장
		for(int i = 1 ; i <=N ; i++) {
			int cur = Integer.parseInt(st.nextToken());
			accSum += cur; // 누적합
			if(hashmap.containsKey(accSum-K)) {
				ans += hashmap.get(accSum-K);
			}
			
			hashmap.merge(accSum , 1L , (o,n)->o+n);

		}
		
		
		System.out.print(ans);
		
	}

}
