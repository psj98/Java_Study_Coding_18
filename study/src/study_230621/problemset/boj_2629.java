package study_230621.problemset;

import java.io.*;
import java.util.*;

public class boj_2629 {
    static int n;
    static int[] weight;
    static boolean[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        weight = new int[n];
        dp = new boolean[n + 1][15001];

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            weight[i] = Integer.parseInt(stk.nextToken());
        }

        findWeight(0, 0); // DP

        // 확인하고자 하는 구슬 무게
        int m = Integer.parseInt(br.readLine());

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int marble = Integer.parseInt(stk.nextToken());
            if (marble > 15000) // 30개 * 500g 까지 가능
                sb.append("N ");
            else {
                if (dp[n][marble]) {
                    sb.append("Y ");
                } else {
                    sb.append("N ");
                }
            }
        }

        // 정답 출력
        System.out.println(sb);
    }

    static void findWeight(int cnt, int sum) {
        if (dp[cnt][sum]) {
            return;
        }

        dp[cnt][sum] = true;

        if (cnt == n) {
            return;
        }

        findWeight(cnt + 1, sum + weight[cnt]); // 왼쪽에 놓는 경우
        findWeight(cnt + 1, sum); // 둘 다 안놓는 경우
        findWeight(cnt + 1, Math.abs(sum - weight[cnt])); // 오른쪽에 놓는 경우
    }
}
