package study_230405.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_6549 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        while (true) {
            stk = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(stk.nextToken());
            if (n == 0)
                break;

            Stack<Integer> stack = new Stack<>();
            long ans = 0;

            // 높이 저장
            long[] height = new long[n + 2];
            for (int i = 1; i <= n; i++)
                height[i] = Long.parseLong(stk.nextToken());

            // 마지막 값까지 계산하기 위해 n+1까지 진행
            for (int i = 1; i <= n + 1; i++) {
                // 현재 값이 이전 값보다 작은 경우
                while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                    // 이전 값을 가져와서 너비랑 곱함
                    long area = 0;
                    long pre = height[stack.pop()]; // 이전 높이

                    if (stack.isEmpty()) { // Stack이 빈 경우, 너비는 맨 처음부터 현재까지
                        area = (i - 1) * pre;
                    } else { // Stack에 값이 있는 경우, 너비는 해당 값부터 현재까지
                        area = (i - stack.peek() - 1) * pre;
                    }

                    ans = Math.max(area, ans); // 최댓값 갱신
                }

                stack.add(i); // index 저장 (너비를 알기 위해)
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }
}
