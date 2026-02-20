package Day0204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//18Àå Ä«µå·Î °ÔÀÓ
//9Àå¾¿ ³ª´«µÚ 9¶ó¿îµå µ¿¾È °ÔÀÓ ÁøÇà
//°¢ ¶ó¿îµå¿¡ ÇÑ Àå¾¿ ³½ ´ÙÀ½ µÎ »ç¶÷ÀÌ ³½ ¯‹¿¡ ÀûÈù ¼ö ºñ±³ÇØ¼­ Á¡¼ö°è»ê
//³ôÀº ¼ö ³»¸é µÎ Ä«µåÀÇ ÇÕ¸¸Å­ Á¡¼ö
//³·Àº ¼ö ³½ »ç¶÷Àº Á¡¼ö X
//
//9¶ó¿îµå ÃÑÁ¡ÀÌ °¡Àå ³ôÀ¸¸é ¿ì½Â, °°À¸¸é ¹«½ÂºÎ
//
//ÀÌ¹ø °ÔÀÓ¿¡ ±Ô¿µÀÌ°¡ ¹ŞÀº 9Àå ¼ø¼­ °íÁ¤µÈ°æ¿ì 
//ÀÌ±â´Â °æ¿ì¿Í Áö´Â °æ¿ì°¡ ÃÑ ¸î°¡Áö ÀÎÁö ÀÛ¼º
public class SW6808_CardGame_°­±âÈ£ {
	static int[] cards = new int[9];
	static int[] opp = new int[9];
	static int[] order = new int[9];
	
	static int lose;
	static int win;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			boolean[] isIn = new boolean[19];
			for(int i = 0 ; i < 9 ; i++) {
				int num = Integer.parseInt(st.nextToken());
				isIn[num] = true;
				cards[i] = num;
				
			}
			// »ó´ë¹æ Ä«µå ±¸ÇÏ±â
			int idx = 0;
			for(int i =1 ; i<=18 ; i++) {
				if(!isIn[i]) opp[idx++] = i;
			}
			
			lose=0;
			win=0;
			dfs(0,0);
			sb.append("#").append(t).append(" ").append(win).append(" ").append(lose).append("\n");
		}
		System.out.print(sb);

	}
	
	public static void dfs(int idx , int V) {
		if(idx==9) {
			//Á¡¼ö °è»ê
			int mS=0; int oS=0;
			for(int i = 0 ; i<9 ;i++) {
				int m =cards[i];
				int o = order[i];
				if(m > o) mS+= (m+o);
				else if(m < o) oS+=(m+o);
			}
			if(mS > oS) win++;
			else if(mS < oS) lose++;
			return;
		}
		
		for(int i = 0;i<9 ; i++) {
			if((V&(1<<i))==0) {
				order[idx] = opp[i];
				dfs(idx+1 , V | (1<<i));
			}
		}
	}

}
