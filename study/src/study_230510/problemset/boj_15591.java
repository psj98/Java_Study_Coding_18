package study_230510.problemset;

import java.io.*;
import java.util.*;

public class boj_15591 {
    static class Node {
        int to, cost; // 정점, 비용

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        /*
         * 1. 그래프에 정보 저장 ([시작 정점] -> {도착 정점, 비용})
         * 2. BFS
         *  2-1. 방문 체크
         *  2-2. k값 체크
         *  2-3. 추천 동영상 개수 저장
         * 3. 정답 출력
         */

        int n = Integer.parseInt(stk.nextToken()); // 정점 개수
        int m = Integer.parseInt(stk.nextToken()); // 질문 개수

        // 그래프 초기화
        ArrayList<Node>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        // 정점 개수 - 1 만큼 반복
        for (int i = 0; i < n - 1; i++) {
            stk = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken());

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        // 질문
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(stk.nextToken()); // 추천할 동영상의 k값
            int v = Integer.parseInt(stk.nextToken()); // 정점

            /* BFS */
            int cnt = 0; // 추천 동영상 개수
            boolean[] visited = new boolean[n + 1]; // 방문 체크 배열
            Queue<Integer> queue = new ArrayDeque<>();

            queue.add(v);
            visited[v] = true;

            while (!queue.isEmpty()) {
                int cur = queue.poll();

                for (Node next : graph[cur]) {
                    if (visited[next.to]) // 방문 체크
                        continue;

                    if (next.cost < k) // 추천 동영상 값 체크
                        continue;

                    queue.add(next.to);
                    visited[next.to] = true;
                    cnt++;
                }
            }

            // 정답 출력
            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}
