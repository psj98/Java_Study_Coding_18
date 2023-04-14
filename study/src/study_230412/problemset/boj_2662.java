package study_230412.problemset;

import java.io.*;
import java.util.*;

public class boj_2662 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        // i만원을 투자했을 때 j기업이 얻는 이익
        int[][] investment = new int[n + 1][m + 1];
        int[][] dp = new int[n + 1][m + 1];
        int[][] company = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            stk = new StringTokenizer(br.readLine());
            stk.nextToken();
            for (int j = 1; j <= m; j++)
                investment[i][j] = Integer.parseInt(stk.nextToken());
        }

        // DP 계산
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) { // n만원 투자
                for (int k = 0; k <= j; k++) {
                    // i기업에 j만원을 투자한 이익 = 이전 기업에서 j-k만원을 투자한 이익 + i기업에 k만원을 투자한 이익
                    if (dp[j][i] < dp[j - k][i - 1] + investment[k][i]) {
                        dp[j][i] = dp[j - k][i - 1] + investment[k][i];
                        company[j][i] = k; // 현재 기업에서 k만원 투자 -> 빼면서 찾기?
                    }
                }
            }
        }

        int ans = dp[n][m]; // 최대 이익
        sb.append(ans).append("\n");

        Stack<Integer> stack = new Stack<>();
        while (m > 0) {
            int invest = company[n][m]; // 현재 기업에서 투자한 액수
            stack.add(invest);

            n -= invest; // 현재 액수 - 투자한 액수 = 다음 기업이 투자한 액수
            m--; // 다음 기업
        }

        // 스택에서 빼면서 순서대로 출력
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);
    }
}
