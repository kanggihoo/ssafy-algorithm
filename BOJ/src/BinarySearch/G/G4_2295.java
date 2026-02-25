package BinarySearch.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

// 세 수를 적당히 골랐을때, a,b,c의 합이 집합 U에 존재하는 d가 있도록 하는 경우의
// 수들 중에서 가장 큰 d를 찾아라. 
public class G4_2295 {
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] A = new int[N];
		for(int i = 0 ; i < N ; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(A);
		
		// a+b 의 모든 경우의 수 구하기 
		Set<Integer> sumSet = new HashSet<>();
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j<N ; j++) {
				sumSet.add(A[i]+A[j]);
			}
		}
		List<Integer> sumList = new ArrayList<>(sumSet);
		sumList.sort(Integer::compare);
		
		// 맨 뒤에서 부터 d 선택
		boolean flag=false;
		int ans = 0;
		for(int i=N-1 ; i>=0 ; i--) {
			int d = A[i];
			if(flag) break;
			// a+b 선택
			for(int sum : sumList) {
				int c = d - sum;
				if(c <= 0) continue;
				if(c==d || binarySearch(A,c,i)) {
					flag=true;
					ans=d; 
					break;
				}
			}
		}
		System.out.print(ans);
	}
	
	public static boolean binarySearch(int[] A , int t , int endIdx) {
		int e = endIdx;
		int s = 0;
		
		while(s<=e) {
			int mid = s+(e-s)/2;
			
			if(A[mid] == t) return true;
			else if(A[mid] > t) e = mid-1;
			else if(A[mid] < t) s = mid+1;
		}
		
		return false;
		
	}
	
}
