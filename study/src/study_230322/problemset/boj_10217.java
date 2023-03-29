package study_230322.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_10217 {
    static int n, m, k;
    static ArrayList<KCM>[] map;
    static int[][] dp;

    static class KCM implements Comparable<KCM> {
        int end, cost, time; // 현재 노드, 비용, 시간

        KCM(int end, int cost, int time) {
            this.end = end;
            this.cost = cost;
            this.time = time;
        }

        @Override
        public int compareTo(KCM o) { // 시간 오름차순 정렬
            if (this.time == o.time)
                return this.cost - o.cost;
            return this.time - o.time;
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            stk = new StringTokenizer(br.readLine());
            n = Integer.parseInt(stk.nextToken());
            m = Integer.parseInt(stk.nextToken());
            k = Integer.parseInt(stk.nextToken());

            // 초기화
            map = new ArrayList[n + 1];
            dp = new int[n + 1][m + 1]; // 노드와 값에 따른 시간
            for (int i = 0; i <= n; i++) {
                map[i] = new ArrayList<>();
                Arrays.fill(dp[i], Integer.MAX_VALUE); // max값으로 초기화
            }

            // 시작, 도착, 비용, 시간 입력
            for (int i = 0; i < k; i++) {
                stk = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(stk.nextToken());
                int end = Integer.parseInt(stk.nextToken());
                int cost = Integer.parseInt(stk.nextToken());
                int time = Integer.parseInt(stk.nextToken());

                map[start].add(new KCM(end, cost, time));
            }

            Dijkstra(); // Dijkstra

            int ans = Integer.MAX_VALUE;
            for (int i = 0; i <= m; i++)
                ans = Math.min(ans, dp[n][i]); // 최솟값 찾기

            // 정답 출력
            if (ans == Integer.MAX_VALUE)
                sb.append("Poor KCM\n");
            else
                sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

    // 다익스트라
    static void Dijkstra() {
        PriorityQueue<KCM> pq = new PriorityQueue<>();
        pq.add(new KCM(1, 0, 0)); // 시작 위치 (1번 노드, 비용 0, 시간 0)

        while (!pq.isEmpty()) {
            KCM cur = pq.poll(); // 현재 KCM

            if (cur.end == n) // 종료
                break;

            if (dp[cur.end][cur.cost] < cur.time) // 시간 갱신
                continue;
            dp[cur.end][cur.cost] = cur.time; // 현재 시간 저장

            for (KCM next : map[cur.end]) {
                int nextNode = next.end;
                int nextCost = cur.cost + next.cost;
                int nextTime = cur.time + next.time;

                if (nextCost > m) // 다음 비용이 m보다 크면 갈 필요 없음
                    continue;

                // 다음에 갈 곳의 시간이 적으면 갱신 -> pq에 저장
                if (dp[nextNode][nextCost] > nextTime) {
                    dp[nextNode][nextCost] = nextTime;
                    pq.add(new KCM(nextNode, nextCost, nextTime));
                }
            }
        }
    }
}

