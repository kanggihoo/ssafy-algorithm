package Day0213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//10*10 ������ ���͸� ��ġ(��ġ , ���� ���� , ����)
//- (1,1) ���� ����
//�������� ���� ������ �־����� ����ư �Ÿ��������� ���� ��������. (�ڱ� ��ġ �����ؼ� �������)
//
//����� A,B �̵������� �־����� (�ʴ�) , ���ʸ��� ���� ���� ������ BC�� ���Ӱ���. �̶� ������ BC�� ���� ��ŭ ���� ����. 
//BC�� �θ��� ����ڰ� �����ϸ� ����ڼ���ŭ ���� ���� �й� 
//�̵����� �־����� ��� ����ڰ� ������ ���� �ִ� 

//����� �ʱ� ��ġ��(0,0) , (9,9)
// BC������  1~ 8�� 
// ������ ¦�� 
// �ʱ� ��ġ���� �������� 
// ���� ��ġ�� 2�� ��ġ�Ȱ�� ����, ����� A,B ���� ���� ��ġ�� ���� 
public class SW5644_WirelessRecharge_강기호 {
	

	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		


		int[]u1 = new int[101]; // ����
		int[]u2 = new int[101]; // ����
		int[][] bcInfo = new int[8][4]; 
		int[] dy = {0,-1,0,1,0};
		int[] dx = {0,0,1,0,-1};

		int T = Integer.parseInt(br.readLine());
		for(int t=1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			
			int totalT = Integer.parseInt(st.nextToken());
			int bcCnt =  Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i =0;i<totalT ;i++) {
				u1[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i =0;i<totalT ;i++) {
				u2[i] = Integer.parseInt(st.nextToken());
			}
			
			// BC ����  (y,x,����,�Ŀ�)
			for(int i=0 ; i < bcCnt ; i++) {
				st = new StringTokenizer(br.readLine());
				int bx = Integer.parseInt(st.nextToken())-1;
				int by = Integer.parseInt(st.nextToken())-1;
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bcInfo[i][0] = by;
				bcInfo[i][1] = bx;
				bcInfo[i][2] = c;
				bcInfo[i][3] = p;
			}
			
			// bc ������ �������� map �ʱ�ȭ
			int[][] bcState = new int[10][10];
			int[][] map = new int[10][10];
			for(int i = 0 ; i< bcCnt ; i++) {
				int[] bc = bcInfo[i];
				int y = bc[0];
				int x = bc[1];
				int range = bc[2];
				int p = bc[3];
				for(int ddy = -range ; ddy<=range ; ddy++) {
					for(int ddx = -range ; ddx<=range ;ddx++) {
						int ny = y+ddy;
						int nx = x+ddx;
						if(Math.abs(ddy)+Math.abs(ddx) > range) continue;
						if(ny>=0 && ny<10 && nx>=0 && nx<10) {
							map[ny][nx] += p;
							bcState[ny][nx] = bcState[ny][nx] | (1<<i); 
						}
					}
				}
			}
			
			
			// ��� �̵�
			int u1y=0; int u1x = 0;
			int u2y=9; int u2x=9;
			int ans=0;	
			for(int step = 0 ; step < totalT+1 ; step++) {
				
				// ���� ��ġ���� ������ ���� ���
				int u1State = bcState[u1y][u1x];
				int u2State = bcState[u2y][u2x];
				int maxV=0;
				if(u1State == 0) {
					for(int i = 0 ; i < bcCnt ; i++) {
						if((u2State & 1<<i) !=0  && maxV < bcInfo[i][3]) maxV = bcInfo[i][3]; 
					}
				}else if(u2State ==0 ) {
					for(int i = 0 ; i < bcCnt ; i++) {
						if((u1State & 1<<i) !=0  && maxV < bcInfo[i][3]) maxV = bcInfo[i][3]; 
					}
				}else {
					for(int i=0 ; i<bcCnt ; i++) {
						for(int j = 0 ; j<bcCnt ; j++) {
							int tmp=0;
							if((u1State & 1<<i) !=0 && (u2State & 1<< j) !=0) {
								if(i==j) { // ������ bc �� ��� 
									tmp += bcInfo[i][3];
								}else tmp += bcInfo[i][3]+ bcInfo[j][3];
							}
							if(maxV < tmp) maxV = tmp;
						}
					}
					
				}
				
				ans += maxV;
				// ���� ��ġ ������Ʈ 
				if(step==totalT)break;
				int u1d = u1[step];
				int u2d = u2[step];

				u1y +=dy[u1d];
				u1x +=dx[u1d];
				
				u2y +=dy[u2d];
				u2x +=dx[u2d];
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
	

}
