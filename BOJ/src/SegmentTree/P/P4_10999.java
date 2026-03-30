package SegmentTree.P;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 지연세그먼트 트리
public class P4_10999 {
    static long[] A;
    static long[] tree;
    static long[] lazy;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken()); //  수의 개수
        int M = Integer.parseInt(st.nextToken()); // 수의 변경 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간 합 구하기 갯수
        A = new long[N];
        for(int i = 0 ; i < N ; i++){
            A[i] = Long.parseLong(br.readLine());
        }
        // 트리 초기화
        tree = new long[N*4];
        lazy = new long[N*4];
        init(1,1,N);

        // M+K개 만큼 연산 정보
        for(int i = 0 ; i <M+K ; i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if(type == 1){ // 구간에대해 동일한 수 더하기
                long v = Long.parseLong(st.nextToken());
                update(1,1,N,start,end,v);
            }else{ // 구간 합 구하기
                long ans = query(1,1,N,start , end);
                sb.append(ans).append("\n");
            }
        }
        System.out.print(sb);
    }

    public static Long init(int cur , int start , int end){
        if(start == end) return tree[cur] = A[start-1];
        int mid = start + (end-start)/2;
        return tree[cur] = init(cur*2 , start , mid) + init(cur*2+1 , mid+1 , end);
    }

    public static void push(int cur , int start , int end){
        if(lazy[cur] != 0){
            tree[cur] += (end-start+1)*lazy[cur];
            if(start != end){
                lazy[cur*2] += lazy[cur];
                lazy[cur*2+1] += lazy[cur];
            }
            lazy[cur] = 0;
        }
    }

    public static void update(int cur , int start, int end , int rLeft , int rRight , long val ){
        push(cur , start , end);
        if(start > rRight || end < rLeft) return ; // 구간 완전히 벗어난 경우
        if(start >= rLeft && end <= rRight){ // 구간 완전히 포함된경우
            lazy[cur] += val;
            push(cur , start , end);
            return;
        }
        int mid = start + (end-start) /2;
        update(cur*2 , start , mid , rLeft , rRight , val);
        update(cur*2+1 , mid+1 , end , rLeft , rRight , val);
        tree[cur] = tree[cur*2] + tree[cur*2+1];
    }

    public static long query(int cur , int start , int end , int rLeft , int rRight){
        push(cur , start , end);
        if(start > rRight || end < rLeft) return 0;
        if(rLeft <= start && end<=rRight) return tree[cur];
        int mid = start+(end-start)/2;
        return query(cur*2 , start , mid , rLeft , rRight) + query(cur*2+1 , mid+1 , end , rLeft, rRight);
    }
}
