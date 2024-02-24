package study_240222.problemset;

import java.io.*;
import java.util.*;

public class boj_3067 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine()); // 동전 개수

            int[] arr = new int[n]; // 동전 배열

            // 동전 정보
            stk = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(stk.nextToken());
            }

            int m = Integer.parseInt(br.readLine()); // 만들어야 하는 값

            int[] dp = new int[m + 1];
            dp[0] = 1;

            // DP
            for (int i = 0; i < n; i++) {
                for (int j = arr[i]; j <= m; j++) {
                    dp[j] += dp[j - arr[i]];
                }
            }

            // 정답 저장
            sb.append(dp[m]).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
}
