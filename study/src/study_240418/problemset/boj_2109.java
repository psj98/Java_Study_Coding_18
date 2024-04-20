package study_240418.problemset;

import java.io.*;
import java.util.*;

public class boj_2109 {
    static class Lecture implements Comparable<Lecture> {
        int pay, day; // 비용, 일 수

        Lecture(int pay, int day) {
            this.pay = pay;
            this.day = day;
        }

        @Override
        public int compareTo(Lecture o) {
            if (this.pay == o.pay) {
                return o.day - this.day; // 일 수 내림차순
            }

            return o.pay - this.pay; // 비용 내림차순
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 강연 개수

        PriorityQueue<Lecture> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(stk.nextToken()); // 강연료
            int d = Integer.parseInt(stk.nextToken()); // 일 수

            pq.add(new Lecture(p, d));
        }

        boolean[] check = new boolean[10001]; // 해당 일에 강연이 있는지 체크

        int sum = 0; // 비용 합
        while (!pq.isEmpty()) {
            Lecture lecture = pq.poll();

            for (int j = lecture.day; j >= 1; j--) {
                // 강연이 있는 경우, 불가능
                if (check[j]) {
                    continue;
                }

                check[j] = true; // 강연 체크
                sum += lecture.pay; // 비용 합
                break;
            }
        }

        // 정답 출력
        sb.append(sum);
        System.out.println(sb);
    }
}
