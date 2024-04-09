package study_240404.problemset;

import java.io.*;

public class boj_2591 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();

        int n = str.length();
        int[] dp = new int[n + 1];

        dp[0] = dp[1] = 1;

        for (int i = 1; i < n; i++) {
            dp[i + 1] += dp[i];

            int ten = str.charAt(i - 1) - '0'; // 십의 자리
            int one = str.charAt(i) - '0'; // 일의 자리

            int num = ten * 10 + one; // 전체 숫자

            // 십의 자리가 0이 아님 & 1 <= num <= 34
            if (ten != 0 && num >= 1 && num <= 34) {
                dp[i + 1] += dp[i - 1]; // 2자리 가능
            }

            // 일의 자리가 0인 경우
            if (one == 0) {
                dp[i + 1] -= dp[i]; // 바로 앞자리 불가능
            }
        }

        // 정답 출력
        sb.append(dp[n]);
        System.out.println(sb);
    }
}
