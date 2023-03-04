package study_230302.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2638 {
    static int n, m, cnt = 0;
    static int[][] map, countMap;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        while (count() != 0) {
            visited = new boolean[n][m];
            countMap = new int[n][m];
            for (int i = 0; i < n; i++) {
                if (i == 0 || i == n - 1) {
                    for (int j = 0; j < m; j++) {
                        if (!visited[i][j] && map[i][j] == 0)
                            bfs(i, j);
                    }
                } else {
                    if (!visited[i][0] && map[i][0] == 0) {
                        bfs(i, 0);
                    }

                    if (!visited[i][m - 1] && map[i][m - 1] == 0) {
                        bfs(i, m - 1);
                    }
                }
            }

            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    if (countMap[i][j] >= 2)
                        map[i][j] = 0;

            cnt++;
        }

        sb.append(cnt);
        System.out.println(sb);
    }

    static void bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { x, y });

        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int curX = queue.peek()[0];
            int curY = queue.poll()[1];

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (map[nx][ny] == 1) {
                        countMap[nx][ny]++;
                    } else if (!visited[nx][ny] && map[nx][ny] == 0) {
                        queue.add(new int[] { nx, ny });
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }

    static int count() {
        int sum = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                sum += map[i][j];

        return sum;
    }
}
