package study_230628.problemset;

import java.io.*;
import java.util.*;

public class boj_9370 {
    static int n, m, t, s, g, h;
    static ArrayList<int[]>[] list;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int testcase = Integer.parseInt(br.readLine()); // testcase
        for (int tc = 0; tc < testcase; tc++) {
            stk = new StringTokenizer(br.readLine());
            n = Integer.parseInt(stk.nextToken()); // 교차로
            m = Integer.parseInt(stk.nextToken()); // 도로
            t = Integer.parseInt(stk.nextToken()); // 목적지 후보 개수

            // 리스트 초기화
            list = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) {
                list[i] = new ArrayList<>();
            }

            stk = new StringTokenizer(br.readLine());
            s = Integer.parseInt(stk.nextToken()); // 출발지
            g = Integer.parseInt(stk.nextToken()); // 지나가야하는 도로
            h = Integer.parseInt(stk.nextToken()); // 지나가야하는 도로

            // 노드 간 거리
            int gh = 0; // g <-> h 비용
            for (int i = 0; i < m; i++) {
                stk = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(stk.nextToken());
                int end = Integer.parseInt(stk.nextToken());
                int cost = Integer.parseInt(stk.nextToken());

                if ((start == g && end == h) || (start == h && end == g)) {
                    gh = cost;
                }

                list[start].add(new int[] { end, cost });
                list[end].add(new int[] { start, cost });
            }

            // 목적지 목록
            int[] destinations = new int[t];
            for (int i = 0; i < t; i++) {
                destinations[i] = Integer.parseInt(br.readLine());
            }

            int sg = dijkstra(s, g); // s -> g 거리
            int sh = dijkstra(s, h); // s -> h 거리

            // s-g-h-d or s-h-g-d
            // Dijkstra
            ArrayList<Integer> ans = new ArrayList<>();
            for (int dest : destinations) {
                int val1 = sg + gh + dijkstra(h, dest);
                int val2 = sh + gh + dijkstra(g, dest);
                int val3 = dijkstra(s, dest);

                // val1, val2의 최솟값이 val3인 경우 ans에 추가
                if (Math.min(val1, val2) == val3) {
                    ans.add(dest);
                }
            }

            Collections.sort(ans); // 오름차순 정렬

            // 정답 출력
            for (int i : ans)
                sb.append(i).append(" ");
            sb.append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    // 다익스트라
    static int dijkstra(int start, int end) {
        boolean[] check = new boolean[n + 1];
        int[] dist = new int[n + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        pq.add(new int[] { start, 0 });
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (check[cur[0]])
                continue;
            check[cur[0]] = true;

            for (int[] next : list[cur[0]]) {
                if (dist[next[0]] <= dist[cur[0]] + next[1])
                    continue;
                dist[next[0]] = dist[cur[0]] + next[1];
                pq.add(new int[] { next[0], dist[next[0]] });
            }
        }

        return dist[end];
    }
}