package study_230302.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1719 {
    static ArrayList<int[]>[] map; // 맵 정보
    static int[][] ans; // 정답 배열

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 입력
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        // 초기화
        map = new ArrayList[n + 1];
        ans = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++)
            map[i] = new ArrayList<>();

        // 맵에 저장
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken());

            map[start].add(new int[] { end, cost });
            map[end].add(new int[] { start, cost });
        }

        // 각 정점 별로 Dijkstra
        for (int i = 1; i <= n; i++)
            Dijkstra(n, i);

        // 출력
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (ans[i][j] == i)
                    sb.append("- ");
                else
                    sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    // Dijkstra
    static void Dijkstra(int V, int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1]; // 비용 오름차순 정렬
            }
        });
        int[] distance = new int[V + 1]; // 거리
        int[] parents = new int[V + 1]; // 거쳐가야 하는 정점
        boolean[] visited = new boolean[V + 1]; // 방문 배열

        Arrays.fill(distance, Integer.MAX_VALUE); // 최댓값으로 저장
        distance[start] = 0; // 시작 정점 0으로 갱신
        parents[start] = start; // 현재 정점으로는 방문하지 않기 때문에 start로 초기화

        pq.add(new int[] { start, 0 }); // 시작 정점, 비용 저장

        while (!pq.isEmpty()) {
            int cur = pq.poll()[0]; // 현재 정점

            visited[cur] = true; // 방문 체크

            for (int[] next : map[cur]) {
                if (visited[next[0]]) // 방문했으면 방문할 필요 없음
                    continue;

                // 거리 갱신
                if (distance[next[0]] > distance[cur] + next[1]) {
                    distance[next[0]] = distance[cur] + next[1];
                    pq.add(new int[] { next[0], distance[next[0]] });
                    parents[next[0]] = cur; // 거쳐가야 하는 정점 저장
                }
            }
        }

        // ans에 정점 저장
        for (int i = 1; i <= V; i++)
            ans[i][start] = parents[i];
    }
}
