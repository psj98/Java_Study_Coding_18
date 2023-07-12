package study_230712.problemset;

import java.io.*;

public class boj_3933 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        /**
         * DP
         * 2개로 만들려면 1개로 이미 만들어진 것에 더함
         * 3개로 만들려면 2개로 이미 만들어진 것에 더함
         * 4개로 만들려면 3개로 이미 만들어진 것에 더함
         */

        int[][] dp = new int[1 << 15][5]; // 2^15 / 4개까지 가능
        for (int i = 1; i * i < (1 << 15); i++) {
            dp[i * i][1] = 1; // 제곱수 => 1개로 가능

            for (int j = i * i; j < (1 << 15); j++) {
                dp[j][2] += dp[j - i * i][1];
                dp[j][3] += dp[j - i * i][2];
                dp[j][4] += dp[j - i * i][3];
            }
        }

        while (true) {
            int num = Integer.parseInt(br.readLine());

            // 기저 조건
            if (num == 0)
                break;

            // 정답 계산
            int sum = 0;
            for (int i = 1; i <= 4; i++) {
                sum += dp[num][i];
            }

            sb.append(sum).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
}
