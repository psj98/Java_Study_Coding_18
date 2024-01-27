package study_240125.problemset;

import java.io.*;
import java.util.Arrays;

public class boj_2631 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int ans = 0;
        int[] line = new int[n];

        for (int i = 0; i < n; i++) {
            line[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        // LIS 구하기
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (line[i] <= line[j]) {
                    continue;
                }

                dp[i] = Math.max(dp[i], dp[j] + 1);
            }

            ans = Math.max(ans, dp[i]);
        }

        // 정답 출력
        sb.append(n - ans);
        System.out.println(sb);
    }
}
