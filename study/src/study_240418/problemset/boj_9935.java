package study_240418.problemset;

import java.io.*;
import java.util.*;

public class boj_9935 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine(); // 원래 문자열
        String bomb = br.readLine(); // 폭발할 문자열

        int strSize = str.length();
        int bombSize = bomb.length();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < strSize; i++) {
            stack.add(str.charAt(i)); // Stack에 저장

            // 폭발할 문자열 크기보다 작으면 불가능
            if (stack.size() < bombSize) {
                continue;
            }

            boolean check = false;

            for (int j = 0; j < bombSize; j++) {
                char stackChar = stack.get(stack.size() - bombSize + j); // Stack의 문자
                char bombChar = bomb.charAt(j); // 폭발할 문자열의 문자

                // 같은 경우, 스킵
                if (stackChar == bombChar) {
                    continue;
                }

                // 다른 경우, 종료
                check = true;
                break;
            }

            // 폭발하지 못하는 경우, 스킵
            if (check) {
                continue;
            }

            // 폭발 문자열 길이만큼 Stack에서 제거
            for (int j = 0; j < bombSize; j++) {
                stack.pop();
            }
        }

        // 정답 저장
        for (Character c : stack) {
            sb.append(c);
        }

        // 정답 출
        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }
}
