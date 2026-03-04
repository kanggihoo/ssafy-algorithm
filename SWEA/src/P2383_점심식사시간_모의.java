import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
NxN 방 , 점심을 먹기 위해 아래 층으로 최대한 빠르게 내려가야함.
방안의 사람은 P , 계단 입구를 S라고 할때 이동완료 시간은 모든 사람이 계단을 내려가고, 아래 층으로 이동완료한 시간

아래층 이동 = 계단 입구까지 이동 + 계단 내려가는 시간

1. 계단 입구 이동
- 현재 위치에서 계단 입구까지 걸리는 시간 =>맨허튼 거리로 

2. 계단 내려가는 시간
- 계단 입구 도착시 , 1분 대기 후 아래칸으로 갈 수 있음. 
- 계단은 최대 3명 이용가능. 그 중 한명 내려가면 다음사람 출발가능
- 계단마다 소요되는 시간 K가 존재 

 
 사람수 1~10명 
 계단 입구는 무조건 2개 , 계단 길이는 2~10 
 
 -> 사람 수 10명이어서 미리 어느 계단으로 갈지를 모든 경우의 수 구할 수 있긴 한데, 
1.  각 사람이 1번, 2번 계단 까지 걸리는 시간을 처음에 구하고 정렬
2. 특정 사람이 어느 계단으로 갈지 비트 마스킹으로 모든 조합 구하기 
3. 각 조합에 대해 시뮬레이션?? 
 계단에서 대기 하는 대기 큐 (계단 도착시간, 계단대기 시간 1초 고려해서) 
 그리고 계단도 큐에 넣고 해야하나?(진입시간+K)로 넣고, 현재 시간이 K가 되면 빼는 식으로?
 */
import java.util.*;
public class P2383_점심식사시간_모의 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        for(int t = 1 ; t<=T ; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 방 한변길이 
            List<Integer>people = new ArrayList<>();
            List<int[]>stair = new  ArrayList<>();
            
            for(int i = 0 ; i < N ; i++) {
            	st = new StringTokenizer(br.readLine());
            	for(int j =0 ; j<N;j++) {
            		int n = Integer.parseInt(st.nextToken()); 
            		if(n == 1) {
            			people.add(i*N+j);
            		}else if(n >1) {
            			stair.add(new int[] {i*N+j , n}); // (위치 , 계단길이)
            		}
            	}
            }
            
            // 사람이 위치할 계단 위치 구하기 (0이면 0번 계단 , 1이면 1번 계단)  
            int pCnt = people.size();
            int ans = Integer.MAX_VALUE;
            for(int i = 0 ; i < (1<<pCnt) ; i++) {
            	
            	Queue<Integer> s0 = new PriorityQueue<>((e1,e2)->Integer.compare(e1, e2));
            	Queue<Integer> s1 = new PriorityQueue<>((e1,e2)->Integer.compare(e1, e2));
            	
            	for(int j = 0 ; j < pCnt ; j++) {
            		int py = people.get(j) / N;
            		int px = people.get(j) % N;
            		if((i&(1<<j)) == 0) { // 0번계단
            			int sy = stair.get(0)[0] / N;
            			int sx = stair.get(0)[0] % N;
            			s0.offer(Math.abs(py-sy)+Math.abs(px-sx));
            		}else { // 1번계단
            			int sy = stair.get(1)[0] / N;
            			int sx = stair.get(1)[0] % N;
            			s1.offer(Math.abs(py-sy)+Math.abs(px-sx));
            		}
            	}            	
            	int res1 = simulation(s0 , stair.get(0)[1]);
            	int res2 = simulation(s1 , stair.get(1)[1]);
            	ans = Math.min(ans, Math.max(res1, res2));

            }

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }
	
	public static int simulation(Queue<Integer> s , int stairSize) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		while(!s.isEmpty()) {
    		int enter = s.poll();
    		if(q.size()< 3) {
    			q.offer(enter+1+stairSize);
    		}else {
    			int pop = q.poll();
    			if(pop <= enter) {
    				q.offer(enter+1+stairSize);
    			}else {
    				q.offer(pop+stairSize);
    			}
    		}
    	}
    	
    	// 큐에서 가장 큰 원소 반환 
    	int res = 0;
    	while(!q.isEmpty()) {
    		res = Math.max(res, q.poll());
    	}
    	return res;
	}

}
