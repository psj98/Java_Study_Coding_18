package study_240418.problemset;

import java.io.*;
import java.util.*;

public class boj_2157 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 1 ~ N으로 종료
        // 서쪽으로만 이동 => 도시 번호가 증가하는 순서대로
        // 맛있는 기내식만 먹으며 이동
        // 기내식 총합

        int n = Integer.parseInt(stk.nextToken()); // 도시 개수
        int m = Integer.parseInt(stk.nextToken()); // 최대 방문 도시 개수
        int k = Integer.parseInt(stk.nextToken()); // 항공로 개수

        // 초기화
        int[][] airLines = new int[n + 1][n + 1]; // 항공로
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], -1);
        }

        dp[1][1] = 0; // 시작 위치

        for (int i = 0; i < k; i++) {
            stk = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(stk.nextToken()); // 시작 도시
            int to = Integer.parseInt(stk.nextToken()); // 종료 도시
            int score = Integer.parseInt(stk.nextToken()); // 기내식 점수

            // 동 => 서 체크
            if (from > to) {
                continue;
            }

            airLines[from][to] = Math.max(airLines[from][to], score); // 같은 항공로에 대해 최댓값 저장
        }

        // i개 방문했을 때, 위치한 도시
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int mid = 1; mid < j; mid++) {
                    // 항공로가 없거나 해당 지점에 가지 않은 경우, 체크
                    if (airLines[j - mid][j] == 0 || dp[i - 1][j - mid] == -1) {
                        continue;
                    }

                    // [도달한 개수 - 1][j - mid 도시] + 점수 : [j - mid 도시] ~ [j 도시]
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - mid] + airLines[j - mid][j]);
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= m; i++) {
            ans = Integer.max(ans, dp[i][n]); // m개 이하 중 목적지에 도달했을 때의 최댓값을 찾음
        }

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }
}
