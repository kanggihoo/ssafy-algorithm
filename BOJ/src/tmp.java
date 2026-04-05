import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
애벌레
N개의 먹이가 나열된 나뭇가지를 오른쪽으로 기어가기
처음 0에 위치, i번째 먹이는 오른쪽으로 i초 기어가면 도달 가능 (매초 1씩 오른쪽으로 무조건 진행)

i번째 먹이가 맛있으면 높은 만족도, 한번 먹기 시작하면 연속적으로 먹어야 하며,
누적된 만족도가 최소 K 이상이되거나, 먹을 먹이가 없으면 연속 먹기 중단
만족도가 K를 초과하면 초과한 만큼 탈피 에너지 축적 ,
 */

public class tmp {
	static int[] satisfy;
	static long[] accSum;
	static long[] D;
	static int N;
	static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		satisfy = new int[N + 1];
		accSum = new long[N + 1];
		D = new long[N + 1];
		for (int i = 1; i <= N; i++) D[i] = -1L;

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			satisfy[i] = Integer.parseInt(st.nextToken());
			accSum[i] = accSum[i - 1] + satisfy[i];
		}

		long ans = dfs(1);
		System.out.print(ans);
	}

	public static long dfs(int idx) {
		if (idx > N) return 0;
		if (D[idx] != -1) return D[idx];
		D[idx] = 0;

		long res1 = dfs(idx + 1);
		// 현재 위치에서 먹는경우
		int next = biarySearch(idx);
		long acc = 0;
		if (next <= N) {
			acc = accSum[next] - accSum[idx - 1] - K;
		}
		long res2 = dfs(next + 1) + acc;

		return D[idx] = Math.max(res1, res2);
	}

	public static int biarySearch(int s) {
		int l = s;
		int r = N;

		while (l <= r) {
			int mid = l + (r - l) / 2;
			long aSum = accSum[mid] - accSum[s - 1];
			if (aSum >= K) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return l;
	}
}
