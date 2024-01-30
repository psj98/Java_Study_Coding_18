package study_240125.problemset;

import java.io.*;
import java.util.*;

public class boj_21758 {
    static int n;
    static int[] honey;
    static int[] leftSum;
    static int[] rightSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        honey = new int[n];
        leftSum = new int[n]; // 왼쪽 => 오른쪽 누적합
        rightSum = new int[n]; // 오른쪽 => 왼쪽 누적합

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            honey[i] = Integer.parseInt(stk.nextToken());

            if (i == 0) {
                leftSum[i] = honey[i];
                continue;
            }

            leftSum[i] = leftSum[i - 1] + honey[i];
        }

        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                rightSum[i] = honey[i];
                continue;
            }

            rightSum[i] = rightSum[i + 1] + honey[i];
        }

        // 정답 출력
        sb.append(Math.max(case1(), Math.max(case2(), case3())));
        System.out.println(sb);
    }

    // 1. 벌 양쪽, 벌통 위치 찾기
    static int case1() {
        int sum = 0;
        for (int i = 1; i < n - 1; i++) {
            sum = Math.max(sum, leftSum[i] + rightSum[i]);
        }

        return sum - honey[0] - honey[n - 1];
    }

    // 2. 벌통 왼쪽 끝, 벌 오른쪽 끝, 한 마리 위치 찾기
    static int case2() {
        int sum = 0;

        for (int i = 1; i < n - 1; i++) {
            sum = Math.max(sum, rightSum[0] * 2 - rightSum[i] - honey[n - 1] - honey[i]);
        }

        return sum;
    }

    // 3. 벌통 오른쪽 끝, 벌 왼쪽 끝, 한 마리 위치 찾기
    static int case3() {
        int sum = 0;

        for (int i = 1; i < n - 1; i++) {
            sum = Math.max(sum, leftSum[n - 1] * 2 - leftSum[i] - honey[0] - honey[i]);
        }

        return sum;
    }
}
