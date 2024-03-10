package study_240307.problemset;

import java.io.*;
import java.util.*;

public class boj_1939 {
    static int N, from, to;
    static ArrayList<Island>[] list;
    static boolean[] visited;

    static class Island {
        int to, cost;

        Island(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());

        // 초기화
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        int left = Integer.MAX_VALUE, right = 0; // 이분탐색을 위한 값

        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());

            int island1 = Integer.parseInt(stk.nextToken()); // 섬 정보
            int island2 = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken()); // 중량

            // 섬 정보 저장
            list[island2].add(new Island(island1, cost));
            list[island1].add(new Island(island2, cost));

            // 최댓값, 최솟값 갱신
            left = Math.min(left, cost);
            right = Math.max(right, cost);
        }

        stk = new StringTokenizer(br.readLine());

        from = Integer.parseInt(stk.nextToken()); // 이동해야 하는 섬 정보
        to = Integer.parseInt(stk.nextToken());

        // 이분 탐색
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            // BFS : 건널 수 있는 경우 left 값 증가 / 건널 수 없는 경우 right 값 감소
            if (bfs(mid)) {
                left = mid + 1;
                ans = mid;
            } else {
                right = mid - 1;
            }
        }

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }

    // BFS
    static boolean bfs(int num) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        queue.add(from);
        visited[from] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            // 도착한 경우, 종료
            if (cur == to) {
                return true;
            }

            for (Island next : list[cur]) {
                // 방문 체크
                if (visited[next.to]) {
                    continue;
                }

                // 중량 제한보다 값이 큰 경우, 스킵
                if (next.cost < num) {
                    continue;
                }

                visited[next.to] = true;
                queue.add(next.to);
            }
        }

        return false;
    }
}
