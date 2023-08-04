package study_230802.problemset;

import java.util.*;
import java.io.*;

public class boj_12785_1 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int w = Integer.parseInt(stk.nextToken());
        int h = Integer.parseInt(stk.nextToken());

        stk = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(stk.nextToken());
        int y = Integer.parseInt(stk.nextToken());

        // 초기화
        long dp[][] = new long[h][w];
        for (int i = 0; i < h; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < w; i++) {
            dp[0][i] = 1;
        }

        // 숫자 저장하면서 경우의 수 계산
        for (int i = 1; i < h; i++) {
            for (int j = 1; j < w; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000007;
            }
        }

        sb.append((dp[y - 1][x - 1] * dp[h - y][w - x]) % 1000007);
        System.out.println(sb);
    }
}