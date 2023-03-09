package study_230308.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj_2565 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n];

		ArrayList<int[]> pos = new ArrayList<>(); // 전깃줄 좌표
		for (int i = 0; i < n; i++) {
			stk = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(stk.nextToken());
			int y = Integer.parseInt(stk.nextToken());
			pos.add(new int[] { x, y }); // 전깃줄 좌표 저장
		}

		// x좌표 기준 정렬
		Collections.sort(pos, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[0] - o1[0];
			}
		});

		// 가능한 전깃줄 개수 탐색
		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (pos.get(i)[1] < pos.get(j)[1]) // 현재 y좌표보다 큰 경우 -> max 값 갱신
					dp[i] = Math.max(dp[i], dp[j] + 1);
			}
		}

		// 가능한 전깃줄 개수 중 최댓값 찾기
		for (int i = 1; i < n; i++)
			dp[0] = Math.max(dp[0], dp[i]);

		sb.append(n - dp[0]); // 제거해야 할 전깃줄 개수 출력
		System.out.println(sb);
	}

}

