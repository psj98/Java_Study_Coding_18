package study_230830.problemset;

import java.io.*;
import java.util.*;

public class boj_10282 {
    static ArrayList<Computer>[] list;
    static int[] dist;
    static boolean[] visited;

    // Computer 클래스
    static class Computer implements Comparable<Computer> {
        int idx, time;

        Computer(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }

        @Override
        public int compareTo(Computer o) {
            return this.time - o.time; // 시간 오름차순 정렬 => 빠른 것부터 계산
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            stk = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(stk.nextToken());
            int d = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());

            // 초기화
            dist = new int[n + 1];
            visited = new boolean[n + 1];
            list = new ArrayList[n + 1];

            for (int i = 0; i <= n; i++) {
                list[i] = new ArrayList<>();
                dist[i] = Integer.MAX_VALUE;
            }

            // 정보 입력
            for (int i = 0; i < d; i++) {
                stk = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(stk.nextToken());
                int to = Integer.parseInt(stk.nextToken());
                int time = Integer.parseInt(stk.nextToken());

                list[to].add(new Computer(from, time));
            }

            dijkstra(c); // 다익스트라

            // 감염된 개수, 최대 시간 구하기
            int num = 0, total = 0;
            for (int i = 1; i <= n; i++) {
                if (dist[i] == Integer.MAX_VALUE)
                    continue;

                num++;
                total = Math.max(total, dist[i]);
            }

            sb.append(num + " " + total).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    // 다익스트라
    static void dijkstra(int start) {
        PriorityQueue<Computer> pq = new PriorityQueue<>();
        pq.add(new Computer(start, 0));

        dist[start] = 0;

        while (!pq.isEmpty()) {
            Computer cur = pq.poll();

            if (visited[cur.idx]) {
                continue;
            }

            visited[cur.idx] = true;

            for (Computer next : list[cur.idx]) {
                if (dist[next.idx] > dist[cur.idx] + next.time) {
                    dist[next.idx] = dist[cur.idx] + next.time;
                    pq.add(new Computer(next.idx, dist[next.idx]));
                }
            }
        }
    }
}
