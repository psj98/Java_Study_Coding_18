package study_231025.problemset;

import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 오름차순 정렬

        // pq에 값 저장
        for (int work : works) {
            pq.add(work);
        }

        // n이 0이 될 때까지 1씩 빼면서 반복
        while (n-- != 0) {
            int cur = pq.poll();

            // 0이면 종료
            if (cur <= 0) {
                break;
            }

            pq.offer(cur - 1); // 1 뺀 값 저장
        }

        // 제곱 계산
        long sum = 0;
        while (!pq.isEmpty()) {
            sum += Math.pow(pq.poll(), 2);
        }

        return sum;
    }
}