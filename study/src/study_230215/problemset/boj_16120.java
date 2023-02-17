package study_230215.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_16120 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>(); // 알파벳을 넣을 스택 (A or P)
		boolean check = false;

		String str = br.readLine();

		for (int i = 0; i < str.length(); i++) {
			// A가 나오고 다음 값이 P인 경우
			if (str.charAt(i) == 'A' && i + 1 < str.length() && str.charAt(i + 1) == 'P') {
				int size = stack.size();
				
				// 앞의 두 값이 PP 인 경우
				if (size - 2 >= 0 && stack.elementAt(size - 2) == 'P' && stack.elementAt(size - 1) == 'P') {
					stack.pop(); // 기존에 PP가 있으므로 P 한 개만 pop
					i++; // A 다음의 P는 볼 필요 없으므로 스킵
				} else {
					stack.add(str.charAt(i));
				}
			} else {
				stack.add(str.charAt(i));
			}
		}

		// P인 경우 PPAP 가능
		if (stack.size() == 1 && stack.peek() == 'P')
			check = true;

		sb.append(check ? "PPAP" : "NP");
		System.out.println(sb);
	}

}

