package study_231213.problemset;

import java.util.*;

class Solution {
    ArrayList<Taxi>[] map; // 맵 정보

    // 목적지, 비용 class
    class Taxi implements Comparable<Taxi> {
        int idx, cost;

        Taxi(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Taxi o) {
            return this.cost - o.cost; // 비용 오름차순 정렬
        }
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        map = new ArrayList[n + 1];

        // 초기화
        for (int i = 0; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        // 맵 정보 저장
        for (int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int cost = fare[2];

            map[from].add(new Taxi(to, cost));
            map[to].add(new Taxi(from, cost));
        }

        int[] together = dijkstra(n, s); // 합승
        int[] startA = dijkstra(n, a); // A에서
        int[] startB = dijkstra(n, b); // B에서

        // 최솟값 구하
        // 어느 한 지점에서 시작, A, B까지의 거리가 최소이면 정답
        for (int i = 0; i <= n; i++) {
            answer = Math.min(together[i] + startA[i] + startB[i], answer);
        }

        return answer;
    }

    // Dijkstra 알고리즘
    public int[] dijkstra(int n, int start) {
        PriorityQueue<Taxi> pq = new PriorityQueue<>();
        int[] dist = new int[n + 1];

        // 초기화
        pq.add(new Taxi(start, 0));
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[start] = 0; // 시작 지점 0

        while (!pq.isEmpty()) {
            Taxi cur = pq.poll();

            for (Taxi next : map[cur.idx]) {
                if (dist[next.idx] <= dist[cur.idx] + next.cost) {
                    continue;
                }

                dist[next.idx] = dist[cur.idx] + next.cost;
                pq.add(new Taxi(next.idx, dist[next.idx]));
            }
        }

        return dist;
    }
}