package PQ.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 중앙값 구하기
public class G2_2696 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        Queue<Integer> maxPQ = new PriorityQueue<>((o1,o2)->Integer.compare(o2,o1));
        Queue<Integer> minPQ = new PriorityQueue<>(Integer::compareTo);
        int[] A = new int[10000];
        for(int i = 0 ; i < T ; i++){
            int M = Integer.parseInt(br.readLine());
            maxPQ.clear();
            minPQ.clear();
            int idx = 0;
            for(int j = 0 ; j<M/10+1 ; j++){
                st = new StringTokenizer(br.readLine());
                while(st.hasMoreTokens()){
                    A[idx++]=Integer.parseInt(st.nextToken());
                }
            }

            // 1부터 중간 값 구하기
            int mid = A[0];
            int cnt = 1;
            sb.append(M/2+M%2).append("\n").append(mid).append(" ");
            for(int j = 1; j < M/2 ; j++){
                for(int k = 0 ; k <2 ; k++){
                    int n = A[i+k];
                    if(n < mid) maxPQ.offer(n);
                    else minPQ.offer(n);
                }
                if(minPQ.size() > maxPQ.size()){
                    maxPQ.offer(mid);
                    mid = minPQ.poll();
                } else if (minPQ.size() < maxPQ.size()) {
                    minPQ.offer(mid);
                    mid = maxPQ.poll();
                }
                sb.append(mid).append(" ");
                cnt++;
                if(cnt >=10) {
                    cnt =0;
                    sb.append("\n");
                }
            }
        }
        System.out.print(sb);



    }


}
