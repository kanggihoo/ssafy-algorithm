package Day0203;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


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
		
		
		// ���� ��� 
		for(int i = 0 ; i < 4; i ++) C[i]= Integer.parseInt(st.nextToken());
		
		// ���� ��� �迭
		int[] curCnt = new int[4];
		
		//�ʱⰪ ���
		for(int i = 0 ; i < P ; i++) {
			curCnt[table.get(str.charAt(i))] +=1;
		}
		int ans = check(curCnt , C) ? 1: 0;
		
		
		// �����̵� ��� 
		for(int e = P ; e<S ; e++) {
			char added = str.charAt(e);
			char removed = str.charAt(e-P);
			
			// ������Ʈ
			curCnt[table.get(added)] +=1;
			curCnt[table.get(removed)] -=1;
			
			// ���� ����Ȯ��
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
