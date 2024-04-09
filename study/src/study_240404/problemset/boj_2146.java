package study_240404.problemset;

import java.io.*;
import java.util.*;

public class boj_2146 {
    static int n, ans = Integer.MAX_VALUE;
    static int[][] map;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine()); // 맵 크기

        // 초기화
        map = new int[n][n];

        // 맵 정보
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());

                // 섬인 경우 -1로 변경
                if (map[i][j] == 1) {
                    map[i][j] = -1;
                }
            }
        }

        // 섬 별 색상 변경
        int color = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == -1) {
                    colorMap(i, j, color++);
                }
            }
        }

        // BFS - 다리 잇기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    continue;
                }

                bfs(i, j);
            }
        }

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }

    // 섬 별 색상 변경
    static void colorMap(int x, int y, int color) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { x, y });

        map[x][y] = color; // 초기값 색상

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                // 같은 섬이 아니거나 바다 체크
                if (map[nx][ny] != -1) {
                    continue;
                }

                // 색상 변경
                map[nx][ny] = color;
                queue.add(new int[] { nx, ny });
            }
        }
    }

    // BFS
    static void bfs(int x, int y) {
        int color = map[x][y];
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];

        queue.add(new int[] { x, y, 0 });
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                // 방문 체크
                if (visited[nx][ny]) {
                    continue;
                }

                // 같은 섬이 아니거나 바다 체크
                if (map[nx][ny] == color) {
                    continue;
                }

                // 다른 섬인 경우, 최솟값 갱신
                if (map[nx][ny] > 0) {
                    ans = Math.min(ans, cur[2]);
                    continue;
                }

                // 다리 잇기
                queue.add(new int[] { nx, ny, cur[2] + 1 });
                visited[nx][ny] = true;
            }
        }
    }
}
