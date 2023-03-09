package study_230308.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_2812_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();

		int n = Integer.parseInt(stk.nextToken()); // 문자열 길이
		int k = Integer.parseInt(stk.nextToken()); // 지워야하는 문자 개수

		String str = br.readLine(); // 문자열
		int[] nums = new int[n]; // 문자열을 숫자로 저장할 배열
		for (int i = 0; i < n; i++)
			nums[i] = str.charAt(i) - '0';

		for (int i = 0; i < n; i++) {
			if (stack.isEmpty()) { // 스택이 비어있으면 문자 삽입
				stack.add(nums[i]);
				continue;
			}

			// 현재 값이 스택 안에 있는 값보다 클 경우, 크거나 같은 값이 나올 때까지 다 뺌
			// k가 0이 되면 뺄 필요 없음
			while (k != 0 && !stack.isEmpty() && stack.peek() < nums[i]) {
				stack.pop();
				k--;
			}

			stack.add(nums[i]); // 현재 값 저장
		}

		// stack 길이 - k 까지 sb에 저장
		for (int i = 0; i < stack.size() - k; i++)
			sb.append(stack.get(i));

		System.out.println(sb);
	}
}
