package study_230705.problemset;

import java.io.*;

public class boj_10422 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        /**
         * DP
         * - 써보면서 생각
         * 
         * dp[6] = dp[4]*dp[0] + dp[2]*dp[2] + dp[0]*dp[4]
         * dp[8] = dp[6]*dp[0] + dp[4]*dp[2] + dp[2]*dp[4] + dp[0]*dp[6] ...
         */

        final long MOD = 1_000_000_007;

        int n = Integer.parseInt(br.readLine());

        // dp 계산
        long[] brackets = new long[5001];
        brackets[0] = brackets[2] = 1;
        for (int i = 4; i <= 5000; i += 2) {
            for (int j = 0; j < i; j += 2) {
                brackets[i] += brackets[j] * brackets[i - j - 2];
                brackets[i] %= MOD;
            }
        }

        // 정답 출력
        for (int i = 0; i < n; i++) {
            sb.append(brackets[Integer.parseInt(br.readLine())]).append("\n");
        }

        System.out.println(sb);
    }
}
