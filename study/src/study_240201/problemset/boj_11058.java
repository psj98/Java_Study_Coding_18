package study_240201.problemset;

import java.io.*;

public class boj_11058 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        long[] dp = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;

            // 6 이하인 경우, A를 작성하는 것이 작업하는 것보다 많음
            if (i <= 6) {
                continue;
            }

            // 점화식
            for (int j = 2; j < 5; j++) {
                dp[i] = Math.max(dp[i], dp[i - (j + 1)] * j);
            }
        }

        // 정답 출력
        sb.append(dp[n]);
        System.out.println(sb);
    }
}
