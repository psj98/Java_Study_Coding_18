package study_230830.problemset;

import java.io.*;
import java.util.*;

public class boj_2096 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[][] min = new int[n][3]; // 최솟값 배열
        int[][] max = new int[n][3]; // 최댓값 배열

        // 초기화
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++)
                min[i][j] = max[i][j] = Integer.parseInt(stk.nextToken());
        }

        for (int i = 1; i < n; i++) {
            // 최솟값 찾기
            min[i][0] += Math.min(min[i - 1][0], min[i - 1][1]);
            min[i][1] += Math.min(min[i - 1][0], Math.min(min[i - 1][1], min[i - 1][2]));
            min[i][2] += Math.min(min[i - 1][1], min[i - 1][2]);

            // 최댓값 찾기
            max[i][0] += Math.max(max[i - 1][0], max[i - 1][1]);
            max[i][1] += Math.max(max[i - 1][0], Math.max(max[i - 1][1], max[i - 1][2]));
            max[i][2] += Math.max(max[i - 1][1], max[i - 1][2]);
        }

        // 정답 출력
        sb.append(Math.max(max[n - 1][0], Math.max(max[n - 1][1], max[n - 1][2]))).append(" ");
        sb.append(Math.min(min[n - 1][0], Math.min(min[n - 1][1], min[n - 1][2])));
        System.out.println(sb);
    }
}
