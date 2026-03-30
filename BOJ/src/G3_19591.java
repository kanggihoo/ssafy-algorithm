import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




/* 0. 숫자 , 연산자 쪼개기 (연산자는 4개) , 음수인경우 어떻게 쪼갤건지 음수인지 - 연산자인지 
 * 		수식은 반드시 수와 연산자가 번갈아 가면서 나온다. 마지막에 연산자가 있는 경우는 존재하지 않으며, 
 * 		맨 앞을 제외하고 음수가 들어오는 경우도 존재하지 않는다.
 * 	
 * 1. 수식에서 앞, 뒤 
 * 
 */
public class G3_19591 {
	static Map<Character , Integer> pTable = new HashMap<>();
	

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Deque<Character> operators = new ArrayDeque<>();
		Deque<Long> numbers = new ArrayDeque<>();
		pTable.put('*', 1);
		pTable.put('/', 1);
		pTable.put('-', 0);
		pTable.put('+', 0);
		
		
		
		// 맨 앞이 음수인 경우 고려 
		String line = br.readLine();
		if(line.charAt(0) =='-') {
			int idx = 1;
			while(idx < line.length()) {
				if(line.charAt(idx) == '/' || line.charAt(idx) == '*' || line.charAt(idx) == '+' || line.charAt(idx) == '-') break;
				idx++;
			}
			long mNum = Long.parseLong(line.substring(0,idx));
			line = line.substring(idx , line.length());
			numbers.offer(mNum);
		}
		if(line.length()>0) {
			int idx = 0;
			for(int i = 0 ; i < line.length() ; i++) {
				char cur =line.charAt(i); 
				if(cur == '/' || cur == '*' || cur == '+' || cur == '-') {
					operators.offer(cur);
					String strNum = line.substring(idx,i);
					if(strNum.length() > 0) {
						numbers.offer(Long.parseLong(strNum));
					}
					idx = i+1;
				}
			}
			// 맨 마지막 추가
			numbers.offer(Long.parseLong(line.substring(idx,line.length())));
		}
		
//		System.out.println(operators.toString());
//		System.out.println(numbers.toString());
		
		// 계산 진행
		while(operators.size() >= 2) {
			char lO = operators.pollFirst();
			char rO = operators.pollLast();
			
			// 우선순위 확인
			int p =priority(lO, rO);
			if(p == 0) {
				long lN = numbers.pollFirst();
				long rN = numbers.pollLast();
				
				long lRes = calculate(lN, lO,numbers.peekFirst());
				long rRes = calculate(numbers.peekLast(), rO,rN);
				if(lRes >= rRes) {
					numbers.pollFirst();
					numbers.offerFirst(lRes);
					numbers.offer(rN);
					operators.offer(rO);
					
				}else {
					numbers.pollLast();
					numbers.offer(rRes);
					numbers.offerFirst(lN);
					operators.offerFirst(lO);
				}
			}else {
				if(p==1) { // 왼쪽 우선순위 더큼  
					long n1 = numbers.poll();
					long n2 = numbers.poll();
					long lRes = calculate(n1, lO, n2);
					numbers.offerFirst(lRes);
					operators.offer(rO);
				}else { // 오른쪽 우선순위 더큼 
					long n1 = numbers.pollLast();
					long n2 = numbers.pollLast();
					long rRes = calculate(n2, rO,n1);
					numbers.offer(rRes);
					operators.offerFirst(lO);
				}
				
			}			
		}
		// 연산자 1개
		long ans = 0;
		if(operators.size()==1) {
			long n1 = numbers.poll();
			long n2 = numbers.poll();
			numbers.offer(calculate(n1, operators.poll(), n2));
		}
		ans = numbers.poll();
		System.out.print(ans);
	}
	
	public static  int priority(char l , char r) {
		return Integer.compare(pTable.get(l), pTable.get(r));
	}
	
	public static long calculate(long a, char c,long b ) {
		if(c == '+') return a+b;
		else if(c =='-') return a-b;
		else if(c=='*') return a*b;
		else return a/b;	
	}
	

}
