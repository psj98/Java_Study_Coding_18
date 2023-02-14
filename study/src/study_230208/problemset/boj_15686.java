package study_230208.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_15686 {
	static ArrayList<int[]> house = new ArrayList<>();
	static ArrayList<int[]> chicken = new ArrayList<>();
	static int[] idx;
	static int n, m, ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());

		idx = new int[m];

		for (int i = 0; i < n; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int pos = Integer.parseInt(stk.nextToken());
				if (pos == 1) // 집 좌표
					house.add(new int[] { i, j });
				else if (pos == 2) // 치킨 집 좌표
					chicken.add(new int[] { i, j });
			}
		}

		find(0, 0);

		sb.append(ans);
		System.out.println(sb);
	}

	public static void find(int cnt, int chickenIdx) {
		if (cnt == m) {
			// 계산해서 value에 저장
			ans = Math.min(ans, calDistance(cnt));
			return;
		}

		for (int i = chickenIdx; i < chicken.size(); i++) {
			idx[cnt] = i;
			find(cnt + 1, i + 1);
		}
	}

	public static int calDistance(int cnt) {
		int sum = 0;

		for (int i = 0; i < house.size(); i++) {
			int distance = Integer.MAX_VALUE;
			for (int j = 0; j < cnt; j++) {
				int x = chicken.get(idx[j])[0];
				int y = chicken.get(idx[j])[1];
				int value = Math.abs(house.get(i)[0] - x) + Math.abs(house.get(i)[1] - y);
				distance = Math.min(value, distance);
			}

			sum += distance;
		}

		return sum;
	}
}
