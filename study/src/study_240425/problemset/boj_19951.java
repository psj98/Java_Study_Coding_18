package study_240425.problemset;

import java.io.*;
import java.util.*;

public class boj_19951 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // n칸, m명의 조교
        // a ~ b까지 k만큼 흙을 덮거나 파내기
        int n = Integer.parseInt(stk.nextToken()); // 연병장 칸 수
        int m = Integer.parseInt(stk.nextToken()); // 조교 수

        int[] playGround = new int[n]; // 연병장 각 칸 높이
        int[] sum = new int[n + 1]; // 누적합 배열

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            playGround[i] = Integer.parseInt(stk.nextToken());
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(stk.nextToken()) - 1; // 시작
            int end = Integer.parseInt(stk.nextToken()) - 1; // 끝
            int k = Integer.parseInt(stk.nextToken()); // 높이

            sum[start] = sum[start] + k;
            sum[end + 1] = sum[end + 1] - k;
        }

        for (int i = 1; i <= n; i++) {
            sum[i] += sum[i - 1];
        }

        for (int i = 0; i < n; i++) {
            playGround[i] += sum[i];
            sb.append(playGround[i]).append(" ");
        }

        // 정답 출력
        System.out.println(sb);
    }
}
