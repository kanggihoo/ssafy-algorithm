package TS.G;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// N 명의 사람, 모두가 한 가족처럼 살아감.
// 화재가 나서 계보가 모두 불탐. => 복원 작업
// N명의 사람은 각자 기억하는 조상이름을 구함. => 모두 기억하는 것은 완벽 
// 각 가문은 한 명의 시조 root로 하는 트리 형태
// 이를 기반으로 몇 개의 가문이 존재했는지 각 가문 정보 출력하라

// 1. 특정 점에서 다음으로 연결되는 노드가 없을 때까지 이동 후 최종 반환되는게 최상위 조상
// 2. 주어지는 연결 정보를 반대로 뒤집어서 최상위 조상으로 부터 bfs + degree 정보 이용해서 degree가 0 이되는경우 현재 depth에 저장 후 나중에 이름순으로 정렬
// 3. 부모 - 자식 관계를 다시 dfs 돌면서 특정 위치의 자식의 수가 몇명인지 계산?? 
// 

import java.util.*;
public class G2_21276 {
	static StringBuilder sb;
	static boolean[] V;
	static List<Integer>[] G;
	
	public static void main (String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		String[] people = new String[N];
		for(int i = 0 ; i < N ; i++) {
			people[i] = st.nextToken();
		}
		Arrays.sort(people);
		Map<String , Integer> stringToNum = new HashMap<>();
		Map<Integer , String> numToString = new HashMap<>();
		for(int i = 0 ; i < N ; i++) {
			stringToNum.put(people[i], i);
			numToString.put(i, people[i]);
		}
		G = new ArrayList[N];
		int[] degree = new int[N];
		for(int i = 0 ; i < N ; i++) G[i] = new ArrayList<>();
		
		int M = Integer.parseInt(br.readLine());
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int c = stringToNum.get(st.nextToken());
			int p = stringToNum.get(st.nextToken());
			G[p].add(c);
			degree[c]++;
		}
		
		// 조상 구하기 
		List<Integer> tops = new ArrayList<>();
		for(int i = 0 ; i < N ; i++) {
			if(degree[i] ==0) tops.add(i);
		}
		tops.sort(null);
		sb.append(tops.size()).append("\n");
		for(int t: tops) {
			sb.append(numToString.get(t)).append(" ");
		}
		sb.append("\n");
		
		
		// 조상부터 dfs 
		List<String>[]G2 = new ArrayList[N];
		for(int i = 0 ; i < N ; i ++) G2[i] = new ArrayList<>();
		
		for(int t : tops) {
			// dfs 순환 
			Queue<Integer> q = new ArrayDeque<Integer>();
			q.offer(t);
			
			while(!q.isEmpty()) {
				int cur = q.poll();
				
				for(int adj : G[cur]) {
					degree[adj] -=1;
					if(degree[adj] ==0) {
						G2[cur].add(numToString.get(adj));
						q.offer(adj);
					}
				}
			}
		}
		
		// 최종 출력
		for(int i = 0 ; i < N ; i ++) {
			String cur = numToString.get(i);
			sb.append(cur).append(" ").append(G2[i].size()).append(" ");
			if(G2[i].size() >= 1) {
				G2[i].sort(null);
				for(String s : G2[i]) sb.append(s).append(" ");
			}
			sb.append("\n");	
		}
		System.out.print(sb);
	}
	
	
}
