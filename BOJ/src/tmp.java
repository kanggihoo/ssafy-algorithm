//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//
///*
//집 구하기
//1. 맥스권 : 집과 맥도날드의 최단거리 x 이하
//2. 스세권 : 스타벅과와 집 사이 최단거리 y이하
//3. 맥세권과 스세권 모두 만족하는 집중 최단거리 합이 최소의 집
//
// */
//public class tmp {
//
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		int N = Integer.parseInt(st.nextToken());
//		int minV = 1001;
//		int ans = 0;
//		int common = 0;
//		st = new StringTokenizer(br.readLine());
//		for(int i = 0 ; i<N; i++){
//			int num = Integer.parseInt(st.nextToken());
//			int[] res = cal(num);
//			common = Math.max(res[1] , common);
//			ans += res[0];
//		}
//		ans += common;
//		System.out.print(ans);
//
//
//	}
//
//
//
//}
