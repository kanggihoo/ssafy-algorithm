#define MAX_N (100)
#define MAX_M (5000)

// 빙하는 매년 3 가지 과정을 순서대로 거치며 크기와 위치가 변한다.
// a. 융해 : 상하좌우 바다에 인접한 얼음덩어리의 높이가 1 씩 줄어든다.
// b. 이동 : 빙하가 상하좌우 중 한 방향으로 1 칸씩 이동한다.
// c. 병합 : 다른 빙하가 서로 충돌하거나, 상하좌우로 인접할 경우 하나의 빙하가 된다.

// a. 융해 
// - 바다와 인접한 얼음덩어리들의 높이가 1 씩 감소 ( 2 개 이상의 빙하로 나누어 질 수 있다.)
// - 빙하가 나누어지더라도 각 빙하의 이동 방향은 변하지 않는다.

// b. 이동 
// - 매년 1 칸씩 인접 좌표로 이동(초기에 이동하는 방향은 제공)
// - 모든 빙하는 동시에 이동

// c. 병합
// - 이동 후 서로 다른 빙하를 구성하고 있는 얼음덩어리가 같은 좌표에 겹치거나 상하좌우로 인접
//  - 겹칠 경우 두 얼음덩어리 중 높이가 높은 얼음덩어리만 남음 
//  - 병합 된 빙하는 이동 방향이 바뀌며, 이동하기 전의 상태를 비교하여 병합 후의 이동방향을 결정
// 	1. 부피가 큰 빙하 > 면적이 작은 빙하 > Y 좌표가 작은거 > X 좌표가 작은거 
//  - 빙하가 병합되는 과정에서 일부 얼음덩어리가 사라지더라도, 이동방향을 정할 때는 이동하기 전의 상태를 비교 
struct RESULT
{
	int heights[MAX_N][MAX_N];
};
// 그 빙하가 움직이는 방향 ( 0 : ↑, 1 : →, 2 : ↓, 3 : ←) 을 의미한다.

struct ICE
{
	int v;
	int area;
	int y;
	int x;
};

int dy[4] = {-1,0,1,0};
int dx[4] = {0,1,0,-1};

int (*curH)[MAX_N];
int (*curP)[MAX_N];
bool tmp[MAX_N][MAX_N];
void init(int N, int M, int mIceBlock[MAX_N][MAX_N], int mIceGroup[MAX_M][3])
{
	curH = mIceBlock;
	
	// int mIceGroup[MAX_M][3] 여기에서 제공된 (x,y)가 나중에 높이가 0이 될수도 있어서 미리 모든 위치에 대한 이동방향 결정해주어야함.
	for(int i = 0 ; i < MAX_N ; i++){
		for(int j = 0 ; j< MAX_N ; j++){
			tmp[i][j] = false;
		}
	}


}

RESULT res;
RESULT oneYearLater()
{
	// 1. 융해
	for(int i = 0 ; i < MAX_N ; i++){
		for(int j = 0 ; j< MAX_N ; j++){
			for(int d = 0 ; d <4 ; d++){
				if(curH[i][j] <=0) continue;
				int ny = i+dy[d]; int nx = j+dx[d];
				if(ny < 0 || ny >= MAX_N || nx < 0 || nx >=MAX_N) continue;

				if(curH[ny][nx] ==0){
					tmp[ny][nx] = true;
					break;
				}
			}
		}
	}

	// 일괄로 높이 감소 
	for(int i = 0 ; i< MAX_N ; i++){
		for(int j = 0 ; j< MAX_N ; j++){
			if(tmp[i][j]) curH[i][j]--;
		}
	}

	// 2. 이동

	// 3. 병합 
	return res;
}
