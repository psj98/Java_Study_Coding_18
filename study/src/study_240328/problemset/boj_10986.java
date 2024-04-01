package study_240328.problemset;

import java.io.*;
import java.util.*;

public class boj_10986 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken()); // 개수
        int m = Integer.parseInt(stk.nextToken()); // 나눠야하는 값

        int[] sum = new int[n + 1]; // 누적합 배열
        long[] idx = new long[m]; // 나머지 값 개수

        long cnt = 0; // 정답

        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            sum[i] = (Integer.parseInt(stk.nextToken()) + sum[i - 1]) % m; // 나머지의 누적합

            // 0이면 맨 처음부터 현재까지 m으로 나눌 수 있기 때문에 정답 증가
            if (sum[i] == 0) {
                cnt++;
            }

            idx[sum[i]]++; // 나머지에 대해 개수 증가
        }

        // 개수가 같은 값에 대해 n * (n - 1) / 2 더하기
        for (int i = 0; i < m; i++) {
            if (idx[i] <= 1) {
                continue;
            }

            cnt += (idx[i] * (idx[i] - 1)) / 2;
        }

        // 정답 출력
        sb.append(cnt);
        System.out.println(sb);
    }
}
