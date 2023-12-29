package study_231228.problemset;

import java.io.*;
import java.util.*;

public class boj_1261 {
    static int n, m;
    static int[][] map;

    static class Point implements Comparable<Point> {
        int x, y, cnt; // 좌표, 부순 개수

        Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        // 부순 개수 오름차순 정렬
        @Override
        public int compareTo(Point o) {
            return this.cnt - o.cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        m = Integer.parseInt(stk.nextToken());
        n = Integer.parseInt(stk.nextToken());

        // 맵 정보 초기화
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        // 정답 출력
        sb.append(bfs());
        System.out.println(sb);
    }

    static int bfs() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[n][m];
        int[] dx = { 1, -1, 0, 0 };
        int[] dy = { 0, 0, 1, -1 };

        pq.add(new Point(0, 0, 0)); // 시작 위치 저장
        visited[0][0] = true; // 시작 위치 방문 체크

        while (!pq.isEmpty()) {
            Point cur = pq.poll();

            // 끝에 도달한 경우
            if (cur.x == n - 1 && cur.y == m - 1) {
                return cur.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                // 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                // 방문 체크
                if (visited[nx][ny]) {
                    continue;
                }

                if (map[nx][ny] == 0) { // 벽 X
                    pq.add(new Point(nx, ny, cur.cnt));
                } else { // 벽 O
                    pq.add(new Point(nx, ny, cur.cnt + 1));
                }

                visited[nx][ny] = true; // 방문
            }
        }

        return 0;
    }
}
