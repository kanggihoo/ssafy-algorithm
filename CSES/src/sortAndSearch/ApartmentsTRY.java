package sortAndSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class ApartmentsTRY {

	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] people = new int[N];
        int[] price = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++) {
        	people[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < M ; i++) {
        	price[i] = Integer.parseInt(st.nextToken());
        }
        
        
        int ans = 0;
        System.out.print(ans);
       
    }
}
