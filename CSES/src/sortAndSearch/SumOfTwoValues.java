package sortAndSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class SumOfTwoValues {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[] A = new int[N];

        st = new StringTokenizer( br.readLine());
        for(int i = 0 ; i<N ; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer , Integer> map = new HashMap<>();
        boolean flag=false;
        int idx1=0 ; int idx2 = 0;
        for(int i=0 ; i < N ; i++){
            if(flag) break;
            int key = T - A[i];
            if(map.containsKey(key)){
                idx1 = map.get(key)+1;
                idx2 = i+1;
                flag=true;
            }
            map.put(A[i], i);
        }
        if(flag){
            System.out.print(idx1 +" "+idx2);
        }else{
            System.out.print("IMPOSSIBLE");
        }




    }
}
