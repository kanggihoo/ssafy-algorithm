package Day0204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

//4�� �ڼ�, 8���� ��, �� ������ N,S ����
//������ȭ��ǥ�� �� �ϳ��� ������ ��ġ 
//���� �ڼ� ȸ���� ���� �ڼ��� �پ� �ִ� ��� �ڼ��� �ٸ� ��� �ݴ� �������� 1ĭ ȸ��
//
//�������� �ڼ������� ��� ȸ������ �� �Ʒ��� ���� ������� ���� ���
//- 1 �� �ڼ����� ������ ȭ��ǥ ��ġ�� �ִ� ���� �ڼ��� N ���̸� 0 ��, S ���̸� 1 ���� ȹ���Ѵ�.
//
//- 2 �� �ڼ����� ������ ȭ��ǥ ��ġ�� �ִ� ���� �ڼ��� N ���̸� 0 ��, S ���̸� 2 ���� ȹ���Ѵ�.
//
//- 3 �� �ڼ����� ������ ȭ��ǥ ��ġ�� �ִ� ���� �ڼ��� N ���̸� 0 ��, S ���̸� 4 ���� ȹ���Ѵ�.
//
//- 4 �� �ڼ����� ������ ȭ��ǥ ��ġ�� �ִ� ���� �ڼ��� N ���̸� 0 ��, S ���̸� 8 ���� ȹ���Ѵ�.

//N���� 0 , S���� 1
//�ڼ������� ���������� �ð��������� 
//1�� �ð���� , -1 �ݽð� ����

public class SW4130_UniqueMagnet_강기호{
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <=T ; t++) {
			Deque<Integer>[] M = new ArrayDeque[4];
			for(int  i = 0 ; i < 4 ; i++) M[i] = new ArrayDeque<>();
			
			int K = Integer.parseInt(br.readLine());
			
			// 4�� �ڼ� �Է�
			for(int i = 0 ; i < 4 ; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0 ; j <8 ; j++) {
					M[i].offer(Integer.parseInt(st.nextToken()));
				}
			}
			
			// K�� ȸ�� 
			for(int r = 0 ; r<K ; r++) {
				st = new StringTokenizer(br.readLine());
				int mIdx = Integer.parseInt(st.nextToken())-1;
				int d = Integer.parseInt(st.nextToken());
				
				// mIdx �ڼ� d�� ȸ��(1:�ð����, -1: �ݽð�)
				List<int[]> tmp = new ArrayList<>();
				tmp.add(new int[] {mIdx, d});
				

				int[] originLR = getLRSign(M[mIdx]);
				int[] origin = new int[] {originLR[0] , originLR[1] , d};
				// ���ʹ������� �� �� �ִ� �ڼ�
				int[] comp = origin.clone();
				for(int i =mIdx-1 ; i>=0 ; i--) {
					int[] cur = getLRSign(M[i]);
					if(cur[1] == comp[0]) break;
					// ���� �ٸ����
					tmp.add(new int[] {i,comp[2]*-1});
					comp[0] = cur[0];
					comp[1] = cur[1];
					comp[2] = comp[2]*-1;
				}
				
				// ������ �������� �� �� �ִ� �ڼ�
				comp = origin.clone();
				for(int i = mIdx+1 ; i<4 ; i++ ) {
					int[] cur = getLRSign(M[i]);
					if(cur[0] == comp[1]) break;
					// ���� �ٸ����
					tmp.add(new int[] {i,comp[2]*-1});
					comp[0] = cur[0];
					comp[1] = cur[1];
					comp[2] = comp[2]*-1;
				}
				
				// ���� �� �ִ� �͵� ������ 
				for(int[] info : tmp) {
					rotate(M[info[0]], info[1]);
					
				}
			}
			// ���� ���� ���
			int score = 0;
			for(int i = 0 ; i < 4 ; i++) {
				if(M[i].peekFirst() == 1) score += Math.pow(2, i);

			}
			sb.append("#").append(t).append(" ").append(score).append("\n");
		}
		
		System.out.print(sb);
		

	}
	
	public static int[] getLRSign(Deque<Integer> dq) {
		int cnt = 0;
		int l=0;
		int r=0;
		for(int i : dq) {
			
			if(cnt == 2) {
				r = i;
			}
			else if(cnt == 6) {
				l = i;
				break;
			}
			cnt++;
			
		}
		
		return new int[] {l,r};
		
	}
	
	public static void rotate(Deque<Integer> dq , int d) {
		if(d == 1) dq.offerFirst(dq.pollLast()); // �ð���� 
		else dq.offer(dq.poll());
	}

}
