import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.util.*;

public class G2_2450 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N  = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] target = new int[N];
        int[] cnt = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ;  i< N ; i++){
            A[i] = Integer.parseInt(st.nextToken());
            cnt[A[i]]++;
        }
        int ans = Integer.MAX_VALUE;
        // 6가지 경우의 수
        for(int i = 1 ; i < 4 ; i++){
            for(int j = 1 ; j <4 ; j++){
                if(i==j) continue;
                for(int k = 1 ; k<4 ; k++){
                    if(i==k || j==k) continue;
                    int curCnt =0;
                    // 정답 배열 만들기
                    int idx = 0;
                    for(int x : Arrays.asList(i,j,k)){
                        for(int t = 0 ; t<cnt[x] ; t++){
                            target[idx++] = x;
                        }
                    }
                    // 현재 배열과 정답 배열간의 통계량 계산
                    int[][] info = new int[4][4];
                    for(int idxx = 0 ; idxx < N ; idxx++){
                        int answer = target[idxx];
                        int cur = A[idxx];
                        if(answer == cur) continue;
                        info[answer][cur]++;
                    }

                    // 현재 target에 대한 정답 구하기
                    for(int y = 1 ; y<3 ; y++ ){
                        for(int x =y+1 ; x<=3 ; x++){
                            int minV =Math.min(info[y][x],info[x][y]);
                            curCnt += minV;
                            info[y][x] -= minV;
                            info[x][y] -= minV;
                        }
                    }
                    curCnt += 2*(Math.abs(info[1][2] - info[2][1]));

//                    System.out.println(Arrays.toString(target));
//                    System.out.println("최솟값 : " +curCnt);
                    ans = Math.min(ans,curCnt);
                }

            }
        }


        System.out.print(ans);
    }
}
