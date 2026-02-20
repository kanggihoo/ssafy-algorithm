package Day0206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

//전차 1개 
//사용자 입력에 따라 맵 이동
//
//문자	의미
//.	평지(전차가 들어갈 수 있다.)
//*	벽돌로 만들어진 벽
//#	강철로 만들어진 벽
//-	물(전차는 들어갈 수 없다.)
//^	위쪽을 바라보는 전차(아래는 평지이다.)
//v	아래쪽을 바라보는 전차(아래는 평지이다.)
//<	왼쪽을 바라보는 전차(아래는 평지이다.)
//>	오른쪽을 바라보는 전차(아래는 평지이다.)
//
//
//문자	동작
//U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
//D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
//L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
//R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
//S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
//
//이동시 맵 밖이면 이동X
//포탄 발사시 벽 또는 강철 벽에 충돌하거나 끝까지 진행
//벽돌 벽이면 평지, 강철이면 아무일 없음
//
//모든 입력 처리후 게임 맵의 상태구하라. 


//^	위쪽을 바라보는 전차(아래는 평지이다.)
//v	아래쪽을 바라보는 전차(아래는 평지이다.)
//<	왼쪽을 바라보는 전차(아래는 평지이다.)
//>	오른쪽을 바라보는 전차(아래는 평지이다.)
public class SW1873_BattleField_강기호 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		// 0:오 , 1:아래 , 2:왼 , 3: 위
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
			
			// 명령어
			int cCnt = Integer.parseInt(br.readLine());
			char[] C = br.readLine().toCharArray();
			
			// 이동
			
			for(char c : C) {
				if(c=='S') { //발사
					int ny=cy+dy[cd]; int nx = cx+dx[cd];
					while(ny>=0 && ny<N && nx>=0 && nx<M) {
						if(map[ny][nx] == '*') { // 벽돌 만난경우
							map[ny][nx] ='.';
							break;
						}else if(map[ny][nx] == '#')break; // 강철

						ny+=dy[cd]; nx +=dx[cd];
					}
					
				}else { // 이동
					cd = table.get(c);
					int ny=cy+dy[cd]; int nx = cx+dx[cd];
					
					// 진행가능 확인
					if(ny>=0 && ny<N && nx>=0 && nx<M && map[ny][nx] =='.') {
						cy = ny;
						cx = nx;
					}	
				}
					
				
			}
			
			sb.append("#").append(t).append(" ");
			// 최종 출력 
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
