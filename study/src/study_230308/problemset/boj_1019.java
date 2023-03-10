package study_230308.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_1019 {
	static int[] ten = new int[10]; // 0 ~ 9 배열
	static int digit = 1; // 자릿수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int start = 1; // 시작 숫자
		int end = Integer.parseInt(br.readLine()); // 입력 숫자

		// start 0 ~ end 9 이면, (end/10 - start/10 + 1) * 자릿수만큼 배열에 더하면 됨
		// 19일 때, 처음엔 1 2 3 4 5 6 7 8 9
		// 다음 10 ~ 19 -> digit = 10 -> 1이 10개 나옴
		while (start <= end) {
			// 일의 자리를 9로 만듦
			while (end % 10 != 9 && end >= 0) {
				count(end--);
			}

			// 값이 역전되면 종료
			if (start > end)
				break;

			// 일의 자리를 0으로 만듦
			while (start % 10 != 0) {
				count(start++);
			}

			// 10으로 나눈 몫
			start /= 10;
			end /= 10;

			// 각 자릿수의 값의 개수 더함
			for (int i = 0; i < 10; i++)
				ten[i] += (end - start + 1) * digit;

			digit *= 10;
		}

		for (int i = 0; i < 10; i++)
			sb.append(ten[i]).append(" ");
		System.out.println(sb);
	}

	// num의 각 자릿수의 값이 몇 개인지 세기
	static void count(int num) {
		while (num != 0) {
			ten[num % 10] += digit;
			num /= 10;
		}
	}
}
