package intro;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MexGridConstruction {
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        
        // 초기화
        for(int i = 0 ; i < N ; i++) {
        	map[0][i] = i;
        	map[i][0] = i;
        }
        
        for(int i = 1 ; i < N ; i++) {
        	for(int j = 1 ; j < N ; j++) {
        		// (i,j) 의 숫자 결정하기
        		boolean[] V = new boolean[N*N];
        		for(int r = 0 ; r<i ; r++) {
        			V[map[r][j]] = true;
        		}
        		
        		for(int c = 0 ; c<j ; c++) {
        			V[map[i][c]] = true;
        		}
        		int target = -1;
        		for(int t = 0 ; t < V.length ; t++) {
        			if(!V[t]) {
        				target = t;
        				break;
        			}
        		}
        		
        		map[i][j] = target;
        	}
        }
        
        // 출력 
        for(int i = 0 ; i < N ; i++) {
        	for(int j = 0 ; j < N ; j++) {
        		sb.append(map[i][j]).append(" ");
        	}
        	sb.append("\n");
        }
        System.out.print(sb);
    }

}
