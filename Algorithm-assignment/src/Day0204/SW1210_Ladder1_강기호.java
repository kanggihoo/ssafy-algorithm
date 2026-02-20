package Day0204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//어느 사다리를 고리면 X 표시에 도착하는지 구하기 
//도착지점은 2로 표현 
//도착 지점 찾아서 거꾸로 가서 도착되는 열좌표 찾으면 될듯?


public class SW1210_Ladder1_강기호 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		char A[][] = new char[100][102];
		for(int v = 0 ; v < 10 ; v++) {
			int T = Integer.parseInt(br.readLine());
			for(int i = 0 ; i < 100 ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < 100 ; j++) {
					A[i][j+1] = st.nextToken().charAt(0);
				}
				A[i][0] ='0';
				A[i][101] ='0';
			}
				
			// 가장 아래에서 2찾기
			int col=0;
			for(int i = 1 ; i<=100 ; i++) {
				if(A[99][i] =='2') {
					col = i;
					break;
				}
			}
			
//			// start 지점에서 부터 올라가기
			int row = 99;
			while(row > 0) {
				
				// 현재 위치에서 다른 점 이동가능할때까지 row 감소
				while(row >0 && A[row][col-1]=='0' && A[row][col+1]=='0') {
					row--;
				}
				if(row ==0) break;
				// x축 이동방향 결정 후 1회 이동
				int dx = A[row][col-1]=='1' ? -1 : 1;
				col += dx;
				
				// y축 1이면 이동
				while(A[row][col]=='1') col+=dx;
				col-=dx;
				row -=1;
			}
			sb.append("#").append(T).append(" ").append(col-1).append("\n");
			
		}
		System.out.print(sb);
		

	}

}
