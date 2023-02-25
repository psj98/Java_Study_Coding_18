package study_230222.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1520 {
	static int n, m;
	static int[][] map, dp;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		map = new int[n][m]; // 맵
		dp = new int[n][m]; // dp맵

		for (int i = 0; i < n; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				dp[i][j] = -1; // dp -1로 초기화 (방문 체크)
			}
		}

		sb.append(dfs(0, 0));
		System.out.println(sb);
	}

	// DP + DFS 탐색
	static int dfs(int x, int y) {
		// 도착 지점에 갔을 경우
		if (x == n - 1 && y == m - 1) {
			return 1;
		}

		// 방문했을 경우, dp 값을 리턴
		if (dp[x][y] != -1) {
			return dp[x][y];
		}

		dp[x][y] = 0; // 방문한 경우, 0으로

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			// 범위 체크 + 값이 작은 곳으로
			if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[x][y] > map[nx][ny]) {
				dp[x][y] += dfs(nx, ny);
			}
		}

		return dp[x][y];
	}
}
