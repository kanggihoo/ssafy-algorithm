package P13084_AI로봇;

import java.util.*;

class Robot {
    int idx;
    int baseScore;
    int time;
    int status; // 0: 작업대기, 1~50000: 투입된 작업 ID, -1: 고장

    public Robot(int idx) {
        this.idx = idx;
        this.baseScore = 0;
        this.time = 0;
        this.status = 0;
    }
}

// 큐 정렬을 위한 스냅샷 전용 클래스
class QueueNode {
    int idx;
    int baseScore;
    int time;
    int v;

    public QueueNode(int idx, int baseScore, int time, int v) {
        this.idx = idx;
        this.baseScore = baseScore;
        this.time = time;
        this.v = v;
    }
}

class UserSolution2 {

    int[][] tasks;
    Robot[] robots;
    int[] robotVersion;
    int N;
    PriorityQueue<QueueNode> minQ;
    PriorityQueue<QueueNode> maxQ;

    public void init(int N) {
        this.N = N;
        robots = new Robot[N + 1];
        robotVersion = new int[N + 1];
        tasks = new int[50001][];

        minQ = new PriorityQueue<>((o1, o2) -> {
            long c1 = (long) o1.baseScore - o1.time;
            long c2 = (long) o2.baseScore - o2.time;
            if (c1 == c2) return Integer.compare(o1.idx, o2.idx);
            return Long.compare(c1, c2);
        });

        maxQ = new PriorityQueue<>((o1, o2) -> {
            long c1 = (long) o1.baseScore - o1.time;
            long c2 = (long) o2.baseScore - o2.time;
            if (c1 == c2) return Integer.compare(o1.idx, o2.idx);
            return Long.compare(c2, c1);
        });

        for (int i = 1; i <= N; i++) {
            robots[i] = new Robot(i);
            robotVersion[i] = 1;
            minQ.add(new QueueNode(i, 0, 0, 1));
            maxQ.add(new QueueNode(i, 0, 0, 1));
        }
    }

    public int callJob(int cTime, int wID, int mNum, int mOpt) {
        tasks[wID] = new int[mNum];
        int count = 0;
        int res = 0;

        while (count < mNum) {
            QueueNode node = null;
            if (mOpt == 0) {
                node = maxQ.poll();
            } else {
                node = minQ.poll();
            }

            // 유효성 및 버전 검사
            if (node == null) continue;
            if (robotVersion[node.idx] != node.v) continue;

            Robot r = robots[node.idx];
            // 대기 중인 로봇만 처리
            if (r.status != 0) continue;

            // 로봇 상태 업데이트 (작업 투입)
            r.status = wID;
            r.baseScore += (cTime - r.time);
            r.time = cTime;

            robotVersion[r.idx]++;
            tasks[wID][count++] = r.idx;

            res += r.idx;
        }
        return res;
    }

    public void returnJob(int cTime, int wID) {
        if (tasks[wID] == null) return;

        for (int rID : tasks[wID]) {
            Robot r = robots[rID];
            // 현재 해당 작업에 투입 중인 로봇만 복귀 처리
            if (r.status == wID) {
                r.status = 0;
                r.time = cTime;
                int newV = ++robotVersion[r.idx];

                // 복귀 시 큐에 새로운 상태 스냅샷 추가
                maxQ.add(new QueueNode(r.idx, r.baseScore, r.time, newV));
                minQ.add(new QueueNode(r.idx, r.baseScore, r.time, newV));
            }
        }
    }

    public void broken(int cTime, int rID) {
        Robot r = robots[rID];
        // 작업 중인 로봇만 고장 처리
        if (r.status >= 1) {
            r.status = -1;
            robotVersion[r.idx]++;
        }
    }

    public void repair(int cTime, int rID) {
        Robot r = robots[rID];
        // 고장난 로봇만 수리 처리
        if (r.status == -1) {
            r.status = 0;
            r.baseScore = 0;
            r.time = cTime;
            int newV = ++robotVersion[r.idx];

            // 수리 완료 후 큐에 새로운 상태 스냅샷 추가
            maxQ.add(new QueueNode(r.idx, r.baseScore, r.time, newV));
            minQ.add(new QueueNode(r.idx, r.baseScore, r.time, newV));
        }
    }

    public int check(int cTime, int rID) {
        Robot r = robots[rID];
        if (r.status == -1) return 0; // 고장
        else if (r.status >= 1) return r.status * -1; // 작업 중
        return (cTime - r.time) + r.baseScore; // 대기 중 (지능 계산)
    }
}
