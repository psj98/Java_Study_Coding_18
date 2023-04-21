package study_230419.problemset;

import java.io.*;
import java.util.*;

public class boj_14863 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());

        int[][] time = new int[n + 1][2]; // 시간
        int[][] cost = new int[n + 1][2]; // 비용
        int[][] dp = new int[n + 1][k + 1]; // dp 배열 (k시간에 해당하는 최댓값)

        // 값 저장
        for (int i = 1; i <= n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                time[i][j] = Integer.parseInt(stk.nextToken());
                cost[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        // 초기값 저장
        dp[1][time[1][0]] = cost[1][0];
        dp[1][time[1][1]] = Math.max(dp[1][time[1][1]], cost[1][1]);

        // 최댓값 갱신
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (dp[i - 1][j] == 0) // 0이면 계산할 필요 없음
                    continue;

                // 현재 시간 + 다음 시간 <= k
                if (j + time[i][0] <= k) // 더한 시간에서의 최댓값, 현재 시간에서의 최댓값 + 다음 비용 중 최댓값
                    dp[i][j + time[i][0]] = Math.max(dp[i][j + time[i][0]], dp[i - 1][j] + cost[i][0]);

                if (j + time[i][1] <= k)
                    dp[i][j + time[i][1]] = Math.max(dp[i][j + time[i][1]], dp[i - 1][j] + cost[i][1]);
            }
        }

        // 정답 출력
        int ans = 0;
        for (int i = 0; i <= k; i++)
            ans = Math.max(ans, dp[n][i]);
        sb.append(ans);
        System.out.println(sb);
    }
}
