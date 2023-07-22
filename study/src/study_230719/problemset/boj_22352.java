package study_230719.problemset;

import java.io.*;
import java.util.*;

public class boj_22352 {
    static int n, m;
    static int[][] map, result; // 기존 항체, 백신을 맞고 난 후
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        // 초기화
        map = new int[n][m];
        result = new int[n][m];

        // 맞기 전 맵
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        // 맞은 후 맵
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                result[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        check(); // BFS로 다른 곳 체크
        boolean ans = tf(); // 정답 체크

        // 정답 출력
        sb.append(ans ? "YES" : "NO");
        System.out.println(sb);
    }

    // 다른 곳이 있으면 false, 모두 같으면 true
    static boolean tf() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != result[i][j])
                    return false;
            }
        }

        return true;
    }

    // BFS로 다른 곳을 같은 것으로 만듦
    static void check() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == result[i][j])
                    continue;

                bfs(i, j, map[i][j], result[i][j]);
                return;
            }
        }
    }

    // BFS
    static void bfs(int x, int y, int pre, int num) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        queue.add(new int[] { x, y });
        visited[x][y] = true;

        map[x][y] = num;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                if (map[nx][ny] != pre)
                    continue;

                if (visited[nx][ny])
                    continue;

                map[nx][ny] = num;
                queue.add(new int[] { nx, ny });
                visited[nx][ny] = true;
            }
        }
    }
}
