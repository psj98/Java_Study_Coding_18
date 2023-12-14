package study_231206.problemset;

import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long min = 1, max = (long) times[times.length - 1] * n; // 이분탐색을 위한 최솟값, 최댓값

        Arrays.sort(times); // 시간 순 정렬

        // 이분 ㅏㅁ색
        while (min <= max) {
            long sum = 0;
            long mid = (min + max) / 2;

            for (int time : times) {
                sum += mid / time;
            }

            if (sum >= n) {
                answer = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return answer;
    }
}