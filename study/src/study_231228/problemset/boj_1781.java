package study_231228.problemset;

import java.io.*;
import java.util.*;

public class boj_1781 {
    static class Ramen implements Comparable<Ramen> {
        int deadline, cnt; // 데드라인, 컵라면 개수

        Ramen(int deadline, int cnt) {
            this.deadline = deadline;
            this.cnt = cnt;
        }

        // 데드라인 오름차순, 컵라면 개수 내림차순
        @Override
        public int compareTo(Ramen o) {
            // 데드라인이 같으면, 컵라면 개수 내림차순
            if (this.deadline == o.deadline) {
                return o.cnt - this.cnt;
            }

            return this.deadline - o.deadline; // 데드라인 오름차순
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Ramen> pq = new PriorityQueue<>();
        PriorityQueue<Integer> ramenCnt = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());

            int deadline = Integer.parseInt(stk.nextToken()); // 데드라인
            int cnt = Integer.parseInt(stk.nextToken()); // 컵라면 개수

            pq.add(new Ramen(deadline, cnt));
        }

        while (!pq.isEmpty()) {
            Ramen cur = pq.poll();
            ramenCnt.add(cur.cnt);

            // 데드라인 내이면 스킵
            if (cur.deadline >= ramenCnt.size()) {
                continue;
            }

            // 데드라인을 벗어난 경우, 제거
            ramenCnt.poll();
        }

        int sum = 0; // 컵라면 합계
        while (!ramenCnt.isEmpty()) {
            sum += ramenCnt.poll();
        }

        // 정답 출력
        sb.append(sum);
        System.out.println(sb);
    }
}
