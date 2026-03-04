import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import java.util.*;
// 운영 비용 , 각 집은 M의 비용 지불가능, 손해 보지 않으면서 최대한 많은 집에 홈방법 서비스 제공 , 그때의 서비스 제공하는 집 개수 출력
public class P2117_홈방범서비스_모의 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
     
        for(int t = 1 ; t<=T ; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); 
            int M = Integer.parseInt(st.nextToken());
            int maxK = 2*N-1;
            // 맵 초기화 
            // 집 위치 저장 리스트
            List<Integer> homeP = new ArrayList<>();
            for(int i = 0 ; i < N ; i++) {
            	st = new StringTokenizer(br.readLine());
            	for(int j = 0 ; j < N ; j++) {
            		int n = Integer.parseInt(st.nextToken());
            		if(n == 1) homeP.add(i*N+j);
            	}
            }
            // 현재 위치에서 모든 집과 떨어진 거리를 미리 구하기
            int ans = 0;
            int[][] D = new int[N*N][2*N]; // (위치, 떨어진거리) = 개수 
            for(int i = 0 ; i <N*N ; i++) {
            	// i번 위치에서의 모든 집과의 거리 계산
        		for(int h : homeP) {
    				int hy = h/N;
    				int hx = h%N;
    				int dist = Math.abs(i/N-hy) + Math.abs(i%N-hx);
    				D[i][dist]++;
            	}
        		// K거리 이하인 개수 없데이트 
        		for(int j = 1; j<2*N ; j++) {
        			D[i][j] += D[i][j-1];
        		}
        		
        		// 계산 한 값을 순환하면서 정답 업데이트 
        		for(int k = 1 ; k<2*N ; k++) {
        			int cnt = D[i][k-1];
        			if(cnt == 0) continue;
        			int cost = k*k+(k-1)*(k-1);
        			int profit = cnt*M - cost;
        			if(profit >=0 && ans < cnt) ans = cnt; 
        		}
            }
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }

}
