package BF.G;

// 지표면에 떨어지는 별똥별의 수를 최소화
// LxL 트램펄린으로 별을 튕겨낼 거임.
// 최대한 많은 별똥별을 튕겨내도록 배치했을때 지구에 부딪히는 별똥별?
// 트램펄린 모서리에 다아도 되며, 비스듬하게 배치안됨. ??
// 트램펄린 1개 배치하는건게

// k개 100여서 이거 이용해야 할거 같은데 2차원 배열 순환하는것도 시간초과야
// 가장 멀리떨어진 K를 포함하냐 안하냐 이런식으로 ??

// k개 순환하면서 트램펄린의 가장 좌상단 모서리에 포함된다고 할때 L 범위에 있는 k가 몇개인지??  이런식으로 ?
// 어떻게 시간초과가 안나지??

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G3_14658 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken()); // 트랜펄린 크기
        int K = Integer.parseInt(st.nextToken());

        int[][] stars = new int[K][2];
        for(int i = 0 ; i < K ; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            stars[i][0] = y;
            stars[i][1] = x;
        }

        // 별 하나 선택()
        int ans = 0;
        for(int i = 0 ; i < K ; i++){
        	for(int j = 0 ; j < K ; j++) {
        		int s1y = stars[i][0]; int s1x = stars[i][1];
        		int s2y = stars[j][0]; int s2x = stars[j][1];
        		
        		int rectangleY = Math.min(s1y, s2y);
        		int rectangleX = Math.min(s1x, s2x);
        		
        		int cnt = 0;
        		for(int k = 0 ; k < K ; k++) {
        			int s3y = stars[k][0];int s3x = stars[k][1];
        			if(s3y >= rectangleY && s3y <= rectangleY+L && s3x >= rectangleX && s3x <=rectangleX+L) cnt++;
        		}
        		ans = Math.max(cnt, ans);
        	}
       }

        System.out.print(K-ans);




    }
}
