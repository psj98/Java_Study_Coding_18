package study_240321.problemset;

import java.io.*;
import java.util.*;

public class boj_2616 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int[] train = new int[n + 1]; // 승객 정보
        int[] sum = new int[n + 1]; // 승객 누적합
        int[][] dp = new int[4][n + 1]; // DP : [소형 기관차 수][객차 수] - 소형 기관차 i대로 j칸의 객차를 끌 수 있는 경우

        // 승객 정보
        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            train[i] = Integer.parseInt(stk.nextToken());
            sum[i] = sum[i - 1] + train[i];
        }

        int maxTrain = Integer.parseInt(br.readLine()); // 소형 기관차가 끌 수 있는 최대 객차 수

        // DP :
        for (int i = 1; i <= 3; i++) {
            for (int j = i * maxTrain; j <= n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - maxTrain] + sum[j] - sum[j - maxTrain]);
            }
        }

        // 정답 출력
        sb.append(dp[3][n]);
        System.out.println(sb);
    }
}
