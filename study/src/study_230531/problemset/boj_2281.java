package study_230531.problemset;

import java.io.*;
import java.util.*;

public class boj_2281 {
    static int n, m;
    static int[] names, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        names = new int[n];
        for (int i = 0; i < n; i++) {
            names[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[n - 1] = 0; // 마지막 줄은 계산 X

        sb.append(check(0));
        System.out.println(sb);
    }

    static int check(int idx) {
        if (dp[idx] < Integer.MAX_VALUE)
            return dp[idx];

        int num = m - names[idx]; // 해당 줄에서 남은 칸

        for (int i = idx + 1; i <= n; i++) {
            if (num < 0) // 더 이상 추가하지 못하는 경우
                break;

            // 끝까지 간 경우
            if (i == n) {
                dp[idx] = 0;
                break;
            }

            dp[idx] = Math.min(dp[idx], num * num + check(i));
            num -= names[i] + 1;
        }

        return dp[idx];
    }
}
