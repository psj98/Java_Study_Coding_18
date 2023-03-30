package study_230329.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2352 {
    static int[] num, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        num = new int[n + 1];
        dp = new int[n + 1];

        // 값 입력 및 저장
        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            num[i] = Integer.parseInt(stk.nextToken());

        int idx = 1;
        dp = new int[n + 1]; // dp 배열
        dp[1] = num[1];
        for (int i = 2; i <= n; i++) {
            if (dp[idx] < num[i])
                dp[++idx] = num[i];
            else {
                int low = lowerBound(1, idx, num[i]);
                dp[low] = num[i];
            }
        }

        // 최대 길이 출력
        sb.append(idx);
        System.out.println(sb);
    }

    static int lowerBound(int left, int right, int num) {
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (num <= dp[mid])
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }
}
