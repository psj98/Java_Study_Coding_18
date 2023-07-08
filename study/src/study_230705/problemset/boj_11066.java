package study_230705.problemset;

import java.io.*;
import java.util.*;

public class boj_11066 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        /**
         * DP
         * 
         * 시작 ~ 끝 파일 합 = (시작 ~ 중간)의 최솟값 + ([중간 + 1] ~ 끝)의 최솟값 + (시작 ~ 끝까지의 파일) 합
         * dp[from][to] = dp[from][mid] + dp[mid+1][to] + sum[to]-sum[from-1]
         */

        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());

            // 초기화
            int[] arr = new int[n + 1];
            int[] sum = new int[n + 1];
            int[][] dp = new int[n + 1][n + 1];
            stk = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(stk.nextToken());
                sum[i] = sum[i - 1] + arr[i]; // 누적합
            }

            // 파일합 구하기
            for (int diff = 1; diff < n; diff++) { // 시작 ~ 끝 차이
                for (int from = 1; diff + from <= n; from++) { // 시작
                    int to = diff + from; // 끝

                    // 최솟값 구하기
                    dp[from][to] = Integer.MAX_VALUE;
                    for (int mid = from; mid < to; mid++) {
                        dp[from][to] = Math.min(dp[from][to],
                                dp[from][mid] + dp[mid + 1][to] + sum[to] - sum[from - 1]);
                    }
                }
            }

            // 정답 출력
            sb.append(dp[1][n]).append("\n");
        }

        System.out.println(sb);
    }
}
