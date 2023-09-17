package study_230906.problemset;

import java.io.*;
import java.util.*;

public class boj_1106 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        /**
         * 적어도 C명 늘리기 위해 투자해야 할 돈의 최솟값
         * => 얻을 수 있는 고객의 수는 100 이하
         * => new int[c + 101];
         */
        int c = Integer.parseInt(stk.nextToken());
        int n = Integer.parseInt(stk.nextToken());

        // 배열 초기화
        int[] dp = new int[c + 101];
        Arrays.fill(dp, 987654321);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(stk.nextToken());
            int customer = Integer.parseInt(stk.nextToken());

            // 이전 값부터 최솟값 갱신
            for (int j = customer; j < c + 101; j++) {
                dp[j] = Math.min(dp[j], cost + dp[j - customer]);
            }
        }

        // 최솟값 구하기
        int ans = Integer.MAX_VALUE;
        for (int i = c; i < c + 101; i++) {
            ans = Math.min(ans, dp[i]);
        }

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }
}
