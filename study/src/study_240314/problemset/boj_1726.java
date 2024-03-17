package study_240314.problemset;

import java.io.*;
import java.util.*;

public class boj_1726 {
    static int n, m;
    static int[][] map;
    static int[] dx = { 0, 1, 0, -1 }; // 동 남 서 북 (시계 방향)
    static int[] dy = { 1, 0, -1, 0 };

    static class Robot {
        int x, y, dir, cnt; // 현재 위치, 이동 횟수

        Robot(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 맵 크기
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        map = new int[n][m];

        // 맵 정보
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        // 시작 위치
        stk = new StringTokenizer(br.readLine());
        int[] start = new int[3];
        for (int i = 0; i < 3; i++) {
            start[i] = Integer.parseInt(stk.nextToken()) - 1;
        }

        // 서쪽, 남쪽 변경
        if (start[2] == 1) {
            start[2] = 2;
        } else if (start[2] == 2) {
            start[2] = 1;
        }

        // 도착 위치
        stk = new StringTokenizer(br.readLine());
        int[] end = new int[3];
        for (int i = 0; i < 3; i++) {
            end[i] = Integer.parseInt(stk.nextToken()) - 1;
        }

        // 서쪽, 남쪽 변경
        if (end[2] == 1) {
            end[2] = 2;
        } else if (end[2] == 2) {
            end[2] = 1;
        }

        // 정답 출력
        sb.append(bfs(start, end)); // BFS
        System.out.println(sb);
    }

    static int bfs(int[] start, int[] end) {
        ArrayDeque<Robot> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[n][m][4]; // 현재 위치, 방향

        queue.add(new Robot(start[0], start[1], start[2], 0));
        visited[start[0]][start[1]][start[2]] = true;

        while (!queue.isEmpty()) {
            Robot cur = queue.poll(); // 현재 로봇

            // 도착 지점과 같은 경우
            if (cur.x == end[0] && cur.y == end[1] && cur.dir == end[2]) {
                return cur.cnt;
            }

            // Go k
            for (int i = 1; i <= 3; i++) {
                int nx = cur.x + dx[cur.dir] * i;
                int ny = cur.y + dy[cur.dir] * i;

                // 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    break;
                }

                // 벽이 있는 경우, 그 다음부터 못감
                if (map[nx][ny] == 1) {
                    break;
                }

                // 방문 체크
                if (visited[nx][ny][cur.dir]) {
                    continue;
                }

                queue.add(new Robot(nx, ny, cur.dir, cur.cnt + 1));
                visited[nx][ny][cur.dir] = true;
            }

            // Turn dir
            for (int i = 0; i < 4; i++) {
                // 같은 방향 스킵
                if (cur.dir == i) {
                    continue;
                }

                // 방문 체크
                if (visited[cur.x][cur.y][i]) {
                    continue;
                }

                // 최소로 회전하도록 값 갱신
                int turnCnt = Math.abs(cur.dir - i);
                if (turnCnt == 3) {
                    turnCnt = 1;
                }

                // 다음에 갈 곳 추가
                queue.add(new Robot(cur.x, cur.y, i, cur.cnt + turnCnt));
                visited[cur.x][cur.y][i] = true;
            }
        }

        return -1;
    }
}
