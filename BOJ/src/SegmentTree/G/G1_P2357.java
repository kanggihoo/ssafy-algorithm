package SegmentTree.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
    int maxV;
    int minV;

    public Node() {
        maxV = 0;
        minV = Integer.MAX_VALUE;
    }
}

public class G1_P2357 {


    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] A = new int[N];
        int[][] Q = new int[M][2];
        for(int i = 0 ; i < N ; i++) A[i] = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            Q[i][0] = Integer.parseInt(st.nextToken());
            Q[i][1] = Integer.parseInt(st.nextToken());
        }

        int K = 1;
        while(K <N) K <<= 1;
        Node[] seg = new Node[K<<1];
        for(int i = 1 ; i < K<<1 ; i++){
            seg[i] = new Node();
        }
        // 리프 노드 초기화
        for(int i = 0 ; i < N ; i++){
            seg[K+i].maxV = A[i];
            seg[K+i].minV = A[i];
        }

        // 나머지 노드 초기화
        for(int i = K-1 ; i >=1 ; i--){
            seg[i].minV = Math.min(seg[i*2].minV ,seg[i*2+1].minV);
            seg[i].maxV = Math.max(seg[i*2].maxV ,seg[i*2+1].maxV);
        }

        // M 번 쿼리 연산
        for(int i = 0 ; i < M ; i++){
            int s = Q[i][0]; int e = Q[i][1];
            int sIdx = K+s-1; int eIdx = K+e-1;
            int maxV = 0;
            int minV = Integer.MAX_VALUE;
            while(sIdx <= eIdx){
                if(sIdx % 2 ==1){
                    maxV = Math.max(maxV , seg[sIdx].maxV);
                    minV = Math.min(minV , seg[sIdx].minV);
                    sIdx++;
                }
                sIdx /=2;

                if(eIdx %2 ==0){
                    maxV = Math.max(maxV , seg[eIdx].maxV);
                    minV = Math.min(minV , seg[eIdx].minV);
                    eIdx--;
                }
                eIdx /=2;
            }
            sb.append(minV).append(" ").append(maxV).append("\n");
        }
        System.out.print(sb);
    }
}
