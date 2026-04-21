package intro;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class TwoSets {

	 public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        int N = Integer.parseInt(br.readLine());
	        StringBuilder sb = new StringBuilder();
	        long sum = (long)N*(N+1)/2;
	        if(sum % 2 ==0) {
	        	sb.append("YES\n");
	        	long target = sum /2;
	        	List<Integer> s1 = new ArrayList<>();
	        	List<Integer> s2 = new ArrayList<>();
	        	int sum1 = 0;
	        	for(int i = N-1 ; i>=1 ; i--) {
	        		if(sum1+i <= target) {
	        			s1.add(i);
	        			sum1+=i;
	        		}else {
	        			s2.add(i);
	        		}
	        	}

	        	// 결과 출력
	        	sb.append(s1.size()).append("\n");
	        	for(int i : s1) {
	        		sb.append(i).append(" ");
	        	}
	        	sb.append(s2.size()).append("\n");
	        	for(int i : s2) {
	        		sb.append(i).append(" ");
	        	}

	        }else {
	        	sb.append("NO");
	        }
	        System.out.print(sb);
	    }
}
