package study_230308.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_2812_1 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>();

		Integer.parseInt(stk.nextToken()); // 문자열 길이
		int k = Integer.parseInt(stk.nextToken()); // 지워야하는 문자 개수

		String str = br.readLine(); // 문자열
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i); // 현재 문자
			if (stack.isEmpty()) { // 스택이 비어있으면 문자 삽입
				stack.add(c);
				continue;
			}

			// 현재 값이 스택 안에 있는 값보다 클 경우, 크거나 같은 값이 나올 때까지 다 뺌
			// k가 0이 되면 뺄 필요 없음
			while (k != 0 && !stack.isEmpty() && stack.peek() < c) {
				stack.pop();
				k--;
			}

			stack.add(c); // 현재 값 저장
		}

		// k가 0이 아닌 경우 체크 -> 맨 앞부터 stack 길이 - k 까지 sb에 저장
		// sb.insert()는 array를 copy해와서 앞에 넣기 때문에 오래 걸림
		while (!stack.isEmpty()) {
			if (k != 0) {
				k--;
				stack.pop();
			} else
				sb.insert(0, stack.pop());
		}

		System.out.println(sb); // 출력
	}
}
