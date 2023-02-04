package study_230201.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class alg_1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int idx = 0;

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int m = Integer.parseInt(br.readLine());

            if (idx < m) {
                for (int j = idx + 1; j <= m; j++) {
                    stack.push(j);
                    sb.append("+\n");
                }

                idx = m;
            }

            else if (!stack.empty() && stack.peek() != m) {
                System.out.println("NO");
                return;
            }

            if (!stack.empty())
                stack.pop();
            sb.append("-\n");
        }

        System.out.println(sb);
    }
}