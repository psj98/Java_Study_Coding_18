package study_240321.problemset;

import java.io.*;
import java.util.*;

public class boj_14938 {
    static int n, m;
    static int[] item;
    static ArrayList<int[]>[] map;
    static int[] dist;
    static boolean[] visited;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken()); // 정점 개수
        m = Integer.parseInt(stk.nextToken()); // 최대 비용
        int r = Integer.parseInt(stk.nextToken()); // 간선 개수

        // 초기화
        item = new int[n + 1];
        dist = new int[n + 1];
        visited = new boolean[n + 1];

        map = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        // 아이템 개수 정보
        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            item[i] = Integer.parseInt(stk.nextToken());
        }

        // 맵 정보
        for (int i = 0; i < r; i++) {
            stk = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken());

            map[from].add(new int[] { to, cost });
            map[to].add(new int[] { from, cost });
        }

        // Dijkstra
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dijkstra(i));
        }

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }

    // Dijkstra
    static int dijkstra(int start) {
        // 초기화
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(visited, false);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });
        pq.add(new int[] { start, 0 });
        dist[start] = 0; // 시작 위치 저장

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            // 방문 체크
            if (visited[cur[0]]) {
                continue;
            }

            visited[cur[0]] = true;

            for (int[] next : map[cur[0]]) {
                if (visited[next[0]]) {
                    continue;
                }

                // 다음 정점으로 갈 수 있는 거리가 더 작은 경우
                if (dist[next[0]] > dist[cur[0]] + next[1]) {
                    dist[next[0]] = dist[cur[0]] + next[1];
                    pq.add(new int[] { next[0], dist[next[0]] });
                }
            }
        }

        // 갈 수 있는 최소 거리와 m 비교
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            // 최소 거리 > m => 불가능
            if (dist[i] > m) {
                continue;
            }

            ans += item[i];
        }

        return ans;
    }
}
