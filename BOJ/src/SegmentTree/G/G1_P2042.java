package SegmentTree.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class G1_P2042 {


    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 업데이트 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간합 횟수

        // 세그 먼트 트리 구하기
        int k = 0;
        while((int)Math.pow(2,k) < N){
            k++;
        }
        int size = (int)Math.pow(2,k+1);
        BigInteger[] T = new BigInteger[size];
        for(int i = 1 ; i < size ; i++) T[i] = BigInteger.ZERO;
        int offSet = (int)Math.pow(2,k);
        int endIdx = offSet*2;

        // 트리의 리프 노드에 원본 배열 삽입
        for(int i = 0 ; i < N ; i++){
            T[offSet+i] = BigInteger.valueOf(Long.parseLong(br.readLine()));
        }

        // 뒤에서 부터 순회하면서 구간 합 계산
        for(int i = endIdx-1 ; i >= 2 ; i--){
            T[i/2] = T[i/2].add(T[i]);
        }

        // M+K 만큼 쿼리 연산 처리
        for(int i = 0 ; i < M+K ; i++){
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            if(q == 1){ // 특정 노드 업데이트
                int idx = Integer.parseInt(st.nextToken());
                Long updated = Long.parseLong(st.nextToken());
                update(T , offSet, idx , updated);
            } else if (q==2) { //구간 합 구하기
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                BigInteger rangeSum = getRangeSum(T ,offSet, s , e);
                sb.append(rangeSum).append("\n");
            }
        }
        System.out.print(sb);

    }

    public static void update(BigInteger[] T , int offSet , int idx , Long v){
        // idx를 리프 노드 인덱스로 변환
        int leafIdx =  offSet-1+idx;

        T[leafIdx] = BigInteger.valueOf(v);
        leafIdx /=2;

        // 위로 올라가면서 구간 합 업데이트
        while(leafIdx > 0){
            T[leafIdx] = T[leafIdx*2].add(T[leafIdx*2+1]);
            leafIdx /=2;
        }
    }

    public  static BigInteger getRangeSum(BigInteger[] T , int offSet , int s , int e){
        BigInteger res = BigInteger.ZERO;
        int sIdx = offSet+s-1;
        int eIdx = offSet+e-1;
        while(sIdx <= eIdx){
            // sIdx가 부모의 오른쪽인 경우
            if(sIdx%2 ==1){
                res = res.add(T[sIdx]);
            }
            sIdx = (sIdx+1)/2;

            // eIdx가 부모의 왼쪽인 경우
            if(eIdx%2 == 0){
                res = res.add(T[eIdx]);
            }
            eIdx  = (eIdx-1)/2;
        }
        return res;
    }
}
