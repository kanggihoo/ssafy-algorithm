package PQ.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;

public class G2_1781 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine()); // 문제의 개수 N
        List<int[]> problems = new ArrayList<>();

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int reward = Integer.parseInt(st.nextToken());
            problems.add(new int[]{deadLine , reward});
        }

        // 1. 데드라인을 기준으로 오름차순 정렬
        problems.sort((o1,o2)->Integer.compare(o1[0] , o2[0]));

        // 2. 컵라면 보상을 담을 최소 힙 (가장 적은 보상이 루트에 오도록 설정)
        Queue<Integer> pq = new PriorityQueue<>();
        long ans = 0; // 누적 보상이 int 범위를 초과할 수 있으므로 long 사용

        for(int[] problem : problems){
            int d = problem[0]; int r = problem[1];

            // 현재 문제의 보상을 큐에 삽입 (일단 문제를 푼다고 가정)
            pq.offer(r);

            // 3. 큐의 크기(푼 문제 수)가 현재 데드라인을 초과하면 가장 보상이 적은 문제를 포기
            if(pq.size() > d) {
                pq.poll();
            }
        }

        // 4. 최종적으로 큐에 남아있는 문제들의 보상이 얻을 수 있는 최대 컵라면 수
        while(!pq.isEmpty()) {
            ans += pq.poll();
        }

        System.out.print(ans);
    }
}