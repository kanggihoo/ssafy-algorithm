
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
        Arrays.sort(stars , (o1, o2) -> {
            if(o1[0] != o2[0]) return Integer.compare(o1[0] , o2[0]);
            return Integer.compare(o1[1] , o2[1]);
        });

        // 별 하나 선택()
        int ans = 0;
        for(int i = 0 ; i < K ; i++){
            for(int d = 0 ; d< 4 ; d++){
                int[] t = stars[i];
                int cnt = 0;
                for(int j = 0 ; j <K ; j++) {
                    int[] c = stars[j];
                    if (d == 0) { // 트램펄린의 좌 상단
                        if (t[0] <= c[0] && t[0] + L - 1 >= c[0] && t[1] <= c[1] && t[1] + L - 1 >= c[1]) cnt++;
                    } else if (d == 1) {  // 트램펄린의 우 상단
                        if (t[0] >= c[0] && t[0] - L + 1 <= c[0] && t[1] >= c[1] && t[1] - L + 1 <= c[1]) cnt++;
                    } else if (d == 2) {  // 트램펄린의 좌 하단
                        if (t[0] >= c[0] && t[0] - L + 1 <= c[0] && t[1] <= c[1] && t[1] + L - 1 >= c[1]) cnt++;
                    } else { // 트램펄린의 우 하단
                        if (t[0] >= c[0] && t[0] - L + 1 <= c[0] && t[1] >= c[1] && t[1] - L + 1 <= c[1]) cnt++;

                    }
                    ans = Math.max(cnt , ans);
                }
            }
        }

        System.out.print(K-ans);




    }
}
