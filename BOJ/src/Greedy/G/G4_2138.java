package Greedy.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_2138 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] O = new int[N];
        int[] T = new int[N];

        String line = br.readLine();
        for(int i = 0 ; i < N ; i++){
            O[i] = line.charAt(i) - '0';
        }
        line = br.readLine();
        for(int i = 0 ; i < N ; i++){
            T[i] = line.charAt(i) - '0';
        }

        int case1 = check(O.clone() , T);
        O[0] = (O[0]+1) %2 ;
        O[1] = (O[1]+1) %2 ;
        int case2 = check(O,T)+1;
//        System.out.printf("%d %d \n" , case1, case2);
        if(case1 >= N+1 && case2 >= N+1) System.out.print(-1);
        else System.out.print(Math.min(case1,case2));
    }

    public  static int check(int[] O , int[] T){
        int cnt = 0;
        for(int i = 1 ; i < O.length ; i++){
            if(O[i-1] != T[i-1]){
                cnt ++;
                for(int j = -1 ; j <=1 ; j++){
                    if(i+j>=O.length) continue;
                    O[i+j] = (O[i+j] +1 ) %2;
                }
            }
        }
        // 맨 마지막 확인
        int e = O.length-1;
        if(O[e] != T[e]) return O.length+1;
        return cnt;
    }



}
