package study_230215.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_1406_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();
        Stack<Character> left = new Stack<Character>();
        Stack<Character> right = new Stack<Character>();

        String str = br.readLine();
        for (int i = 0; i < str.length(); i++)
            left.add(str.charAt(i)); // 각 자리수를 Character형으로 저장

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            char op = stk.nextToken().charAt(0);

            if (op == 'L') { // 커서 왼쪽으로
                if (!left.empty())
                    right.push(left.pop());
            } else if (op == 'D') { // 커서 오른쪽으로
                if (!right.empty())
                    left.push(right.pop());
            } else if (op == 'B') { // 커서 왼쪽 값 삭제
                if (!left.empty())
                    left.pop();
            } else if (op == 'P') { // 커서 왼쪽에 추가
                left.add(stk.nextToken().charAt(0));
            }
        }

        while (!left.empty()) {
            right.add(left.pop());
        }

        while (!right.empty()) {
            sb.append(right.pop());
        }

        System.out.println(sb);
    }
}
