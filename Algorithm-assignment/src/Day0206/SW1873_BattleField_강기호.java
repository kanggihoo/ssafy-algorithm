package Day0206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

//���� 1�� 
//����� �Է¿� ���� �� �̵�
//
//����	�ǹ�
//.	����(������ �� �� �ִ�.)
//*	������ ������� ��
//#	��ö�� ������� ��
//-	��(������ �� �� ����.)
//^	������ �ٶ󺸴� ����(�Ʒ��� �����̴�.)
//v	�Ʒ����� �ٶ󺸴� ����(�Ʒ��� �����̴�.)
//<	������ �ٶ󺸴� ����(�Ʒ��� �����̴�.)
//>	�������� �ٶ󺸴� ����(�Ʒ��� �����̴�.)
//
//
//����	����
//U	Up : ������ �ٶ󺸴� ������ �������� �ٲٰ�, �� ĭ ���� ĭ�� ������� �� �� ĭ���� �̵��Ѵ�.
//D	Down : ������ �ٶ󺸴� ������ �Ʒ������� �ٲٰ�, �� ĭ �Ʒ��� ĭ�� ������� �� ĭ���� �̵��Ѵ�.
//L	Left : ������ �ٶ󺸴� ������ �������� �ٲٰ�, �� ĭ ������ ĭ�� ������� �� ĭ���� �̵��Ѵ�.
//R	Right : ������ �ٶ󺸴� ������ ���������� �ٲٰ�, �� ĭ �������� ĭ�� ������� �� ĭ���� �̵��Ѵ�.
//S	Shoot : ������ ���� �ٶ󺸰� �ִ� �������� ��ź�� �߻��Ѵ�.
//
//�̵��� �� ���̸� �̵�X
//��ź �߻�� �� �Ǵ� ��ö ���� �浹�ϰų� ������ ����
//���� ���̸� ����, ��ö�̸� �ƹ��� ����
//
//��� �Է� ó���� ���� ���� ���±��϶�. 


//^	������ �ٶ󺸴� ����(�Ʒ��� �����̴�.)
//v	�Ʒ����� �ٶ󺸴� ����(�Ʒ��� �����̴�.)
//<	������ �ٶ󺸴� ����(�Ʒ��� �����̴�.)
//>	�������� �ٶ󺸴� ����(�Ʒ��� �����̴�.)
public class SW1873_BattleField_강기호{

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		// 0:�� , 1:�Ʒ� , 2:�� , 3: ��
		int[] dy = new int[] {0,1,0,-1};
		int[] dx = new int[] {1,0,-1,0};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		char[][] map = new char[20][20];
		Map<Character , Integer> table = new HashMap<>();
		Map<Integer, Character> Itable = new HashMap<>();
		char[] dSign = {'R','D','L','U'};
		char[] pSign = {'>' , 'v' , '<' ,'^'};
		for(int i = 0 ; i < 4 ; i++) {
			table.put(dSign[i], i);
			Itable.put(i,pSign[i]);
		}
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1 ; t<=T ; t++) {
			st = new StringTokenizer(br.readLine());
			int N =Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int cy=0; int cx=0; int cd=0;
			for(int i = 0 ; i<N ; i++) {
				char[] line= br.readLine().toCharArray();
				for(int j = 0 ; j<M ; j++) {
					char c = line[j];
					map[i][j] = c;
					if(c=='>' || c=='v' || c=='<' || c=='^') {
						cy = i; cx = j;
						map[i][j] ='.';
						if(c=='>') cd = 0;
						else if(c=='v') cd = 1;
						else if(c=='<') cd = 2; 
						else if(c=='^') cd =3;
					}
				}
			}
			
			// ��ɾ�
			int cCnt = Integer.parseInt(br.readLine());
			char[] C = br.readLine().toCharArray();
			
			// �̵�
			
			for(char c : C) {
				if(c=='S') { //�߻�
					int ny=cy+dy[cd]; int nx = cx+dx[cd];
					while(ny>=0 && ny<N && nx>=0 && nx<M) {
						if(map[ny][nx] == '*') { // ���� �������
							map[ny][nx] ='.';
							break;
						}else if(map[ny][nx] == '#')break; // ��ö

						ny+=dy[cd]; nx +=dx[cd];
					}
					
				}else { // �̵�
					cd = table.get(c);
					int ny=cy+dy[cd]; int nx = cx+dx[cd];
					
					// ���డ�� Ȯ��
					if(ny>=0 && ny<N && nx>=0 && nx<M && map[ny][nx] =='.') {
						cy = ny;
						cx = nx;
					}	
				}
					
				
			}
			
			sb.append("#").append(t).append(" ");
			// ���� ��� 
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j<M ; j++) {
					if(i==cy && j ==cx) sb.append(Itable.get(cd));
					else sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);
		


	}

}
