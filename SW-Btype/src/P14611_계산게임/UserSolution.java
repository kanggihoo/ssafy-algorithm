package P14611_계산게임;

/*
 * 일반 카드 : 1~30 
 * 조커 카드 : -1 => 조커값은 1~30중 변경가능 
 * 초기 5장 카드 주어짐 
 * 임의의 카드 5장과 나열할 방향이 주어지면 기존의 나열된 카드들의 왼쪽, 오른쪽으로 이어붙힘 
 * 
 * 결과값 mNum과 순서 mNth가 주어지면 테이블의 카드중 조건에 맞는 카드 찾기 
 * 	1. 가장 왼쪽부터 탐색
 * 	2. 선택한 카드와 오른쪽 3개 카드(총 4장)의 합을 20으로 나눈 나머지가 mNum과 동일한지 확인 
 * 	3. 오른쪽으로 계속 이동하면서 2) 조건에 맞는 mNth 번째 찾으면 해당 카드와 인접 3장 총4장 카드를 반환 
		
 */

class UserSolution {
	
	static int J;
	
	
	// 처음 테이블에 나열할 카드 5장주어지고, 초기 조커값이 제공 
    void init(int mJoker, int mNumbers[]) {
    	J = mJoker;

    }
    
    // 테이블에 나열할 5장 카드 제공, mDir =0이면 왼쪽 , 1이면 가장 오른쪽
    // 호출횟수 : 10,000회 => 최대 양쪽 100,000
    void putCards(int mDir, int mNumbers[]) {
    	
    }
    /*
     * Parameters
   	mNum : 찾아야 하는 계산 결과값 (0 ≤ mNum ≤ 19)
   	mNth : 찾아야 하는 결과값이 나와야 하는 횟수 (1 ≤ mNth ≤ 1,000)
   	ret[] : 찾은 카드에 적힌 수 (조커 카드의 경우 -1)
   	
	return
   	조건에 맞는 카드를 찾았으면 1, 아니면 0
     */

    int findNumber(int mNum, int mNth, int ret[]) {
    	
        return -1;
    }
    
    // 조커 값 변경
    void changeJoker(int mValue) {
    	J = mValue;
    }
}