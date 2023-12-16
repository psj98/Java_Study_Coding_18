package study_231206.problemset;

import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];

        // map 정보 초기화
        ArrayList<Integer>[] map = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        // map 정보 저장 (양방향)
        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];

            map[from].add(to);
            map[to].add(from);
        }

        // 거리 초기화
        int[] distance = new int[n + 1];
        Arrays.fill(distance, -1);
        distance[destination] = 0;

        // 시작 위치 저장
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { destination, 0 });

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int next : map[cur[0]]) {
                // 이미 거리가 저장되어 있는 경우, 스킵
                if (distance[next] >= 0) {
                    continue;
                }

                // 거리 저장
                distance[next] = cur[1] + 1;
                queue.add(new int[] { next, cur[1] + 1 }); // 이후에 갈 곳 저장
            }
        }

        // 정답 저장
        for (int i = 0; i < sources.length; i++) {
            answer[i] = distance[sources[i]];
        }

        return answer;
    }
}