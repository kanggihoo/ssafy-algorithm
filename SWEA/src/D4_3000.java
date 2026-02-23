import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 중간값 구하기

// 한개의 자연수를 공책에 적고, N번에 걸쳐 2개씩을 추가로 공책에 적는다.
// 2개를 추가할 때마다 지금까지 적은 수 중에 크기가 중간이 수를 알려주어야 한다.

public class D4_3000 {



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        Queue<Integer> maxPQ = new PriorityQueue<>((o1, o2) -> Integer.compare(o2,o1));
        Queue<Integer> minPQ = new PriorityQueue<>(Integer::compare);
        int MOD =  20171109;


        for(int t = 1 ; t<=T ; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int mid = Integer.parseInt(st.nextToken());
            maxPQ.clear();
            minPQ.clear();
            int ans = 0;

            for(int i = 0 ; i < N ; i++){
                st = new StringTokenizer(br.readLine());

                // 기존 mid값과 비교하여 max , min pq에 넣기
                for(int j = 0 ; j<2 ; j++){
                    int n = Integer.parseInt(st.nextToken());
                    if(mid > n){
                        maxPQ.offer(n);
                    }else{
                        minPQ.offer(n);
                    }
                }
                // 2개 큐의 원소개수 조정
                while(maxPQ.size() != minPQ.size()){
                    if(maxPQ.size() > minPQ.size()){
                        minPQ.offer(mid);
                        mid = maxPQ.poll();
                    } else if (maxPQ.size() < minPQ.size()) {
                        maxPQ.offer(mid);
                        mid = minPQ.poll();
                    }
                }
                ans = (ans+mid)%MOD;
            }
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }


}
