package study_240314.problemset;

import java.io.*;
import java.util.*;

public class boj_14728 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken());
        int t = Integer.parseInt(stk.nextToken());

        int[] dp = new int[t + 1]; // DP

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(stk.nextToken()); // 시간
            int score = Integer.parseInt(stk.nextToken()); // 점수

            // 최댓값 구하기
            for (int j = t; j >= time; j--) {
                dp[j] = Math.max(dp[j], dp[j - time] + score);
            }
        }

        // 정답 출력
        sb.append(dp[t]);
        System.out.println(sb);
    }
}
