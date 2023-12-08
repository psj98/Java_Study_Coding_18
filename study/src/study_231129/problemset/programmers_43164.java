package study_231129.problemset;

import java.util.*;

class Solution {
    ArrayList<Integer>[] map;

    public int solution(int n, int[][] edge) {
        // 초기화
        map = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        // 노드 저장
        for (int[] cur : edge) {
            int from = cur[0];
            int to = cur[1];

            map[from].add(to);
            map[to].add(from);
        }

        return bfs(n); // BFS
    }

    // BFS
    public int bfs(int n) {
        int answer = 0, max = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];

        queue.add(new int[] { 1, 0 });
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            // 같은 깊이 => 정답 증가
            if (cur[1] == max) {
                answer++;
            } else if (cur[1] > max) { // 작은 깊이 => 1로 초기화
                max = cur[1];
                answer = 1;
            }

            for (int next : map[cur[0]]) {
                // 방문 체크
                if (visited[next]) {
                    continue;
                }

                queue.add(new int[] { next, cur[1] + 1 });
                visited[next] = true;
            }
        }

        return answer;
    }
}