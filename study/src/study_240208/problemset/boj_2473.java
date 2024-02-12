package study_240208.problemset;

import java.io.*;
import java.util.*;

public class boj_2473 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        long[] amount = new long[n];
        long[] ans = new long[3];
        long max = Long.MAX_VALUE;

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            amount[i] = Long.parseLong(stk.nextToken());
        }

        Arrays.sort(amount); // 오름차순 정렬

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;

            // 이분 탐색
            while (left < right) {
                long sum = amount[i] + amount[left] + amount[right];
                long diff = Math.abs(sum);

                // 절댓값이 더 작은 경우, 정답 갱신
                if (diff < max) {
                    ans[0] = amount[i];
                    ans[1] = amount[left];
                    ans[2] = amount[right];

                    max = diff;
                }

                if (sum > 0) { // 0보다 큰 경우, 양수를 작게 해야 함
                    right--;
                } else { // 0보다 작은 경우, 음수를 크게 해야 함
                    left++;
                }
            }
        }

        // 정답 저장
        for (long i : ans) {
            sb.append(i).append(" ");
        }

        // 정답 출력
        System.out.println(sb);
    }
}
