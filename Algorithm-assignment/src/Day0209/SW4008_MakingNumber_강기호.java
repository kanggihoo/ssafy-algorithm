package Day0209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//N���� ���ڰ� �����ִ� ������, ��Ģ������ ī�带 ���ڿ� ���� �־� �پ��� ���� ���ϱ�
//�������� �켱���� ������� �ʰ� ���� -> ���������� ���
//�� ����� �ִ밡 �Ǵ� ���İ�, �ּҰ� �Ǵ� ������ ã�� �� ���� ���� ���


public class SW4008_MakingNumber_강기호{
	
	static int N;
	static int minV;
	static int maxV;
	static int[] signCnt;
	static int[] numbers;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		numbers = new int[12]; 
		signCnt = new int[4];
		for(int t=1 ; t<=T ; t++) {
			N = Integer.parseInt(br.readLine());
			
			
			// + , - , * , / ����
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i< 4 ; i++) {
				signCnt[i] = Integer.parseInt(st.nextToken());
			}
			
			// ���� ������Ʈ
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			// ���
			minV = Integer.MAX_VALUE;
			maxV = Integer.MIN_VALUE;
			dfs(0,numbers[0]);
			sb.append("#").append(t).append(" ").append(maxV - minV).append("\n");
		}
		System.out.print(sb);
			

	}
	
	public static void dfs(int time , int sum) {
		if(time == N-1) {
			if(sum < minV) minV = sum;
			if(sum > maxV) maxV = sum;
			return;
		}
		
		// ������ ���õǰ� ���
		for(int i = 0 ; i<4 ; i++) {
			if(signCnt[i] <= 0) continue;
			signCnt[i] -=1;
			if(i == 0) { // + ����
				dfs(time+1 , sum+numbers[time+1]);
			}else if(i==1) { // - ����
				dfs(time+1 , sum-numbers[time+1]);
			}else if(i==2) { // * ���� 
				dfs(time+1 , sum*numbers[time+1]);
			}else { // / ���� 
				dfs(time+1 , sum/numbers[time+1]);
			}
			signCnt[i] +=1;
		}
	}


}
