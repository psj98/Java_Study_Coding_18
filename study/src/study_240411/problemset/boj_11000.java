package study_240411.problemset;

import java.io.*;
import java.util.*;

public class boj_11000 {

    static class Lecture implements Comparable<Lecture> {
        int start, end;

        Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lecture o) {
            if (this.start == o.start) {
                return this.end - o.end; // 종료 시간 오름차순
            }

            return this.start - o.start; // 시작 시간 오름차순
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        Lecture[] lectures = new Lecture[n]; // 강의
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(stk.nextToken()); // 시작 시간
            int end = Integer.parseInt(stk.nextToken()); // 종료 시간

            lectures[i] = new Lecture(start, end); // 강의 저장
        }

        Arrays.sort(lectures); // 강의 정렬

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(lectures[0].end); // 종료 시간 기준으로 저장

        for (int i = 1; i < n; i++) {
            // 다음 강의가 가능하다면, 앞 강의를 제거
            if (pq.peek() <= lectures[i].start) {
                pq.poll();
            }

            pq.add(lectures[i].end); // 새로운 강의 추가
        }

        // 정답 출력
        sb.append(pq.size());
        System.out.println(sb);
    }
}
