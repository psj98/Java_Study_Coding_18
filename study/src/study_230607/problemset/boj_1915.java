package study_230607.problemset;

import java.io.*;
import java.util.*;

public class boj_1915 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        /**
         * DP
         * 
         * 1. 현재 값에 따라 정사각형 길이 정보 저장
         * 1-1. 현재 값이 0인 경우, 스킵 -> 정사각형이 될 수 없음
         * 1-2. 현재 값이 0이 아닌 경우, 현재 값을 제외한 값 체크 -> i-1, j-1, i-1 j-1 값 비교
         *      => 최솟값 + 1을 현재 값에 저장 -> ans 갱신
         */

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        // 맵 정보
        int[][] map = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= m; j++)
                map[i][j] = str.charAt(j - 1) - '0';
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] == 0)
                    continue;

                map[i][j] = Math.min(map[i - 1][j], Math.min(map[i][j - 1], map[i - 1][j - 1])) + 1;
                ans = Math.max(ans, map[i][j]);
            }
        }

        sb.append(ans * ans);
        System.out.println(sb);
    }
}