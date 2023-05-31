package study_230531.problemset;

import java.io.*;
import java.util.*;

public class boj_11779 {
    static int[] dist, preCity;
    static ArrayList<Node>[] graph;

    static class Node implements Comparable<Node> {
        int idx, cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        /* 비용 오름차순 정렬 */
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 도시의 개수
        int m = Integer.parseInt(br.readLine()); // 버스의 개수

        dist = new int[n + 1];
        preCity = new int[n + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        // 그래프 초기화
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 버스 정보 - 출발 도시 번호, 도착 도시 번호, 버스 비용
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken());

            graph[start].add(new Node(end, cost));
        }

        // 출발점 도시 번호, 도착점 도시 번호
        stk = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(stk.nextToken());
        int end = Integer.parseInt(stk.nextToken());

        dijkstra(n, start); // 다익스트라

        sb.append(dist[end]).append("\n");

        // 방문한 도시 개수, 도시 번호 구하기
        int cnt = 1;
        Stack<Integer> stack = new Stack<>();
        stack.add(end);

        // 마지막에 방문한 도시부터 거꾸로 Stack에 저장
        while (end != start) {
            cnt++;
            stack.push(preCity[end]);
            end = preCity[end];
        }

        sb.append(cnt).append("\n"); // 개수 출력

        // Stack에서 거꾸로 출력
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        // 정답 출력
        System.out.println(sb);
    }

    // 다익스트라 알고리즘
    static void dijkstra(int n, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        boolean[] visited = new boolean[n + 1];
        dist[start] = 0; // 시작 좌표에 대한 비용

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            // 방문 체크
            if (visited[cur.idx])
                continue;

            visited[cur.idx] = true;

            // 방문할 수 있는 도시에 대해 최솟값 갱신
            for (Node next : graph[cur.idx]) {
                if (dist[next.idx] <= dist[cur.idx] + next.cost)
                    continue;

                dist[next.idx] = dist[cur.idx] + next.cost;
                preCity[next.idx] = cur.idx; // 이전에 방문한 마을 기록
                pq.add(new Node(next.idx, dist[next.idx]));
            }
        }
    }
}
