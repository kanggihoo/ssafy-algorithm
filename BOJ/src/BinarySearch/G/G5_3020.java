package BinarySearch.G;

/*
동굴 길이 N , 높이 H (N은 짝수)

 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class G5_3020 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] A1 = new int[N/2];
        int[] A2 = new int[N/2];
        for(int i = 0 ; i < N/2; i ++ ){
            A1[i] = Integer.parseInt(br.readLine());
            A2[i] = Integer.parseInt(br.readLine());
        }

        // 정렬
        Arrays.sort(A1);
        Arrays.sort(A2);

        // 높이 H에 대한탐색
        int ans = N+1;
        int cnt = 0;
        for(int h = 1 ; h<=H ; h++){
            int res1 = binarySearch(A1, h);
            int res2 = binarySearch(A2, H-h+1);
            int res = N/2-res1 + N/2-res2;
            if(res < ans){
                ans = res;
                cnt = 1;
            }else if(res == ans) cnt ++;
        }
        System.out.printf("%d %d" , ans , cnt);
    }


    public static int binarySearch(int[] A , int t){
        int s = 0;
        int e = A.length -1;

        while(s<=e){
            int mid= s + (e-s) / 2;

            if(A[mid] < t ){
                s = mid+1;
            }
            else{
                e = mid-1;
            }
        }
        return s;

    }
}
