package study_231221.problemset;

import java.io.*;
import java.util.*;

public class boj_7579 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken()); // 앱 개수
        int m = Integer.parseInt(stk.nextToken()); // 새로운 앱 B를 실행할 때 필요한 메모리

        int[] app = new int[n]; // 앱 메모리
        int[] cost = new int[n]; // 비용

        // 앱 메모리
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            app[i] = Integer.parseInt(stk.nextToken());
        }

        // 비용
        int sum = 0;
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            sum += cost[i] = Integer.parseInt(stk.nextToken());
        }

        int[] dp = new int[sum + 1]; // DP 배열
        for (int i = 0; i < n; i++) {
            for (int j = sum; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + app[i]); // 최댓값 갱신
            }
        }

        // m을 넘으면 정답 출력
        for (int i = 0; i <= sum; i++) {
            if (dp[i] < m) {
                continue;
            }

            // 정답 출력
            sb.append(i);
            System.out.println(sb);
            break;
        }
    }
}
