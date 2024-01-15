package study_240111.problemset;

import java.io.*;
import java.util.*;

public class boj_15971 {
    static int n;
    static ArrayList<Node> map[];

    static class Node {
        int idx, cost; // 위치, 비용

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken()); // 방 개수
        int one = Integer.parseInt(stk.nextToken()); // 1번 로봇이 위치한 방 번호
        int two = Integer.parseInt(stk.nextToken()); // 2번 로봇이 위치한 방 번호

        // 초기화
        map = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        // 맵 정보 저장
        for (int i = 0; i < n - 1; i++) {
            stk = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken());

            map[from].add(new Node(to, cost));
            map[to].add(new Node(from, cost));
        }

        // 정답 출력
        sb.append(bfs(one, two));
        System.out.println(sb);
    }

    // BFS => two까지 이동하면서 최댓값과 비용 합을 구함
    // 정답 : 비용 합 - 최댓값
    static int bfs(int one, int two) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];

        queue.add(new int[] { one, 0, 0 }); // 현 위치, 비용, 최댓값
        visited[one] = true; // 방문 체크

        int ans = 0; // 정답

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            // 로봇 2의 위치로 간 경우
            if (cur[0] == two) {
                ans = cur[1] - cur[2]; // 비용 합 - 최댓값
                break;
            }

            for (Node node : map[cur[0]]) {
                // 방문한 경우, 스킵
                if (visited[node.idx]) {
                    continue;
                }

                visited[node.idx] = true; // 방문 체크
                queue.add(new int[] { node.idx, cur[1] + node.cost, Math.max(cur[2], node.cost) });
            }
        }

        return ans;
    }
}
