package study_240328.problemset;

import java.io.*;
import java.util.*;

public class boj_15486 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 2]; // i일째 받을 수 있는 최대 이익
        int[] time = new int[n + 2]; // 시간
        int[] cost = new int[n + 2]; // 비용

        for (int i = 1; i <= n; i++) {
            stk = new StringTokenizer(br.readLine());

            time[i] = Integer.parseInt(stk.nextToken()); // 시간
            cost[i] = Integer.parseInt(stk.nextToken()); // 비용
        }

        int ans = 0; // 정답
        for (int i = 1; i < n + 2; i++) {
            ans = Math.max(ans, dp[i]); // 현재 일까지의 최댓값 갱신

            int endDate = i + time[i]; // 상담 종료일

            // 종료일이 퇴사일을 넘어가는 경우, 스킵
            if (endDate > n + 1) {
                continue;
            }

            // 최댓값 갱신
            dp[endDate] = Math.max(dp[endDate], ans + cost[i]);
        }

        // 정답 출력
        sb.append(dp[n + 1]);
        System.out.println(sb);
    }
}
