package intro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;



public class StringReorder {
	static String input;
	static int[] counter;
	static char[] hist;
	static boolean flag;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        counter = new int[26];
        hist = new char[input.length()];
        for(char c : input.toCharArray()) {
        	counter[c-'A']++;
        }
        
        int pre = -1;
        for(int i = 0 ; i < input.length() ; i++) {
        	
        	int orderIdx =-1;
    		int freIdx =-1;
    		int freV =0;
    		for(int j = 0 ; j < 26 ; j++) {
    			if(counter[j]==0 || j==pre)continue; // 인접하지 않고 개수0이면 pass 
    			if(orderIdx == -1) orderIdx = j;
    			if(freV < counter[j]) {
    				freIdx = j;
    				freV = counter[j];
    			}
    		}
    		
    		// 가장 빈도수 높은거 확인해서 놓을 수 없는지 확인
    		int remain= input.length()-i;
    		if(orderIdx != -1 && ((remain+1) /2 + (remain+1) %2) >= freV) {
    			if(((remain+1) /2 + (remain+1) %2) == freV) {
    				hist[i] = (char)(freIdx+'A');
    				counter[freIdx]--;
    				pre = freIdx;
    			}else {
    				hist[i] = (char)(orderIdx+'A');
    				counter[orderIdx]--;
    				pre = orderIdx;    					
    			}
    		}else {
    			flag = true;
    			break;
    		}
        }
        
        
        if(!flag) {
        	StringBuilder sb = new StringBuilder();
        	for(int i = 0 ; i < input.length() ; i++) {
        		sb.append(hist[i]);
        	}
        	System.out.print(sb);
        }else {
        	System.out.print(-1);
        }
    }
	
	

}
