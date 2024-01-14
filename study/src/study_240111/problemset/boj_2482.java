package study_240111.problemset;

import java.io.*;

public class boj_2482 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 전체 개수
        int k = Integer.parseInt(br.readLine()); // 뽑을 개수

        // n / 2보다 크면 고를 수 없음
        if (k > n / 2) {
            sb.append(0);
            System.out.println(sb);
            return;
        }

        int mod = 1_000_000_003; // 나눌 값

        // 초기값 저장
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][1] = i;
        }

        // DP
        for (int i = 4; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % mod;
            }
        }

        // 정답 출력
        sb.append(dp[n][k]);
        System.out.println(sb);
    }
}
