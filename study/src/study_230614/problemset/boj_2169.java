package study_230614.problemset;

import java.io.*;
import java.util.*;

public class boj_2169 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        int[][] map = new int[n][m];
        int[][] dp = new int[n][m];
        int[][] check = new int[m][2];

        // 맵 정보
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        dp[0][0] = map[0][0]; // 초기화

        // 첫번째 줄 누적합 계산
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + map[0][i];
        }

        for (int i = 1; i < n; i++) {
            // 왼쪽 -> 오른쪽 / MAX(왼쪽에서 옴, 위쪽에서 옴)
            check[0][0] = dp[i - 1][0] + map[i][0];
            for (int j = 1; j < m; j++) {
                check[j][0] = Math.max(check[j - 1][0], dp[i - 1][j]) + map[i][j];
            }

            // 오른쪽 -> 왼쪽 / MAX(오른쪽에서 옴, 위쪽에서 옴)
            check[m - 1][1] = dp[i - 1][m - 1] + map[i][m - 1];
            for (int j = m - 2; j >= 0; j--) {
                check[j][1] = Math.max(check[j + 1][1], dp[i - 1][j]) + map[i][j];
            }

            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.max(check[j][0], check[j][1]);
            }
        }

        // 정답 출력
        sb.append(dp[n - 1][m - 1]);
        System.out.println(sb);
    }
}
