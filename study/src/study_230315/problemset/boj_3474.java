package study_230315.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_3474 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine()); // 정수
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			int sum = 0, five = 5;

			// 5의 제곱수로 나눈 몫을 더함
			while (true) {
				if (num / five == 0)
					break;
				sum += num / five;
				five *= 5;
			}

			sb.append(sum).append("\n"); // 합 출력
		}

		System.out.println(sb);
	}
}
