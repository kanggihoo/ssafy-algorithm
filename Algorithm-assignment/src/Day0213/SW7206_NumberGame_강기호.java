package Day0213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//임의의 수 하나로 시작
//1. 시작 수는 자연수 1이상 99999이하의 수이다.                                                        
//2. 시작 수의 사이를 터치하면 수는 두개 또는 그 이상으로 쪼개진다.                                               
//3. 쪼개진 수를 모두 곱한다.                                                                     
//4. 곱한 수가 10이상이면  더 이상 쪼개수 없을 때까지   규칙 2~3번을 반복하여 수행한다.
//5. 한번 쪼개질 때마다 turn 수가 증가 한다.                                                             
//6. 최대 턴수가 나오도록 한다.  

public class SW7206_NumberGame_강기호 {
	
	static int[] D = new int[100000];

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		

		int T = Integer.parseInt(br.readLine());
		for(int t=1 ; t<=T ; t++) {
			int N = Integer.parseInt(br.readLine());
			
			int ans = dfs(N);
			
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
//		System.out.print(split(1 , 2 ,"79"));
	}
	
	
	public static int dfs(int number) {
		if(D[number]!=0) return D[number];

		
		String str = String.valueOf(number);
		for(int i = 1 ; i<(1<<str.length()-1) ; i++) {
			int sum = split(i,str.length()-1 , str);
			if(sum >= 10) {
				D[number] = Math.max(D[number], dfs(sum)+1);
			}
		}
		
		return D[number];
		
	}
	
	public static int split(int state , int N , String s) {
		List<Integer> tmp = new ArrayList<>();
		int idx = 0;
		for(int i = 0 ; i<1<<N ; i++) {
			if((state & 1<<i) !=0){
				int num  = Integer.parseInt(s.substring(idx , i+1));
				tmp.add(num);
				idx = i+1;
			}
		}
		tmp.add(Integer.parseInt(s.substring(idx, s.length())));
		
		int res = 1;
		for(int i : tmp) res *=i;
		return res;
		
			
	}

}
