package study_230215.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_27211 {
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static int[][] map;
    static int n, m, cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        map = new int[n][m];

        // 좌표 저장 (0, 1)
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        // 좌표가 0인 부분에 대해 bfs
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    bfs(i, j);
                    cnt++; // bfs 끝나면 (영역 1개 확인)
                }
            }
        }

        sb.append(cnt);
        System.out.println(sb);
    }

    // 0인 좌표 탐색 후, 2로 변경
    public static void bfs(int a, int b) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { a, b });
        map[a][b] = 2;

        // 0이 안나올 때까지 탐색
        while (!queue.isEmpty()) {
            int x = queue.peek()[0];
            int y = queue.peek()[1];
            queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 왼쪽에서 오른쪽으로 or 위에서 아래로 넘어가는 경우, 끝 지점으로 값 갱신
                if (nx < 0)
                    nx = n - 1;
                if (ny < 0)
                    ny = m - 1;

                // 값이 범위를 벗어나는 경우 갱신
                nx %= n;
                ny %= m;

                if (map[nx][ny] == 0) {
                    map[nx][ny] = 2; // 방문 표시
                    queue.add(new int[] { nx, ny });
                }
            }
        }
    }
}
