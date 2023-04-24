package study_230419.problemset;

import java.io.*;
import java.util.*;

public class boj_13904 {
    static class Homework implements Comparable<Homework> {
        int days, score;

        Homework(int days, int score) {
            this.days = days;
            this.score = score;
        }

        @Override
        public int compareTo(Homework o) { // 내림차순 정렬
            return o.days - this.days;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 점수 내림차순 정렬
        PriorityQueue<Homework> homeworks = new PriorityQueue<>(); // 과제 목록

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int days = Integer.parseInt(stk.nextToken());
            int score = Integer.parseInt(stk.nextToken());

            homeworks.add(new Homework(days, score)); // 과제 정보 저장
        }

        int maxDays = homeworks.peek().days; // 최대 일수
        int ans = 0;

        // 최대 일수부터 거꾸로 -> 해당 일수에 과제 점수를 넣고 최댓값을 빼서 ans에 더함
        while (maxDays != 0) {
            while (!homeworks.isEmpty()) {
                // 최대 일수가 아닌 경우, break
                if (homeworks.peek().days != maxDays)
                    break;

                pq.add(homeworks.poll().score); // 점수 저장
            }

            // 점수가 있는 경우, ans에 최댓값 더함
            if (!pq.isEmpty())
                ans += pq.poll();

            maxDays--; // 일수 감소
        }

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }
}
