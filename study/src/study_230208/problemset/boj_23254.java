package study_230208.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_23254 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Pair> pq = new PriorityQueue<>(); // Pair(점수, 시간 별 증가 점수)

        int n = Integer.parseInt(stk.nextToken()) * 24; // 시간
        int m = Integer.parseInt(stk.nextToken()); // 과목 개수
        int sum = 0; // 정답

        int[] scores = new int[m]; // 점수 배열

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++)
            scores[i] = Integer.parseInt(stk.nextToken());

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++)
            pq.add(new Pair(scores[i], Integer.parseInt(stk.nextToken())));

        // 각 시간 별로 체크
        for (int i = 0; i < n; i++) {
            // 맨 앞의 값이 100이면 전체의 값이 100이므로 더 이상 계산할 필요 없음
            if (pq.peek().first == 100)
                break;

            // score 계산
            // 원점수 + min(100-원점수, 점수 증가 값)
            int scoreUp = pq.peek().second; // 시간 별 점수 증가
            int score = pq.peek().first + Math.min(100 - pq.peek().first, scoreUp);
            pq.poll();
            pq.add(new Pair(score, scoreUp));
        }

        while (!pq.isEmpty()) {
            sum += pq.peek().first;
            pq.poll();
        }

        sb.append(sum);
        System.out.println(sb);
    }

    public static class Pair implements Comparable<Pair> {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        // 점수를 증가할 수 있는 값이 큰 순으로 정렬
        @Override
        public int compareTo(Pair a) {
            // 1 교환 / -1 교환 X
            return Math.min(100 - this.first, this.second) < Math.min(100 - a.first, a.second) ? 1 : -1;
        }
    }

}
