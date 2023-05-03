package study_230503.problemset;

import java.io.*;

public class boj_20500 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        if (n == 1) { // 1인 경우, 0 출력
            sb.append(0);
            System.out.println(sb);
            return;
        }

        int mod = 1_000_000_007;
        int[][] dp = new int[n + 1][3]; // 자릿수, 3으로 나눈 나머지
        dp[2][0] = 1;
        dp[2][1] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i][0] = (dp[i - 1][1] + dp[i - 1][2]) % mod; // 이전 나머지 1에 +5 & 이전 나머지 2에 +1
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % mod; // 이전 나머지 0에 +1 & 이전 나머지 2에 +5
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % mod; // 이전 나머지 0에 +5 & 이전 나머지 1에 +1
        }

        // 정답 출력
        sb.append(dp[n][0]);
        System.out.println(sb);
    }
}
