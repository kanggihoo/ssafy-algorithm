package P16245_물류허브;
/*

총 운송비용은 각 도시에서 허브 도시까지 왕복에 필요한 최소 비용의 모든 합
허브가 있는 도시의 운송 비용은 0, 도로는 단방향이기 때문에 가는것과 오는 비용의 최소비용다를 수 있음.


 */
import java.util.*;

class UserSolution {

    static List<int[]>[] fwG;
    static List<int[]>[] bwG;
    static Map<Integer , Integer> mapTable;
    static int N;
    // N개 도로, 각 도로의 출발 , 도착 , 운송 비용
    // 도시의 총 개수 반환
    public int init(int N, int sCity[], int eCity[], int mCost[]) {

        mapTable = new HashMap<>();
        int idx =0;
        fwG = new ArrayList[600];
        bwG = new ArrayList[600];
        for(int i = 0 ; i <600 ; i ++){
            fwG[i] = new ArrayList<>();
            bwG[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < N ; i++){

            if (!mapTable.containsKey(sCity[i])) mapTable.put(sCity[i], idx++);
            int s = mapTable.get(sCity[i]);
            if (!mapTable.containsKey(eCity[i])) mapTable.put(eCity[i], idx++);
            int e = mapTable.get(eCity[i]);
            int cost = mCost[i];

            fwG[s].add(new int[]{e,cost});
            bwG[e].add(new int[]{s,cost});
        }
        this.N = mapTable.size();
        return mapTable.size();
    }

    // 출발 도시 s , 도착도시 e , 운송비용 mCost인 간선 추가
    // init에서 없던 도시는 주어지지 않고, 이미 존재하는 간선 X

    public void add(int sCity, int eCity, int mCost) {
        int s = mapTable.get(sCity);
        int e = mapTable.get(eCity);
        fwG[s].add(new int[]{e,mCost});
        bwG[e].add(new int[]{s,mCost});
        return;
    }

    // mHub에 허브 설치한 경우 총운송 비용 계산
    public int cost(int mHub) {
        int start = mapTable.get(mHub);
        int[] d1 = dij(start , fwG);
        int[] d2 = dij(start , bwG);
        int ans = 0;
        for(int i = 0; i <N ; i++){
            ans += d1[i]+d2[i];
        }
        return ans;
    }

    public int[] dij(int start , List<int[]>[]G){
        Queue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1],o2[1]));
        int[] D = new int[N];
        for(int i = 0 ; i < N ; i++) D[i] = Integer.MAX_VALUE;
        D[start] = 0;
        pq.offer(new int[]{start , 0 });

        while(!pq.isEmpty()){
            int[] info = pq.poll();
            int cur = info[0]; int dist = info[1];

            if(D[cur] < dist) continue;

            for(int[] adjInfo : G[cur]){
                if(D[adjInfo[0]] > D[cur]+adjInfo[1]){
                    D[adjInfo[0]] = D[cur]+adjInfo[1];
                    pq.offer(new int[]{adjInfo[0] , D[adjInfo[0]]});
                }
            }
        }

        return D;
    }
}