package study_230308.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_2533 {
	static int n;
	static ArrayList<Integer>[] node;
	static int[][] dp;
	static boolean[] visited;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());

		// 초기화
		visited = new boolean[n + 1];
		dp = new int[2][n + 1];
		node = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++)
			node[i] = new ArrayList<>();

		// 정점 연결 정보 저장
		for (int i = 0; i < n - 1; i++) {
			stk = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(stk.nextToken());
			int end = Integer.parseInt(stk.nextToken());
			node[start].add(end);
			node[end].add(start);
		}

		dfs(1); // dfs

		sb.append(Math.min(dp[0][1], dp[1][1]));
		System.out.println(sb);
	}

	// 말단 노드까지 dfs -> 얼리어답터 개수 세기
	static void dfs(int cur) {
		visited[cur] = true; // 현재 노드 방문

		dp[0][cur] = 0; // 얼리어답터가 아닌 경우 -> 0
		dp[1][cur] = 1; // 얼리어답터인 경우 -> 1

		// 다음에 갈 노드가 있을 경우
		for (int next : node[cur]) {
			if (!visited[next]) { // 방문하지 않은 경우, dfs(next)
				dfs(next);

				dp[0][cur] += dp[1][next]; // 얼리어답터가 아닌 경우 -> 다음은 무조건 얼리어답터
				dp[1][cur] += Math.min(dp[1][next], dp[0][next]); // 얼리어답터인 경우 -> 다음은 얼리어답터일 수도 있고 아닐수도 있음
			}
		}
	}
}