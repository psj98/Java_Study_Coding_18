package study_240111.problemset;

import java.io.*;
import java.util.*;

public class boj_17835 {
    static class Node implements Comparable<Node> {
        int idx;
        long cost;

        Node(int idx, long cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken()); // 도시 개수
        int m = Integer.parseInt(stk.nextToken()); // 도로 개수
        int k = Integer.parseInt(stk.nextToken()); // 면접장 개수

        // 거리 배열 초기화
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        // 맵 정보 초기화
        ArrayList<Node>[] map = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        // 맵 정보 저장
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            long cost = Long.parseLong(stk.nextToken()); // 비용

            map[to].add(new Node(from, cost)); // 면접장에서부터 거꾸로 가야하기 때문에 반대로 저장
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int idx = Integer.parseInt(stk.nextToken());

            pq.add(new Node(idx, 0)); // 면접장 위치 저장
            dist[idx] = 0;
        }

        // Dijkstra
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.idx] < cur.cost) {
                continue;
            }

            for (Node next : map[cur.idx]) {
                if (dist[next.idx] <= cur.cost + next.cost) {
                    continue;
                }

                dist[next.idx] = cur.cost + next.cost;
                pq.add(new Node(next.idx, dist[next.idx]));
            }
        }

        // 가장 큰 값 찾기
        int ansIdx = 0;
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            if (ans >= dist[i]) {
                continue;
            }

            ansIdx = i;
            ans = dist[i];
        }

        // 정답 출력
        sb.append(ansIdx).append("\n").append(ans);
        System.out.println(sb);
    }
}
