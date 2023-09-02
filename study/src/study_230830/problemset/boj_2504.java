package study_230830.problemset;

import java.io.*;
import java.util.*;

public class boj_2504 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Stack<Character> stack = new Stack<>();
        char[] str = br.readLine().toCharArray(); // 문자열

        int num = 1, ans = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(') { // 곱하기 2
                num *= 2;
                stack.add('(');
            } else if (str[i] == '[') { // 곱하기 3
                num *= 3;
                stack.add('[');
            } else if (str[i] == ')') {
                // 스택이 비어 있거나 괄호가 맞지 않는 경우, 0 출력
                if (stack.isEmpty() || stack.peek() != '(') {
                    ans = 0;
                    break;
                }

                // 괄호가 맞는 경우, ans에 num 더함
                if (str[i - 1] == '(')
                    ans += num;
                num /= 2; // 괄호 제거
                stack.pop();
            } else if (str[i] == ']') {
                // 스택이 비어 있거나 괄호가 맞지 않는 경우, 0 출력
                if (stack.isEmpty() || stack.peek() != '[') {
                    ans = 0;
                    break;
                }

                // 괄호가 맞는 경우, ans에 num 더함
                if (str[i - 1] == '[')
                    ans += num;
                num /= 3; // 괄호 제거
                stack.pop();
            }
        }

        // 정답 출력
        sb.append(stack.isEmpty() ? ans : 0);
        System.out.println(sb);
    }
}
