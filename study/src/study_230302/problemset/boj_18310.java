package study_230302.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_18310 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder sb = new StringBuilder();

        // 초기화 및 값 저장
		int n = Integer.parseInt(br.readLine());
		int[] num = new int[n];
		stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			num[i] = Integer.parseInt(stk.nextToken());

		Arrays.sort(num); // 오름차순 정렬

		sb.append(num[(n - 1) / 2]); // 중간값 출력
		System.out.println(sb);
	}
}
