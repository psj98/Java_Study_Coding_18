package study_230315.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_14002 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        // 입력 및 초기화
        int n = Integer.parseInt(br.readLine());
        int[] cost = new int[n];
        int[] dp = new int[n];
        dp[0] = 1;

        // 값 저장
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            cost[i] = Integer.parseInt(stk.nextToken());

        int ans = 1;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (cost[i] > cost[j])
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
            }

            ans = Math.max(ans, dp[i]); // dp의 최댓값 -> 최장 증가 수열 길이
        }

        Stack<Integer> stack = new Stack<>();
        
        // 앞에서부터 dp[]에 저장된 값이 1, 2 ~ ans 까지이기 때문에 거꾸로 받아와야 함
        for (int i = n - 1; i >= 0; i--) {
            if (ans == dp[i]) { // ans와 같으면 스택에 추가
                stack.add(cost[i]);
                ans--;
            }
        }

        // 정답 출력
        sb.append(stack.size()).append("\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);
    }
}
