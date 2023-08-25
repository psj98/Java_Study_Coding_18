import java.io.*;
import java.util.*;

public class boj_2225 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken()); // 만들어야 할 값
        int k = Integer.parseInt(stk.nextToken()); // 몇 개로 해야할 지

        int[][] dp = new int[n + 1][k + 1];

        // 초기화
        for (int i = 0; i <= n; i++) {
            dp[i][1] = 1;
        }

        for (int i = 1; i <= k; i++) {
            dp[0][i] = 1;
        }

        // 계산
        // dp[n][k] = dp[n-1][k] + dp[n][k-1];
        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % 1000000000;
            }
        }

        sb.append(dp[n][k]);
        System.out.println(sb);
    }
}
