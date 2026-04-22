package sortAndSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FerrisWheel {
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int limit = Integer.parseInt(st.nextToken());
        int[] W = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++) {
        	W[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(W);
        int l = 0 ; int r = N-1;
        int ans =0;
        while(l < r) {
        	int twoSum = W[l]+W[r];
        	if(twoSum <= limit) {
        		ans++;
        		l++;
        		r--;
        	}else {
        		ans++;
        		r--;
        	}
        }
        if(l==r) {
        	ans++;
        }
        
        System.out.print(ans);
       
    }
	

}
