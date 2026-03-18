package SegmentTree.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G1_14438 {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        
        int K = 1;
        while(N> K) K<<=1;
        
        // 세그 먼트 트리 배열 만들기 
        int[] seg = new int[K<<1];
        st = new StringTokenizer(br.readLine());
        for(int i = K ; i < K<<1; i++) {
        	if(i < K+N) {
        		seg[i] = Integer.parseInt(st.nextToken());
        	}else {
        		seg[i] = Integer.MAX_VALUE;
        	}
        	
        }
        // 세그먼트 트리 채우기 
        for(int i = K-1; i >=1 ; i--) {
        	seg[i] = Math.min(seg[i*2], seg[i*2+1]);
        }
        
        // 쿼리에 대한 동작
        int Q = Integer.parseInt(br.readLine());
        for(int q = 0 ; q < Q ; q++) {
        	st = new StringTokenizer(br.readLine());
        	int c = Integer.parseInt(st.nextToken());
        	int i = Integer.parseInt(st.nextToken());
        	int j = Integer.parseInt(st.nextToken());
        	if(c == 1) { // 업데이트 연산 (i번 을 j로)
        		int treeIdx = i-1+K;
        		seg[treeIdx] = j;
        		while(treeIdx > 1) {
        			treeIdx /= 2;
        			seg[treeIdx] = Math.min(seg[treeIdx*2], seg[treeIdx*2+1]);
        		}
        	}else { // 범위내에 가장 작은 값 출력 [i~j]
        		int treeS = K+i-1;
        		int treeE = K+j-1;
        		int ans = Integer.MAX_VALUE;
        		while(treeS <= treeE) {
        			if(treeS %2 ==1) {
        				ans = Math.min(ans, seg[treeS]);
        				treeS+=1;
        			}
        			if(treeE %2 ==0) {
        				ans = Math.min(ans, seg[treeE]);
        				treeE-=1;
        			}
        			treeS /=2;
        			treeE /=2;
        		}
        		sb.append(ans).append("\n");
        	}
        	
        }
        System.out.print(sb);
    }

}
