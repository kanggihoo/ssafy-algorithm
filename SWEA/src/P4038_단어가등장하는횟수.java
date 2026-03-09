import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class P4038_단어가등장하는횟수 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        int MOD = (int)Math.pow(10, 9)+7;

        for(int t = 1 ; t<=T ; t++){
            
            String P = br.readLine();
            String C = br.readLine();
            int pSize = P.length();
            int cSize = C.length();
            
            long window =0; 
            long comp=0;
            int ans = 0;
            long weight=1;
            for(int i = 0 ; i+cSize <=pSize ;i++) {
            	if(i == 0) { // 처음 윈도우 구하기
            		window = P.charAt(0);
            		comp =C.charAt(0); 
            		for(int j = 1 ; j<cSize ; j++) {
            			window = ((window*128)%MOD + P.charAt(j))%MOD;
            			comp = ((comp*128)%MOD + C.charAt(j))%MOD;
            			weight = (weight*128)%MOD;
            		}
            		if(window == comp) ans++;
            	}else {
            		window = (MOD+window-(P.charAt(i-1)*weight)%MOD)%MOD;
            		window = ((window*128)%MOD + P.charAt(i+cSize-1))%MOD;
            		if(window==comp) ans++;
            	}
            }
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }


}
