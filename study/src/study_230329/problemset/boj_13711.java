package study_230329.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_13711 {
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        // 입력
        int n = Integer.parseInt(br.readLine());
        int[] num1 = new int[n + 1];
        int[] num2 = new int[n + 1];
        int[] index = new int[n + 1];

        // 첫 번째 행 입력
        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            num1[i] = Integer.parseInt(stk.nextToken());

        // 두 번째 행 입력
        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int cur = Integer.parseInt(stk.nextToken());
            num2[cur] = i; // idx를 숫자, 들어온 순서를 값으로 저장
        }

        // index 배열에 num1 순서대로 나열
        for (int i = 1; i <= n; i++) {
            index[i] = num2[num1[i]];
        }

        int idx = 1;
        dp = new int[n + 1]; // dp 배열
        dp[1] = index[1];
        for (int i = 2; i <= n; i++) {
            if (dp[idx] < index[i])
                dp[++idx] = index[i];
            else {
                int low = lowerBound(1, idx, index[i]);
                dp[low] = index[i];
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
