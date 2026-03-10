package P14611_계산게임;


import java.util.*;

/*
카드 나열 하고 인접한 카드의 값을 이용하여 계산하는 게임
카드는 두종류 (일반, 조커)
    - 일반카드 : 1~30 중 한개로 카드 계산할때 이용
    - 조커 카드 : -1 카드로 1~30중의 하나로 변경가능

<게임설명>
1. 초기 5장 카드가 가로 방향으로 나열되고, 조커 값이 주어짐.
2. 임의 카드 5장과 나열할 방향이 주어지면 왼쪽, 또는 오른쪽 방향으로 주어진 카드를 붙힌다.
3. 계산 결과값 mNum과 순서 mNth가 주어지면 아래 방식으로 계산 후 조건에 맞는 카드 찾기
    - 1) 가장 왼쪽부터 한 칸씩 이동해서 카드 선택
    - 2) 선택한 카드와 오른쪽 인접 3개(총 4개 카드) 합을 20으로 나눈 나머지가 mNum과 동일한지 확인
    - 3) (2)번 조건에 맞는 mNth 카드 찾으면 그 카드와 인접한 3장을 반환

조커값은 게임중간마다 변경가능하고 , 가장 최근에 변경된 조커값을 사용

*/

class UserSolution {
    int[] table;
    Deque<Integer>[][] D;
    int l; int r;
    int jV;
    int MOD = 20;



    // 처음 테이블에 나열할 5장 카드(무조건 5장) (왼->오)
    // 조커에서 사용할 값이 주어짐.
    void init(int mJoker, int mNumbers[]) {
        table = new int[100005];

        // D 초기화
        D = new ArrayDeque[5][20];
        for(int i = 0 ; i <5 ; i++){
            for(int j = 0 ; j < 20 ; j++){
                D[i][j] = new ArrayDeque<>();
            }
        }
        // 초기 위치 초기화
        r = 50000;
        l = 49999;
        jV = mJoker;
        // 처음 제공되는 5개 카드 초기화 및 누적합 구하기
        for (int i = 0; i < 5; i++) {
            int curNum = mNumbers[i];
            table[r+i] = curNum;
        }
        // D 업데이트
        for(int i = r ; i <r+2 ; i++){
            int jCnt = 0;
            int sum = 0;
            for(int j = 0 ; j < 4 ; j++){
                if(table[i+j] == -1){
                    jCnt++;
                }else {
                    sum+=table[i+j];
                }
            }
            D[jCnt][sum%MOD].offer(i);
        }
        r+=5;
    }

    // 추가로 나열할 5장의 카드(무조건5) 가 주어짐.(왼->오)
    // mDir : 0 => 현재 카드의 왼쪽에 추가 , 1: => 가장 오른쪽에 추가
    // 호출 횟수 : 10000회
    void updateSum(int mDir , int[] mNumbers){
        if (mDir == 0) { // 왼쪽에 추가
            //누적합 및 D 업데이트
            for (int i = 0; i < 5; i++) {
                int curNum = mNumbers[5-i-1];
                table[l-i] = curNum;
            }
            // D 업데이트
            for(int i = l ; i>l-5 ; i--) {
                int jCnt =0;
                int sum=0;
                for(int j = 0 ; j < 4 ; j++) {
                    if(table[i+j] == -1) {
                        jCnt++;
                    }else {
                        sum += table[i + j];
                    }
                }
                D[jCnt][sum%MOD].offerFirst(i);
            }
            l-=5;
        } else { // 오른쪽에 추가
            for (int i = 0; i < 5; i++) {
                int curNum = mNumbers[i];
                table[r+i] = curNum;
            }
            // D 업데이트
            for(int i = r-3 ; i<r+2 ; i++) {
                int jCnt =0;
                int sum=0;
                for(int j = 0 ; j < 4 ; j++) {
                    if(table[i+j] == -1) {
                        jCnt++;
                    }else {
                        sum += table[i + j];
                    }
                }
                D[jCnt][sum%MOD].offer(i);
            }
            r+=5;
        }

    }
    void putCards(int mDir, int mNumbers[]) {
        updateSum(mDir , mNumbers);
    }

    // 인접한 카드 계산 결과가 mNum인 mNth 번째 카드 찾고, 그 카드와 오른쪽 3개 카드를 ret에 기록
    // 조건에 맞는 카드를 찾으면 1, 찾을 수 없으면 0(이때 ret 배열 무시)
    // ret의 배열 크기는 항상 4 , 조커이면 -1로 넣기
    //    mNum : 찾아야 하는 계산 결과값 (0 ≤ mNum ≤ 19)
    //    mNth : 찾아야 하는 결과값이 나와야 하는 횟수 (1 ≤ mNth ≤ 1,000)
    int findNumber(int mNum, int mNth, int ret[]) {
        List<Deque> dqList = new ArrayList<>();
        int total=0;
        for(int jCnt = 0 ; jCnt<5 ; jCnt++){
            int requiredV = (MOD+mNum-(jCnt*jV)%MOD)%MOD;
            Deque<Integer> dq = D[jCnt][requiredV];
            if(dq.size() >0){
                total+=dq.size();
                dqList.add(dq);
            }

        }
        if(total < mNth){
            return 0;
        }
        // dqList 순환하면서 mNth 번째 인덱스 찾기
        PriorityQueue<int[]> pq = new PriorityQueue<>((e1,e2)->Integer.compare(e1[0] , e2[0]));
        Iterator<Integer>[] iterators = new Iterator[dqList.size()];

        // 각 dq의 첫번째 원소값 빼기
        for(int i = 0 ; i < dqList.size() ; i++){
            Iterator<Integer> it = dqList.get(i).iterator();
            iterators[i] = it;
            pq.offer(new int[]{it.next(),i});
        }

        // mTh번 반복
        int resIdx = 0;
        for(int i = 0 ; i<mNth ; i++){
            int[] current = pq.poll();
            resIdx = current[0];
            if(iterators[current[1]].hasNext()){
                pq.offer(new int[]{iterators[current[1]].next(), current[1]});
            }
        }
        // ret 배열 업데이트
        for(int i = 0 ; i <4 ; i++){
            ret[i] = table[resIdx+i];
        }
        return 1;

    }

    // 조커 값을 mValue로 변경
    void changeJoker(int mValue) {
        jV= mValue;
    }
}
