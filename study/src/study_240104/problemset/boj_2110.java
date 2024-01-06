package study_240104.problemset;

import java.io.*;
import java.util.*;

public class boj_2110 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken()); // 집 개수
        int c = Integer.parseInt(stk.nextToken()); // 설치해야 하는 공유기 개수

        // 집 위치 정보
        int[] house = new int[n];
        for (int i = 0; i < n; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house); // 오름차순 정렬

        // 이분 탐색
        int left = 1, right = house[n - 1] - house[0] + 1;
        while (left < right) {
            int mid = (left + right) / 2;

            int cnt = 1; // 첫 번째 집 무조건 설치
            int last = house[0]; // 마지막에 설치한 집 위치

            for (int i = 1; i < house.length; i++) {
                int cur = house[i]; // 현재 집 위치

                // 최소 거리보다 작은 경우, 스킵
                if (cur - last < mid) {
                    continue;
                }

                cnt++; // 설치 개수 증가
                last = cur; // 마지막에 설치한 집 위치 갱신
            }

            if (cnt < c) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        // 정답 출력
        sb.append(left - 1);
        System.out.println(sb);
    }
}
