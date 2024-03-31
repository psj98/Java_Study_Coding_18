package study_240328.problemset;

import java.io.*;
import java.util.*;

public class boj_2461 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken()); // 학급
        int m = Integer.parseInt(stk.nextToken()); // 학급의 학생 수

        int[][] school = new int[n][m];

        // 학생 정보
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                school[i][j] = Integer.parseInt(stk.nextToken());
            }

            Arrays.sort(school[i]); // 오름차순 정렬
        }

        int ans = Integer.MAX_VALUE; // 정답
        int[] idx = new int[n]; // 각 학급의 idx

        // 투 포인터
        while (true) {
            int minIdx = 0; // 최솟값이 위치한 학급 idx
            int min = school[0][idx[0]], max = school[0][idx[0]]; // 최솟값, 최댓값

            for (int i = 1; i < n; i++) {
                // 최댓값 찾기
                if (school[i][idx[i]] > max) {
                    max = school[i][idx[i]];
                }

                // 최솟값 찾기
                if (school[i][idx[i]] < min) {
                    min = school[i][idx[i]];
                    minIdx = i; // 최솟값의 idx 갱신
                }
            }

            ans = Math.min(ans, max - min); // 최솟값 갱신

            // m을 넘어가면 종료
            if (idx[minIdx] + 1 == m) {
                break;
            }

            idx[minIdx]++; // idx 증가
        }

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }
}
