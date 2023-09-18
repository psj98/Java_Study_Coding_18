package study_230913.problemset;

import java.io.*;
import java.util.*;

public class boj_2056 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] time = new int[N + 1];
        int[] dp = new int[N + 1];

        // 1번 작업 => 선행 작업 없음
        stk = new StringTokenizer(br.readLine());
        time[1] = dp[1] = Integer.parseInt(stk.nextToken());
        stk.nextToken();

        // 1번 이후 작업 (2번 ~)
        for (int i = 2; i <= N; i++) {
            stk = new StringTokenizer(br.readLine());
            time[i] = dp[i] = Integer.parseInt(stk.nextToken()); // 시간

            int cnt = Integer.parseInt(stk.nextToken()); // 선행 작업 개수
            for (int j = 0; j < cnt; j++) {
                int num = Integer.parseInt(stk.nextToken());
                dp[i] = Math.max(dp[i], dp[num] + time[i]);
            }
        }

        Arrays.sort(dp); // 오름차순 정렬

        // 정답 출력
        sb.append(dp[N]);
        System.out.println(sb);
    }
}
