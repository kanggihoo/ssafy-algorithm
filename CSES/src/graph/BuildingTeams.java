package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class BuildingTeams {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Integer>[] G = new ArrayList[N+1];
        
        for(int i = 1 ; i <=N ; i++) G[i] = new ArrayList<>();
        
        for(int i = 0 ; i < M ; i++) {
        	st = new StringTokenizer(br.readLine());
        	int n1 = Integer.parseInt(st.nextToken());
        	int n2 = Integer.parseInt(st.nextToken());
        	G[n1].add(n2);
        	G[n2].add(n1);
        }
        
        int[] V = new int[N+1];
        for(int i = 1; i <=N ; i++) {
        	if(V[i] == 0) {
        		V[i] =1;
        		Queue<Integer> q = new ArrayDeque<Integer>();
        		q.offer(i);
        		while(!q.isEmpty()) {
        			int cur = q.poll();
        			
        		}
        	}
        }
       
    }

}
