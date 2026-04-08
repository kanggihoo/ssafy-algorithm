package DP.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class G5_17485 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] A = new int[N][M];
		int[][][] D = new int[N+1][M][3];

		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 1 ; i<= N ; i++) {
            for(int j = 0 ; j<M ; j++){
                for(int d = 0 ; d<3 ; d++) D[i][j][d] = Integer.MAX_VALUE;
            }
		}

        for(int j = 0 ; j < M ; j++){
            if(j == 0){
                D[1][j][1] = D[1][j][2] = A[0][j];
            }else if(j==M-1){
                D[1][j][0] = D[1][j][1] = A[0][j];

            }else{
                D[1][j][0] = D[1][j][1] = D[1][j][2] = A[0][j];
            }

        }

		for(int i = 1 ; i <= N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(j-1 < 0) {
                    D[i][j][1] = Math.min(D[i-1][j][0], D[i-1][j][2]) +A[i-1][j];
                    D[i][j][2] = Math.min(D[i-1][j+1][0], D[i-1][j+1][1]) +A[i-1][j+1];
				}else if(j+1 >=M) {
                    D[i][j][0] = Math.min(D[i-1][j-1][1], D[i-1][j-1][2]) +A[i-1][j-1];
                    D[i][j][1] = Math.min(D[i-1][j][0], D[i-1][j][2]) +A[i-1][j];
				}else {
					D[i][j][0] = Math.min(D[i-1][j-1][1], D[i-1][j-1][2]) +A[i-1][j-1];
                    D[i][j][1] = Math.min(D[i-1][j][0], D[i-1][j][2]) +A[i-1][j];
                    D[i][j][2] = Math.min(D[i-1][j+1][0], D[i-1][j+1][1]) +A[i-1][j+1];
				}

			}
		}

		int ans = Integer.MAX_VALUE;
		for(int j = 0 ; j < M ; j++) {
            for(int d =0 ; d<3 ; d++){
                ans = Math.min(ans, D[N][j][d]);
            }

		}
		System.out.print(ans);




	}

}
