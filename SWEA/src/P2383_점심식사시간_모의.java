import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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
public class P2383_점심식사시간_모의 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        for(int t = 1 ; t<=T ; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 셀 개수 
            int M = Integer.parseInt(st.nextToken()); // 격리 시간
            int K = Integer.parseInt(st.nextToken());  // 군집 개수


//            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }

}
