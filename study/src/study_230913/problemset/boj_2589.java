package study_230913.problemset;

import java.io.*;
import java.util.*;

public class boj_2589 {
    static int n, m, ans = 0;
    static char[][] map;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        // 초기화 및 저장
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // 모든 육지에 대해 계산
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'W') {
                    continue;
                }

                bfs(i, j);
            }
        }

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }

    // BFS
    static void bfs(int x, int y) {
        int cnt = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        queue.add(new int[] { x, y, 0 }); // 좌표, 이동 횟수
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                // 바다 체크
                if (map[nx][ny] == 'W') {
                    continue;
                }

                // 방문 체크
                if (visited[nx][ny]) {
                    continue;
                }

                queue.add(new int[] { nx, ny, cur[2] + 1 });
                visited[nx][ny] = true;
                ans = Integer.max(ans, cur[2] + 1);
            }
        }

        ans = Integer.max(ans, cnt); // 최댓값 갱신
    }
}
