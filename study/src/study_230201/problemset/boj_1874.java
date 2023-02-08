package study_230201.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int idx = 0; // 넣을 수

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int m = Integer.parseInt(br.readLine());

            // m보다 작으면 스택에 넣음
            if (idx < m) {
                for (int j = idx + 1; j <= m; j++) {
                    stack.push(j);
                    sb.append("+\n");
                }

                idx = m; // idx보다 큰 수를 넣어야 하기 때문에 갱신
            }

            // idx >= m이면 이미 stack에 값이 있다는 얘기
            // 가장 위에 있는 값이 m이 아니면, 불가능
            else if (!stack.empty() && stack.peek() != m) {
                System.out.println("NO");
                return;
            }

            // 해당 값 추출
            if (!stack.empty())
                stack.pop();
            sb.append("-\n");
        }

        System.out.println(sb);
    }
}
