//package P13084_AI로봇;
//import java.util.*;
//
//class Robot {
//    int idx;
//    int baseScore;
//    int time;
//    int status; // 0: 작업대기, 1~50000: 작업 중, -1: 고장
//    int wid;
//    int v;
//
//    public Robot(int idx, int score, int time, int v) {
//        this.idx = idx;
//        this.baseScore = score;
//        this.time = time;
//        this.status = 0;
//        this.v = v;
//    }
//}
//
//class UserSolution {
//    Robot[][] tasks;
//    Robot[] robots;
//    int[] robotVersion;
//    int N;
//    Queue<Robot> minQ;
//    Queue<Robot> maxQ;
//
//    public void init(int N) {
//        this.N = N;
//        robots = new Robot[N + 1];
//        robotVersion = new int[N + 1];
//        tasks = new Robot[50001][];
//
//        // 지능지수 계산: baseScore + (cTime - time) => baseScore - time (상수항 제외)
//        minQ = new PriorityQueue<>((o1, o2) -> {
//            long c1 = (long) o1.baseScore - o1.time;
//            long c2 = (long) o2.baseScore - o2.time;
//            if (c1 == c2) return Integer.compare(o1.idx, o2.idx);
//            return Long.compare(c1, c2);
//        });
//
//        maxQ = new PriorityQueue<>((o1, o2) -> {
//            long c1 = (long) o1.baseScore - o1.time;
//            long c2 = (long) o2.baseScore - o2.time;
//            if (c1 == c2) return Integer.compare(o1.idx, o2.idx);
//            return Long.compare(c2, c1);
//        });
//
//        for (int i = 1; i <= N; i++) {
//            robotVersion[i] = 1;
//            robots[i] = new Robot(i, 0, 0, robotVersion[i]);
//            minQ.add(robots[i]);
//            maxQ.add(robots[i]);
//        }
//    }
//
//    public int callJob(int cTime, int wID, int mNum, int mOpt) {
//        tasks[wID] = new Robot[mNum];
//        int count = 0;
//        int res = 0;
//
//        while (count < mNum) {
//            Robot r = (mOpt == 0) ? maxQ.poll() : minQ.poll();
//            if (r == null) break;
//
//            // 유효성 검사: 버전 체크 및 대기 상태 확인
//            if (robotVersion[r.idx] != r.v || r.status != 0) continue;
//
//            // 상태 업데이트
//            r.status = wID;
//            r.baseScore += (cTime - r.time);
//            r.time = cTime;
//            r.wid = wID;
//            r.v = ++robotVersion[r.idx]; // 버전 갱신으로 큐 내 이전 데이터 무효화
//            
//            tasks[wID][count++] = r;
//            res += r.idx;
//        }
//        return res;
//    }
//
//    public void returnJob(int cTime, int wID) {
//        if (tasks[wID] == null) return;
//        for (Robot r : tasks[wID]) {
//            if (r == null || r.status == -1 || r.wid != wID) continue;
//
//            r.status = 0;
//            r.time = cTime;
//            r.wid = -1;
//            r.v = ++robotVersion[r.idx];
//            
//            maxQ.add(r);
//            minQ.add(r);
//        }
//        tasks[wID] = null; // 메모리 정리
//    }
//
//    public void broken(int cTime, int rID) {
//        Robot r = robots[rID];
//        if (r.status >= 1) { // 작업 중인 경우만 처리
//            r.status = -1;
//            robotVersion[r.idx]++; // 현재 작업 리스트 및 큐에서 무효화
//        }
//    }
//
//    public void repair(int cTime, int rID) {
//        Robot r = robots[rID];
//        if (r.status == -1) {
//            r.status = 0;
//            r.baseScore = 0;
//            r.time = cTime;
//            r.v = ++robotVersion[r.idx];
//            
//            maxQ.add(r);
//            minQ.add(r);
//        }
//    }
//
//    public int check(int cTime, int rID) {
//        Robot r = robots[rID];
//        if (r.status == -1) return 0;
//        if (r.status >= 1) return r.wid * -1;
//        return (cTime - r.time) + r.baseScore;
//    }
//}