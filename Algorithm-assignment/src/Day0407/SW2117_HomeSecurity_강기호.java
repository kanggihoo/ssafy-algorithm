package Day0407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

class Home{
	int y;
	int x;
	Home(int y , int x){
		this.y = y;
		this.x = x;
	}
	
}

public class SW2117_HomeSecurity_강기호 {
	
	
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
             
            // 모든 위치에서 최대 K 범위 까지 확인
            int ans = 0;
            for(int i = 0 ;  i< N ; i++) {
                for(int j = 0 ; j<N ; j++) {
                    for(int k = 1;k<=maxK ; k++) {
                        int cnt = 0;
                        int cost = k*k+(k-1)*(k-1);
                        for(int h : homeP) {
                            int hy = h/N;
                            int hx = h%N;
                            int dist = Math.abs(i-hy) + Math.abs(j-hx);
                            if(dist < k ) cnt++;
                        }
                        int profit = cnt*M - cost;
                        if(profit >=0 && ans < cnt) ans = cnt; 
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
 
        System.out.print(sb);
	}

}
