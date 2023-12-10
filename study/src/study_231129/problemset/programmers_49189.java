package study_231129.problemset;

import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int[] wh = scores[0];
        int answer = 1, maxScore = 0, whSum = wh[0] + wh[1];

        // 태도 점수 내림차순, 동료 평가 점수 오름차순 정렬
        Arrays.sort(scores, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);

        for (int[] score : scores) {
            // 동료 평가 점수가 높으면 종류
            if (score[1] < maxScore) {
                if (score.equals(wh)) {
                    return -1;
                }
            } else {
                maxScore = Math.max(maxScore, score[1]);

                if (score[0] + score[1] > whSum) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
