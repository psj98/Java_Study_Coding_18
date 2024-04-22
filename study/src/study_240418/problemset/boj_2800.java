package study_240418.problemset;

import java.io.*;
import java.util.*;

public class boj_2800 {
    static ArrayList<int[]> brackets;
    static boolean[] check;
    static TreeSet<String> set;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine(); // 문자열
        int size = str.length();

        // 초기화
        Stack<Integer> stack = new Stack<>();
        brackets = new ArrayList<>();
        check = new boolean[size];
        set = new TreeSet<>();

        // 괄호 위치 저장
        for (int i = 0; i < size; i++) {
            char c = str.charAt(i);

            if (c == '(') { // '('인 경우, 위치를 stack에 저장
                stack.add(i);
            } else if (c == ')') { // ')'인 경우, '('의 위치와 ')'의 위치를 List에 저장
                brackets.add(new int[] { stack.pop(), i });
            }
        }

        find(0, str.toCharArray()); // 괄호가 제거된 문자열 찾기

        // 정답 저장
        for (String s : set) {
            System.out.println(s);
        }

        // 정답 출력
        System.out.println(sb);
    }

    // 괄호가 제거된 문자열 찾기
    static void find(int cnt, char[] str) {
        // 괄호 크기만큼 했을 경우
        if (cnt == brackets.size()) {
            StringBuilder sb = new StringBuilder();
            boolean isRemoved = false; // 괄호가 제거되었는지 판단

            for (int i = 0; i < str.length; i++) {
                // 괄호가 제거된 경우, true로 변경
                if (check[i]) {
                    isRemoved = true;
                    continue;
                }

                sb.append(str[i]); // 문자열 추가
            }

            // 괄호가 제거된 경우에만, set에 저장
            if (isRemoved) {
                set.add(sb.toString());
            }

            return;
        }

        find(cnt + 1, str); // 괄호가 제거되지 않은 경우

        // 괄호가 제거된 경우
        int[] bracketIdx = brackets.get(cnt);
        check[bracketIdx[0]] = true;
        check[bracketIdx[1]] = true;
        find(cnt + 1, str);
        check[bracketIdx[0]] = false;
        check[bracketIdx[1]] = false;

    }
}
