package study_240104.problemset;

import java.io.*;
import java.util.*;

public class boj_1757 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken()); // 운동할 시간
        int m = Integer.parseInt(stk.nextToken()); // 지침 지수 범위

        // 거리 정보
        int[] distance = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            distance[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[n + 1][m + 1]; // [현재 위치][지침 지수]
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0]; // 쉰 경우

            // 달린 경우
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j - 1] + distance[i];
            }

            for (int j = 1; j <= m; j++) {
                if (j >= i) {
                    break;
                }

                dp[i][0] = Math.max(dp[i][0], dp[i - j][j]); // 최댓값 찾기
            }
        }

        // 정답 출력
        sb.append(dp[n][0]);
        System.out.println(sb);
    }
}
