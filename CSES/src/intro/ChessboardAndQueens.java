package intro;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ChessboardAndQueens {
	static boolean[][] map;
	static int ans;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = 8;
        StringBuilder sb = new StringBuilder();
        map = new boolean[N][N];
        
        for(int i = 0 ; i < N ; i++) {
        	int col = 0;
        	for(char c : br.readLine().toCharArray()) {
        		if(c=='*') map[i][col] = true;
        		col++;
        	}
        }
        ans =0;
        dfs(0,0,0,0);
        System.out.print(ans);
    }
	
	public static void dfs(int row , int cS , int d1S , int d2S) {
		if(row == 8) {
			ans++;
			return;
		}
		
		// col 선택
		for(int col = 0 ; col < 8 ; col++) {
			if(map[row][col] || (cS & 1<<col)!=0 || (d1S & 1<<row-col+7)!=0 || (d2S & 1<<row+col) !=0 ) continue;
			dfs(row+1 , cS | 1<<col , d1S | 1<<row-col+7 , d2S | 1<<row+col);
		}
	}

}
