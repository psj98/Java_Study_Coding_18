package study_240425.problemset;

import java.io.*;
import java.util.*;

public class boj_1647 {
    static int n, m;
    static ArrayList<int[]>[] cities;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken()); // 집 개수
        m = Integer.parseInt(stk.nextToken()); // 도로 개수

        // 초기화
        cities = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            cities[i] = new ArrayList<>();
        }

        // 도로 저장
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken());

            cities[from].add(new int[] { to, cost });
            cities[to].add(new int[] { from, cost });
        }

        // 정답 출력
        sb.append(kruskal());
        System.out.println(sb);
    }

    // 크루스칼 알고리즘
    static int kruskal() {
        int sum = 0, maxCost = 0; // 전체 합, 도로 비용 중 최댓값
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1]; // 비용 오름차순 정렬
        });

        pq.add(new int[] { 1, 0 }); // 초기값

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            // 방문 체크
            if (visited[cur[0]]) {
                continue;
            }

            visited[cur[0]] = true; // 방문
            sum += cur[1]; // 도로 값 저장
            maxCost = Math.max(maxCost, cur[1]); // 최댓값 갱신

            for (int[] next : cities[cur[0]]) {
                // 방문체크
                if (visited[next[0]]) {
                    continue;
                }

                pq.add(next); // 다음에 갈 도시 저장
            }
        }

        return sum - maxCost; // 전체 합 중 최댓값을 뺀 값 반환
    }
}
