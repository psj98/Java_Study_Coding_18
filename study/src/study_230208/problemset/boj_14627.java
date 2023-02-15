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

		int start = 1, end = 1000000000; // 파 최소 길이, 최대 길이
		long paSum = 0; // 파 길이 합

		int s = Integer.parseInt(stk.nextToken());
		int c = Integer.parseInt(stk.nextToken());
		int[] pa = new int[s]; // 파 길이 배열

		for (int i = 0; i < s; i++) {
			pa[i] = Integer.parseInt(br.readLine());
			paSum += pa[i]; // 각 파의 길이를 더함
		}

		// 이분 탐색
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

		// 타입 변환 및 출력
		sb.append(paSum - end * (long) c);
		System.out.println(sb);
	}

}
