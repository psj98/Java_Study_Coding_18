package study_240321.problemset;

import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class boj_1707 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int t = 0; t < tc; t++) {
            stk = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(stk.nextToken()); // 정점 개수
            int E = Integer.parseInt(stk.nextToken()); // 간선 개수

            // 초기화
            ArrayList<Integer>[] map = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) {
                map[i] = new ArrayList<>();
            }

            // 맵 정보
            for (int i = 0; i < E; i++) {
                stk = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(stk.nextToken());
                int to = Integer.parseInt(stk.nextToken());

                map[from].add(to);
                map[to].add(from);
            }

            // 정답 저장
            sb.append(bfs(map, V) ? "YES\n" : "NO\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    static boolean bfs(ArrayList<Integer>[] map, int V) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int[] check = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            // 아직 방문하지 않은 정점
            if (check[i] == 0) {
                check[i] = 1; // 1번으로 색칠
                queue.add(i);
            }

            while (!queue.isEmpty()) {
                int cur = queue.poll();

                for (int next : map[cur]) {
                    // 이미 방문했고, 같은 색인 경우
                    if (check[next] == check[cur]) {
                        return false; // 실패
                    }

                    // 방문하지 않은 경우
                    if (check[next] == 0) {
                        check[next] = check[cur] * -1; // 다른 색으로 색칠
                        queue.add(next);
                    }
                }
            }
        }

        return true;
    }
}
