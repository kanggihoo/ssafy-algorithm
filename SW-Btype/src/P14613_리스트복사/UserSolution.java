package P14613_리스트복사;
import java.util.*;
class UserSolution
{
	// 메모리 제한은 256MB

	public Map<String, ListState>P;
	public int[][] original;
	public int originalCnt;

	class Node{
		int idx;
		int v;
		Node pre;

		public Node(int idx , int v , Node n){
			this.idx = idx;
			this.v = v;
			this.pre = n;
		}
	}

	class ListState {
		int originalIdx;
		Node lastUpdated;

		public ListState( int idx , Node n){
			this.originalIdx = idx;
			this.lastUpdated = n;
		}
	}

	public void init()
	{
		P = new HashMap<>();
		original = new int[10][];
		originalCnt =0;
	}
	
	
	// mName 리스트가 생성되지 않음을 보장 
	// mName 리스트를 새로 생성, 원소 개수 mLength (1~200,000) , 0 ≤ mListValue[i] ≤ 32,767(2^16개) 
	// 호출 횟수 10 
	public void makeList(char mName[], int mLength, int mListValue[])
	{
		// 여기는 진짜로 배열을 만들어야 하는거고
		String name = getName(mName);
		ListState curState = new ListState(originalCnt , null);
		P.put(name , curState);
		int[] tmp = new int[mLength];
		for(int i = 0 ; i < mLength ; i++){
			tmp[i] = mListValue[i];
		}
		original[originalCnt++] = tmp;

	}
	
	// mDest 리스트를 새로 생성 , mSrc 리스트를 mDest에 복사 , ture이면 값을 모두 복사 , false 인 경우 주소만 복사
	// 여기서 mkaeList에서 만든 원본 배열이랑 연결정보를 기록해야함. 
	// 호출횟수 : 5000 
	public void copyList(char mDest[], char mSrc[], boolean mCopy)
	{

		ListState curState = P.get(getName(mSrc));
		String copiedName = getName(mDest);
		Node curNode = curState.lastUpdated;
		if(mCopy) { // 모두 복사
			ListState newState = new ListState(curState.originalIdx , curNode);
			P.put(copiedName , newState);
		}else{
			P.put(copiedName , curState);
		}

	}
	
	// mIdx 번째 원소 값을 변경 (0부터 인덱스 시작) 
	// 호출 횟수 : 100,000
	public void updateElement(char mName[], int mIndex, int mValue)
	{
		ListState curState = P.get(getName(mName));
		Node newNode = new Node(mIndex , mValue , curState.lastUpdated);
		curState.lastUpdated = newNode;
	}

	// mIndex 번째 원소 반환
	// 호출 횟수 : 400 
	// 여기서 지연적으로 값을 계산하고, 
	// 복사, 업데이트 연산 기록을 불러와서 해당 함수 호출 시점에 연산 후 결과 반환 (호출될때마다 연산 기록 처음 부터 인덱스만 바라봐도 될거 같은데 )
	//  => 업데이트 연산인데 mIndex랑 다르면 pass 업데이트 할 필요없고, 
	public int element(char mName[], int mIndex)
	{
		ListState curState = P.get(getName(mName));
		int oIdx = curState.originalIdx;
		Node curNode = curState.lastUpdated;
		while(curNode!=null){
			if(curNode.idx == mIndex ) return curNode.v;
			curNode = curNode.pre;
		}
		return original[oIdx][mIndex];
	}

	public String getName(char[] names){
		int idx = 0;
		while(names[idx]!='\0'){
			idx++;
		}
		return new String(names , 0 , idx);
	}
}