package study_230823.problemset;

import java.io.*;
import java.util.*;

public class boj_1918 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        /**
         * 피연산자 바로 출력
         * 연산자 Stack에 넣기
         * - 괄호가 나오면 계속 저장
         * - 닫는 괄호가 나오면 여는 괄호가 있는데까지 출력 => 괄호 제거
         * - 연산자 우선 순위 체크
         * - 괄호는 출력 X
         * - [+, -] < [*, /]
         */

        String str = br.readLine();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char cur = str.charAt(i);

            // 알파벳은 바로 출력
            if (cur >= 'A' && cur <= 'Z') {
                sb.append(cur);
                continue;
            }

            if (cur == '(') { // 열린 괄호
                stack.add(cur);
            } else if (cur == ')') { // 닫힌 괄호
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }

                stack.pop(); // 괄호 제거
            } else { // 연산자 (+, -, *, /)
                while (!stack.isEmpty() && getRank(stack.peek()) >= getRank(cur)) {
                    sb.append(stack.pop());
                }

                stack.add(cur);
            }
        }

        // 나머지 출력
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        // 정답 출력
        System.out.println(sb);
    }

    // 연산자 우선순위 체크
    static int getRank(char cur) {
        if (cur == '+' || cur == '-') {
            return 0;
        } else if (cur == '*' || cur == '/') {
            return 1;
        } else {
            return -1;
        }
    }
}
