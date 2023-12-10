package study_231129.problemset;

import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;

        // 오름차순 정렬
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int pos = Integer.MIN_VALUE; // 카메라 위치

        for (int[] route : routes) {
            // 시작 지점보다 큰 경우, 스킵
            if (pos >= route[0]) {
                continue;
            }

            pos = route[1];
            answer++;
        }

        return answer;
    }
}