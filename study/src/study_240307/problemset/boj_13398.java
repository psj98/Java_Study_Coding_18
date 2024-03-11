package study_240307.problemset;

import java.io.*;
import java.util.*;

public class boj_13398 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        // 배열 수 정보
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        // 오른쪽으로 이동하면서 최댓값 구하기
        int[] dpRight = new int[n];
        dpRight[0] = arr[0];

        int ans = dpRight[0];
        for (int i = 1; i < n; i++) {
            dpRight[i] = Math.max(dpRight[i - 1] + arr[i], arr[i]);
            ans = Math.max(ans, dpRight[i]);
        }

        // 왼쪽으로 이동하면서 최댓값 구하기
        int[] dpLeft = new int[n];
        dpLeft[n - 1] = arr[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            dpLeft[i] = Math.max(dpLeft[i + 1] + arr[i], arr[i]);
        }

        // 현재 값을 제외하고 더했을 때의 최댓값
        for (int i = 1; i < n - 1; i++) {
            ans = Math.max(ans, dpRight[i - 1] + dpLeft[i + 1]);
        }

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }
}
