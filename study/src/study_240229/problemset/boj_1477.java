package study_240229.problemset;

import java.io.*;
import java.util.*;

public class boj_1477 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(stk.nextToken()); // 현재 휴게소 개수
        int M = Integer.parseInt(stk.nextToken()); // 추가로 세울 휴개소 개수
        int L = Integer.parseInt(stk.nextToken()); // 고속도로 길이

        // 휴게소 위치 배열
        int[] arr = new int[N + 2];
        arr[0] = 0;
        arr[N + 1] = L;

        // 휴게소 위치 저장
        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        Arrays.sort(arr); // 오름차순 정렬

        // 이분 탐색 => mid를 구하고, mid로 각 간격을 나눴을 때 M개가 들어가는 경우를 찾음
        int left = 1, right = L - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = 0; // 개수

            // 개수 세기
            for (int i = 1; i < N + 2; i++) {
                cnt += (arr[i] - arr[i - 1] - 1) / mid;
            }

            if (cnt > M) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // 정답 출력
        sb.append(left);
        System.out.println(sb);
    }
}
