package study_231228.problemset;

import java.io.*;
import java.util.*;

public class boj_2560 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int a = Integer.parseInt(stk.nextToken()); // 성체가 되는데 걸리는 일 수
        int b = Integer.parseInt(stk.nextToken()); // 개체를 만들지 않는 순간
        int d = Integer.parseInt(stk.nextToken()); // 죽는 날짜
        int N = Integer.parseInt(stk.nextToken()); // 결과 날짜

        int[] dp = new int[N + 1];
        dp[0] = 1;

        for (int i = 1; i <= N; i++) {
            if (i < a) {
                dp[i] = dp[i - 1] % 1000;
            } else if (i < b) {
                dp[i] = (dp[i - 1] + dp[i - a]) % 1000;
            } else {
                dp[i] = (dp[i - 1] + dp[i - a] - dp[i - b] + 1000) % 1000;
            }
        }

        if (N - d >= 0) {
            sb.append((dp[N] - dp[N - d] + 1000) % 1000);
        } else {
            sb.append(dp[N]);
        }

        // 정답 출력
        System.out.println(sb);
    }
}
