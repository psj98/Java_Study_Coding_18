package study_230419.problemset;

import java.io.*;
import java.util.*;

public class boj_14863 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());

        int[][] veh = new int[n][2];
        int[][] weight = new int[n][2];
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                veh[i][j] = Integer.parseInt(stk.nextToken());
                weight[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        // 정답 출력
        System.out.println(sb);
    }
}
