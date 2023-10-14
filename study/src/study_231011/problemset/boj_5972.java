package study_231011.problemset;

import java.io.*;
import java.util.*;

public class boj_5972 {
    static int n, m;
    static ArrayList<Node>[] list;
    static int[] dist;
    static boolean[] visited;

    static class Node implements Comparable<Node> {
        int to, cost; // 도착 지점, 비용

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost; // 비용 오름차순 정렬
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        // 초기화
        dist = new int[n + 1];
        visited = new boolean[n + 1];
        list = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        Arrays.fill(dist, Integer.MAX_VALUE); // 거리 배열 초기화

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken());

            // 시작 지점, 도착 지점, 비용 저장
            list[from].add(new Node(to, cost));
            list[to].add(new Node(from, cost));
        }

        diskstra(); // 다익스트라 알고리즘

        // 정답 출력
        sb.append(dist[n]);
        System.out.println(sb);
    }

    // 다익스트라
    static void diskstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        dist[1] = 0; // 시작 지점 비용

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.to]) {
                continue;
            }

            visited[cur.to] = true;

            for (Node next : list[cur.to]) {
                if (dist[next.to] > dist[cur.to] + next.cost) {
                    dist[next.to] = dist[cur.to] + next.cost;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }
        }
    }
}
