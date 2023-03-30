package study_230329.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_14003 {
    static int[] num, dp, index;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        num = new int[n + 1]; // 값 저장 배열
        dp = new int[n + 1]; // 길이 구할 배열
        index = new int[n + 1]; // 위치를 저장할 배열

        // 값 입력 및 저장
        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            num[i] = Integer.parseInt(stk.nextToken());

        int idx = 1; // 시작 위치
        dp = new int[n + 1];
        dp[1] = num[1];
        index[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (dp[idx] < num[i]) {
                dp[++idx] = num[i];
                index[i] = idx;
            } else {
                int low = lowerBound(1, idx, num[i]); // 이분 탐색
                dp[low] = num[i];
                index[i] = low; // i번째 배열에 idx 저장
            }
        }

        sb.append(idx).append("\n"); // 길이 출력

        // 부분 수열 찾기 -> idx에서 거꾸로 이동하면서 stack에 저장
        Stack<Integer> stack = new Stack<>();
        for (int i = n; i > 0; i--) {
            if (index[i] == idx) {
                stack.add(num[i]);
                idx--;
            }
        }

        // 부분 수열 출력
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        // 정답 출력
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
