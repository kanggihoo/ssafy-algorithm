package Day0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

//�ܽ��� �츮 N�� (1~N��ȣ)
//�� �츮���� 0����~X���� ���� 
//
//M���� ����� ����µ�, l~r���� �츮 ���� �ܽ��� ���� s ����
//�� ����� �����ϴ� �ܽ��� �� ��ġ ���϶� 
//����� �������� �� ��� �ܽ��� ���� ���� ������, �׷��� ��ġ�� ���������� ���� �ռ� ���� ��� 
//
//����� �����ϴ� �ܽ��� ��ġ�� ������ -1 ���


public class JUN15649_NandM_강기호{
	
	static int N;
	static int M;
	static int[] cur;
	static boolean V[];
			
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		V = new boolean[N];
		cur = new int[M];
		
		
		dfs(0);
		
		System.out.print(sb);
	}
	
	public static void dfs(int idx) {
		if(idx == M) {
			for(int i = 0 ; i < M ; i++) {
				sb.append(cur[i]).append(" ");
			}
			sb.append("\n");
			
			return;
		}
		
		for(int i = 0; i <N ; i++) {
			if(!V[i]) {
				V[i] = true;
				cur[idx] = i+1;
				dfs(idx+1);
				V[i] = false;
			}
		}
		
	}
	
	
	
	

}
