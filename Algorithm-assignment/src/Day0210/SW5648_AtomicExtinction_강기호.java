package Day0210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

//���� ������ �ùķ��̼� 
// 
//�� : y�� ����(�Ʒ�����) => 0
//�� : y�� ����(����) => 1
//�� : x ���� => 2
//�� : x ����  => 3
//��� ���� �̵��ӵ� ���� (�ʴ�1ĭ)
//��� ���ڴ� ���ÿ� �̵�
//2�� �̻� ���� �浹�� ���� ������ ������ ��� ������ �Ҹ�
//
//���ڵ��� ó�� ��ġ [x, y] �� -1,000 �̻� 1,000 ������ ������ �־�����. (-1,000��x,y��1,000)
//4. ���ڵ��� 2���� ��� ������ �����̸� ���ڵ��� ������ �� �ִ� ��ǥ�� ������ ������ ����. => ���������� �ڿ��Ҹ��ϴ°ɷ� 
//
//
//���ڵ��� �Ҹ�Ǹ鼭 �����ϴ� �������� ���ձ��϶�
//-> �̰� ���� ������ �浹���� ������ �Ǵ����� => �� 2000�� �ݺ��ϸ� �Ǳ���. �˾Ƽ� ������ ��������


public class SW5648_AtomicExtinction_강기호 {
	static class A{
		int y;
		int x;
		int d;
		int k;
		boolean isL;
		
		public A(int x,int y,int d,int k) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.k = k;
		}
	}

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[] dy = {1,-1,0,0 };
		int[] dx = {0,0,-1,1};
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1 ; t<=T ; t++) {
			int[][] map = new int[2001][2001];
			int N = Integer.parseInt(br.readLine());
			A[] atoms = new A[N+1];
			for(int i = 1 ; i <= N ; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken())+1000;
				int y = Integer.parseInt(st.nextToken())+1000;
				int d = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				atoms[i] = new A(x,y,d,k);
				map[y][x] = i;
			}
			
			int ans =0;
			while(true) {
				
				// �̵�
				for(int i = 1 ; i <= N ; i++) {
					A a = atoms[i];
					if(a.isL) continue;
					int ny = a.y+dy[a.d];
					int nx = a.x+dx[a.d];
					if(ny>=0 && ny<=2000 && nx>=0 && nx<=2000) {
						if(map[ny][nx]==0) {
							map[ny][nx] = i;
							map[a.y][a.x] = 0;
							a.y = ny;
							a.x = nx;
						}else {
							A nextA = atoms[map[ny][nx]];
							// ���� �� ������ ��� 
							if(map[ny][nx] > i) {
								// ���� ���� ��ġ�� ���� ���ڶ� �����Ѱ��
								if(a.y == nextA.y+dy[nextA.d] && a.x == nextA.x+dx[nextA.d]) {
									nextA.isL=true;
									a.isL = true;
									map[a.y][a.x] = 0;
									map[nextA.y][nextA.x] = 0;
									ans += (a.k+nextA.k);
								}else {
									map[ny][nx] = i;
									map[a.y][a.x] = 0;
									a.y = ny;
									a.x = nx;
								}
							}else { // �̹� ����������� �Ѵ� �ı�
								a.isL = true;
						        ans += a.k;
						        map[a.y][a.x] = 0;
						        
						        // �̹� ���� ���� �ƴ� ���� �������� ���� (�ߺ� ����)
						        if (!nextA.isL) {
						            nextA.isL = true;
						            ans += nextA.k;
						        }
						        a.y = ny; a.x = nx;
							}
						}
					}else { // ���� �����ó��
						a.isL = true;
						map[a.y][a.x] = 0;
					}
				}
				
				// ��� ���Ұ� ������� Ȯ��
				if(check(atoms,N))break;
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
	
	public static boolean check(A[] atoms, int N) {
		for(int i = 1; i<= N ; i++) {
			if(!atoms[i].isL) return false;
		}
		return true;
	}

}
