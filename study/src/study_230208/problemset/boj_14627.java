package study_230208.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14627 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int start = 1, end = 1000000000;
		long paSum = 0;

		int s = Integer.parseInt(stk.nextToken());
		int c = Integer.parseInt(stk.nextToken());
		int[] pa = new int[c];

		for (int i = 0; i < s; i++) {
			pa[i] = Integer.parseInt(br.readLine());
			paSum += pa[i];
		}

		while (start <= end) {
			int mid = (start + end) / 2;
			int cnt = 0;

			for (int i = 0; i < s; i++) {
				cnt += pa[i] / mid; // 각 파 별로 개수 세기
			}

			if (cnt >= c) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		sb.append(paSum - end * (long) c);
		System.out.println(sb);
	}

}
