package study_230719.problemset;

import java.io.*;
import java.util.*;

public class boj_2240 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        /**
         * DP
         * 1. 자두는 T초 동안 떨어짐 / 최대 W번만 움직여야 함
         * 2. 받을 수 있는 자두의 최대 개수 / 시작 1번 나무
         */

        int T = Integer.parseInt(stk.nextToken());
        int W = Integer.parseInt(stk.nextToken());

        // 자두 정보
        int[] trees = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            trees[i] = Integer.parseInt(br.readLine());
        }

        int[][][] dp = new int[3][T + 1][W + 2]; // [현재 위치][현재 시간][이동 횟수] => 이동 횟수 0 포함
        for (int i = 1; i <= T; i++) {
            for (int j = 1; j <= W + 1; j++) {
                if (trees[i] == 1) { // 1번에 떨어질 경우
                    dp[1][i][j] = Math.max(dp[1][i - 1][j], dp[2][i - 1][j - 1]) + 1;
                    dp[2][i][j] = Math.max(dp[1][i - 1][j - 1], dp[2][i - 1][j]);
                } else { // 2번에 떨어질 경우
                    // 시작할 때, 1번에 있어야 함
                    if (i == 1 && j == 1) {
                        continue;
                    }

                    dp[1][i][j] = Math.max(dp[1][i - 1][j], dp[2][i - 1][j - 1]);
                    dp[2][i][j] = Math.max(dp[1][i - 1][j - 1], dp[2][i - 1][j]) + 1;
                }
            }
        }

        // 정답 출력 => 1, 2번 위치에서 T시간이 지났을 때 최댓값
        int ans = 0;
        for (int i = 0; i <= W + 1; i++) {
            ans = Math.max(ans, Math.max(dp[1][T][i], dp[2][T][i]));
        }

        sb.append(ans);
        System.out.println(sb);
    }
}
