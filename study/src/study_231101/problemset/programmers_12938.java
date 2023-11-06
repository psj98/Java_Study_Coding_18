package study_231101.problemset;

import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];

        // n이 s보다 큰 경우, -1 리턴
        if (n > s) {
            return new int[] { -1 };
        }

        // 정가운데 값 구하기
        for (int i = 0; i < n; i++) {
            answer[i] = s / n;
        }

        // 남은 개수만큼 +1
        for (int i = 0; i < s % n; i++) {
            answer[i]++;
        }

        Arrays.sort(answer); // 오름차순 정렬

        return answer;
    }
}