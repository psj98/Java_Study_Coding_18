package study_231206.problemset;

import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]); // 처리 시간 오름차순 정렬
        int idx = 0, cnt = 0, end = 0, answer = 0;

        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]); // 요청시간 오름차순 정렬

        while (cnt < jobs.length) {
            // 작업 시간까지 pq에 저장
            while (idx < jobs.length && jobs[idx][0] <= end) {
                pq.add(jobs[idx++]);
            }

            // 작업이 없는 경우, end 갱신
            if (pq.isEmpty()) {
                end = jobs[idx][0];
                continue;
            }

            // 작업까지 완료한 값 저장
            int[] cur = pq.poll();

            answer += end + cur[1] - cur[0];
            end += cur[1];
            cnt++;
        }

        return (int) Math.floor(answer / jobs.length);
    }
}