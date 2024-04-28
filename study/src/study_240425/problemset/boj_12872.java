package study_240425.problemset;

import java.io.*;
import java.util.*;

public class boj_12872 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken()); // 노래 개수
        int m = Integer.parseInt(stk.nextToken()); // 사이에 적어도 있어야 할 곡 개수
        int p = Integer.parseInt(stk.nextToken()); // 들어야할 노래 개수

        int mod = 1_000_000_007;
        long[][] dp = new long[p + 1][n + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= p; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i][j] + dp[i - 1][j - 1] * (n - j + 1)) % mod; // 플레이리스트에 넣지 않은 음악

                // 플레이리스트에 넣은 음악 (m개의 간격을 둬야 함)
                if (j > m) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j] * (j - m)) % mod;
                }
            }
        }

        // 정답 출력
        sb.append(dp[p][n]);
        System.out.println(sb);
    }
}
