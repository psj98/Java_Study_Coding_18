package study_230302;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1238 {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        ArrayList<int[]>[] homeMap, partyMap; // 집으로, 파티로
        int[] tohome, toparty; // 집으로 최단 경로, 파티로 최단 경로
        int ans = 0; // 정답

        // 입력
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int x = Integer.parseInt(stk.nextToken());

        // 초기화
        homeMap = new ArrayList[n + 1];
        partyMap = new ArrayList[n + 1];
        tohome = new int[n + 1];
        toparty = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            homeMap[i] = new ArrayList<>();
            partyMap[i] = new ArrayList<>();
        }

        Arrays.fill(tohome, Integer.MAX_VALUE);
        Arrays.fill(toparty, Integer.MAX_VALUE);

        // 맵 정보 저장
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken());
            homeMap[start].add(new int[] { end, cost }); // start -> end
            partyMap[end].add(new int[] { start, cost }); // end -> start
        }

        // 다익스트라 알고리즘
        tohome = Dijkstra(homeMap, n, x, tohome);
        toparty = Dijkstra(partyMap, n, x, toparty);

        // 최솟값 구하기
        for (int i = 1; i <= n; i++)
            ans = Math.max(ans, tohome[i] + toparty[i]);

        sb.append(ans);
        System.out.println(sb);
    }

    // 다익스트라 알고리즘
    static int[] Dijkstra(ArrayList<int[]>[] map, int V, int start, int[] distance) {
        boolean[] visited = new boolean[V + 1]; // 방문 배열
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1]; // 비용 순 정렬
            }
        });

        pq.add(new int[] { start, 0 }); // 시작 정점, 비용 0
        distance[start] = 0; // 방문 체크

        while (!pq.isEmpty()) {
            int node = pq.poll()[0]; // 현재 정점
            visited[node] = true; // 방문 체크

            for (int[] next : map[node]) {
                if (visited[next[0]]) // 방문했으면 방문할 필요 없음
                    continue;

                // 최단 거리
                if (distance[next[0]] > distance[node] + next[1]) {
                    distance[next[0]] = distance[node] + next[1];
                    pq.offer(new int[] { next[0], distance[next[0]] });
                }
            }
        }

        return distance;
    }
}
