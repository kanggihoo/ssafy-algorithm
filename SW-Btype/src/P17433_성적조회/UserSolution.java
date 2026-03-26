package P17433_성적조회;
/*
 * 성적 추가는 ID , 학년 , 성별 , 점수 
 * 삭제할때는 ID로 
 * 조회시에는 학년(1,2,3) , 성별(남,여) , 특정점수 이상 학생 중에서 점수가 가장 낮은 학생 ID 반환
 */
import java.util.*;

class Score implements Comparable<Score>{
	int idx;
	int score;
	
	Score(int i , int s){
		this.idx = i;
		this.score = s;
				
	}

	@Override
	public int compareTo(Score o) {
		// TODO Auto-generated method stub
		if(this.score != o.score) return Integer.compare(this.score, o.score);
		return Integer.compare(this.idx, o.idx);
	}
}

class Info {
	int grade;
	int gender;
	int idx;
	int score;
	
	Info(int idx, int grade , int gender, int score){
		this.idx = idx;
		this.grade = grade;
		this.gender = gender;
		this.score = score;
		
		
	}
}

class UserSolution {
	
	TreeSet<Score>[][] Table; // [학년][성별]
	int count;
	Map<Integer , Integer> idToInt = new HashMap<>();
	Map<Integer , Integer> intToId = new HashMap<>();
	Info[] students;
	
	
	// 등록된 성적이 없는 상태 
	public void init() {
		Table = new TreeSet[4][2];
		for(int i = 0 ; i<4 ; i++) {
			for(int j = 0 ; j <2 ; j++) Table[i][j] = new TreeSet<>();
		}
		count = 0;
		students = new Info[20001];
		return;
	}
	// 학생 ID , 학년 , 성별 , score 제공 
	// mGrade , mGender 학생 중 점수가 가장 높은 학생 ID 계산 => 이 중에서 ID가 가장 큰 값반환  
	// 점수가 이미 기록된 학생이 입력으로 주어지지 않고, 기록이 삭제되고 다시 추가될 수는 있음. 
	//  mId: 학생의 ID ( 1 ≤ mId ≤ 1,000,000,000 )
	public int add(int mId, int mGrade, char mGender[], int mScore) {
		idToInt.put(mId, count);
		intToId.put(count, mId);
		int id = idToInt.get(mId);
		int gender = 0; // 남성
		if(mGender[0] =='f') gender = 1; // 여성
		
		students[count++] = new Info(id , mGrade , gender , mScore);
		Score s = new Score(id, mScore);
		Table[mGrade][gender].add(s);
		
		// 학년, 성별 그룹 중 점수 가장 높은값 반환 
		Score max = Table[mGrade][gender].last();
		return intToId.get(max.idx);
	}
	// 학생 ID의 기록 삭제 => 없는 ID이면 0반환 
	// 삭제 후 ID 학생의 학년, 성별이 동일한 학생 중 점수 가장 낮은 학생 ID 반환 => 여러명인경우 가장 작은 값을 반환 
	// 삭제 후 학년과 성별이 동일한 학생이 없으면 0 반환 
	public int remove(int mId) {
		int id=-1;
		if(!idToInt.containsKey(mId)) return 0;
		id = idToInt.get(mId);
		
		Info info = students[id];
		Table[info.grade][info.gender].remove(new Score(id,info.score));
		if(Table[info.grade][info.gender].size()==0) return 0;
		
		return intToId.get(Table[info.grade][info.gender].first().idx);
	}
	
	// 학년 집합 , 성별 집합에 속하면서 점수가 mScore 이상인 학생 중 가장 점수 낮은 학생 ID 반환 => 이때 여러명이명 ID가장 작은 값반환 
	// 무조건 학년, 성별 집합 크기는 1이상 
	// 점수가 mScore 이상 학생 없으면 0반환 
	public int query(int mGradeCnt, int mGrade[], int mGenderCnt, char mGender[][], int mScore) {
		int res =Integer.MAX_VALUE;
		for(int i = 0 ; i < mGradeCnt ; i++) {
			for(int j = 0 ; j <  mGenderCnt ; j++) {
				int gender = 0;
				int grade = mGrade[i];
				if(mGender[j][0] =='f') gender = 1;
				Score s = Table[grade][gender].floor(new Score(-1,mScore));
				if(s==null) continue;
				int realId = intToId.get(s.idx);
				res = Math.min(res, realId);
			}
		}
		
		return res == Integer.MAX_VALUE ? 0 : res;
	}
}