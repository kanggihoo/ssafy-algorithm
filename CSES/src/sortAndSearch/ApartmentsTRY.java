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
        int[] aparts = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++) {
        	people[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < M ; i++) {
        	aparts[i] = Integer.parseInt(st.nextToken());
        }
        
        // 정렬
        Arrays.sort(people);
        Arrays.sort(aparts);
        int ans = 0;
        int idx = 0;
        for(int apart : aparts) {
        	if(idx == N)break;
        	
        	int cur = people[idx];
        	if(cur-K <= apart && cur+K >= apart) {
        		idx++;
        		ans++;
        	}else if(cur+K< apart) {
        		idx++;
        		while(idx < N && people[idx] -K <=apart) {
        			if(people[idx]+K>=apart) {
        				ans++;
        				idx++;
        				break;
        			}
        			idx++;
        		}
        	}
        	
        }
        
        
        System.out.print(ans);
       
    }
}
