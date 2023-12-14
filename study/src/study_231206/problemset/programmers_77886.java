package study_231206.problemset;

import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        // 가장 작은 값 찾기
        for (int i = 0; i < s.length; i++) {
            answer[i] = find(s[i]);
        }

        return answer;
    }

    public String find(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int cnt = 0; // 110 개수

        // stack에 넣으면서 110 개수 세기
        for (char c : s.toCharArray()) {
            if (stack.size() <= 1) {
                stack.push(c);
                continue;
            }

            char b = stack.pop();
            char a = stack.pop();

            if (a == '1' && b == '1' && c == '0') {
                cnt++;
                continue;
            }

            stack.push(a);
            stack.push(b);
            stack.push(c);
        }

        int zeroIdx = stack.size(); // 0 위치
        boolean check = false;

        // 가장 뒤에 있는 0 위치 찾기
        while (!stack.isEmpty()) {
            if (!check) {
                if (stack.peek() == '1') {
                    zeroIdx--;
                } else {
                    check = true;
                }
            }

            sb.insert(0, stack.pop()); // StringBuilder에 값 저장
        }

        // 110이 없는 경우, 원래 값 반환
        if (cnt == 0) {
            return s;
        }

        // cnt 개수만큼 0 위치 뒤에 추가
        while (cnt-- > 0) {
            sb.insert(zeroIdx, "110");
            zeroIdx += 3;
        }

        return sb.toString();
    }
}