package DP.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Score{
	String s;
	int score;
	
	Score(String s , int score){
		this.s = s;
		this.score = score;
	}
	
}

public class G4_21941 {
	
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int M = Integer.parseInt(br.readLine());
        Score[] scores = new Score[M];
        StringTokenizer st;
        for(int i = 0 ; i < M ; i++) {
        	st = new StringTokenizer(br.readLine());
        	String s = st.nextToken();
        	int score = Integer.parseInt(st.nextToken());
        	scores[i] = new Score(s , score);
        }
        int[] dp = new int[input.length()+1];
        
        for(int i =1 ; i<=input.length() ; i++) {
        	
        	for(int j = 0 ; j < M ; j++) {
        		String s = scores[j].s;
        		int score = scores[j].score;
        		if(s.length() <= i) {
        			if(check(input , s , i)) {
            			dp[i] = Math.max(Math.max(dp[i-s.length()]+score, dp[i-1]+1),dp[i]);
        			}else {
        				dp[i] = Math.max(dp[i], dp[i-1]+1);
        			}
        		
        		}else {
        			dp[i] = Math.max(dp[i-1]+1, dp[i]);
        		}
        	}
        }
        
        System.out.print(dp[input.length()]);

    }
	
	public static boolean check(String origin , String s , int i) {
		if(s.length()> i)return false;
		for(int j = 0 ; j<s.length() ; j++) {
			if(origin.charAt(i-s.length()+j) != s.charAt(j)) return false;
		}
		return true;
	}

}
