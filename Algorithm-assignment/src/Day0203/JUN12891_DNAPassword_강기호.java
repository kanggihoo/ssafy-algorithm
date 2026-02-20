package Day0203;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
DAN문자열은 모든 문자가 "A, C, G , T인 문자열 
임의의 DNA 문자열을 만들고 만들어진 DNA 문자열의 부분문자열을 비밀번호로 
=> 등장하는 문자의 개수가 특정 개수 이상이어야 사용가능.

임의의 DNA와 , 부분 문자열 길이, 각 단어가 몇번 이상 등장해야 하는지가 주어질때
만들 수 있는 비밀번호 종류 구하라 
부분문자열이 등장하는 위치 다른 다른 문자열
 * 
 */

public class JUN12891_DNAPassword_강기호 {


	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		String str = br.readLine();
		int[] C = new int[4];
		
		char[] cOrder = {'A' , 'C' , 'G' , 'T'};
		Map<Character, Integer> table = new HashMap<>();
		
		for(int i = 0 ; i < 4 ; i++) table.put(cOrder[i], i);
		
		
		st = new StringTokenizer(br.readLine());
		
		
		// 조건 계산 
		for(int i = 0 ; i < 4; i ++) C[i]= Integer.parseInt(st.nextToken());
		
		// 문자 계산 배열
		int[] curCnt = new int[4];
		
		//초기값 계산
		for(int i = 0 ; i < P ; i++) {
			curCnt[table.get(str.charAt(i))] +=1;
		}
		int ans = check(curCnt , C) ? 1: 0;
		
		
		// 슬라이딩 계산 
		for(int e = P ; e<S ; e++) {
			char added = str.charAt(e);
			char removed = str.charAt(e-P);
			
			// 업데이트
			curCnt[table.get(added)] +=1;
			curCnt[table.get(removed)] -=1;
			
			// 조건 만족확인
			if(check(curCnt ,C)) ans ++;
		}
		
		System.out.print(ans);

	}
	
	public static boolean check(int[] cur , int[] rule) {
		for(int i = 0 ; i < 4 ; i++) {
			if(cur[i] < rule[i]) return false;
		}
		return true;
	}

}
