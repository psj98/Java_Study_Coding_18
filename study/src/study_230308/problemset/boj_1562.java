package study_230308.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_1562 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int sum = 0;
        int[][][] dp = new int[n + 1][10][1024]; // n 자릿수, 마지막 자리 값, 어떤 숫자가 들어있는지

        // 한 자릿수일 때 -> 마지막 자리 값에 대해 비트마스킹 (1은 1, 2는 2, 3은 4, ...)
        for (int i = 1; i < 10; i++)
            dp[1][i][1 << i] = 1;

        // 완전 탐색
        // 2자릿수 - 마지막 자리 5 (j) -> 45, 65 - 이에 대해 비트마스킹 (현재 비트마스킹 값(k) | (1 << j))
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 1024; k++) {
                    int bit = k | (1 << j); // 다음에 갈 비트 값

                    if (j == 0) // 마지막 자리가 0인 경우, 현재 자리가 1일 때만 가능
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][1][k]) % 1000000000;
                    else if (j == 9) // 마지막 자리가 9인 경우, 현재 자리가 8일 때만 가능
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][8][k]) % 1000000000;
                    else // 나머지는 j - 1 이나 j + 1 일 때 가능
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j - 1][k] + dp[i - 1][j + 1][k]) % 1000000000;
                }
            }
        }

        // n자릿수 -> 0 ~ 9까지 다 있는 경우
        for (int i = 0; i < 10; i++)
            sum = (sum + dp[n][i][1023]) % 1000000000;

        sb.append(sum);
        System.out.println(sb);
    }
}
