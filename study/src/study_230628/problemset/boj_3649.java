package study_230628.problemset;

import java.io.*;
import java.util.*;

public class boj_3649 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        /**
         * 구멍에 맞는 두 조각을 찾는 문제
         * => 정렬 후, 이분 탐색
         */

        String str = null;
        while ((str = br.readLine()) != null) { // 테스트케이스가 없으면 종료
            int hole = Integer.parseInt(str) * 10000000; // 구멍 크기 -> 나노미터로 변경
            int n = Integer.parseInt(br.readLine()); // 레고 조각 개수

            // 레고 조각 정보
            int[] lego = new int[n];
            for (int i = 0; i < n; i++) {
                lego[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(lego); // 오름차순 정렬

            // 이분 탐색
            int left = 0, right = n - 1;
            boolean check = false;
            while (left < right) {
                int sum = lego[left] + lego[right];
                if (sum == hole) { // 같으면
                    check = true;
                    break;
                } else if (sum < hole) { // 구멍이 더 크면, 왼쪽의 레고 조각을 큰 조각으로 변경
                    left++;
                } else { // 합이 더 크면, 오른쪽의 레고 조각을 작은 조각으로 변경
                    right--;
                }
            }

            // 정답 출력
            if (check)
                sb.append("yes ").append(lego[left]).append(" ").append(lego[right]).append("\n");
            else
                sb.append("danger\n");
        }

        System.out.println(sb);
    }
}
