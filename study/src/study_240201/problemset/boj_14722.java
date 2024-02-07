package study_240201.problemset;

import java.io.*;
import java.util.*;

public class boj_14722 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        // 초기화
        int[][] map = new int[n][n];
        int[][][] dp = new int[n][n][3];

        // 맵 정보
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        // 초기값 설정
        if (map[0][0] == 0) {
            dp[0][0][0] = 1;
        }

        for (int i = 1; i < n; i++) {
            // 동쪽으로 가는 경우, 초기값
            int row = map[0][i];

            dp[0][i][0] = (row == 0 ? dp[0][i - 1][2] + 1 : dp[0][i - 1][0]);
            dp[0][i][1] = ((row == 1 && dp[0][i][2] < dp[0][i][0]) ? dp[0][i - 1][0] + 1 : dp[0][i - 1][1]);
            dp[0][i][2] = ((row == 2 && dp[0][i][0] < dp[0][i][1]) ? dp[0][i - 1][1] + 1 : dp[0][i - 1][2]);

            // 남쪽으로 가는 경우, 초기값 설정
            int col = map[i][0];

            dp[i][0][0] = (col == 0 ? dp[i - 1][0][2] + 1 : dp[i - 1][0][0]);
            dp[i][0][1] = ((col == 1 && dp[i][0][2] < dp[i][0][0]) ? dp[i - 1][0][0] + 1 : dp[i - 1][0][1]);
            dp[i][0][2] = ((col == 2 && dp[i][0][0] < dp[i][0][1]) ? dp[i - 1][0][1] + 1 : dp[i - 1][0][2]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                int cur = map[i][j];

                // 최댓값 갱신 (동쪽 or 남쪽 중 최댓값)
                dp[i][j][0] = (cur == 0 ? Math.max(dp[i][j - 1][2] + 1, dp[i - 1][j][2] + 1)
                        : Math.max(dp[i][j - 1][0], dp[i - 1][j][0]));
                dp[i][j][1] = ((cur == 1 && dp[i][j][2] < dp[i][j][0])
                        ? Math.max(dp[i][j - 1][0] + 1, dp[i - 1][j][0] + 1)
                        : Math.max(dp[i][j - 1][1], dp[i - 1][j][1]));
                dp[i][j][2] = ((cur == 2 && dp[i][j][0] < dp[i][j][1])
                        ? Math.max(dp[i][j - 1][1] + 1, dp[i - 1][j][1] + 1)
                        : Math.max(dp[i][j - 1][2], dp[i - 1][j][2]));
            }
        }

        // 정답 출력
        sb.append(Math.max(dp[n - 1][n - 1][0], Math.max(dp[n - 1][n - 1][1], dp[n - 1][n - 1][2])));
        System.out.println(sb);
    }
}
