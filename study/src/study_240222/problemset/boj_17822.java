package study_240222.problemset;

import java.io.*;
import java.util.*;

public class boj_17822 {
    static int n, m;
    static int[][] map;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static boolean check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        int t = Integer.parseInt(stk.nextToken());

        map = new int[n][m];

        // 맵 정보
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        // 회전 횟수
        for (int tc = 0; tc < t; tc++) {
            stk = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(stk.nextToken()); // 배수
            int d = Integer.parseInt(stk.nextToken()); // 0 : 시계, 1 : 반시계
            int k = Integer.parseInt(stk.nextToken()); // 칸 수

            // 반시계일 경우, 시계 방향으로 변경
            if (d == 1) {
                k = m - k;
            }

            rotate(x, k); // 회전

            check = false; // 인접 여부 체크
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 0) {
                        continue;
                    }

                    bfs(i, j); // 인접 수 삭제
                }
            }

            // 인접수가 있는 경우, 스킵
            if (check) {
                continue;
            }

            avg(); // 없는 경우, 평균값 구하기
        }

        int sum = 0; // 총합
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum += map[i][j];
            }
        }

        // 정답 출력
        sb.append(sum);
        System.out.println(sb);
    }

    // 회전
    static void rotate(int x, int k) {
        // Queue에 저장하여 순서대로 저장
        for (int i = x - 1; i < n; i += x) {
            ArrayDeque<Integer> queue = new ArrayDeque<>();

            for (int j = m - k; j < m; j++) {
                queue.add(map[i][j]);
            }

            for (int j = 0; j < m - k; j++) {
                queue.add(map[i][j]);
            }

            for (int j = 0; j < m; j++) {
                map[i][j] = queue.poll();
            }
        }
    }

    // BFS
    static void bfs(int x, int y) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        int cnt = 1; // 인접한 값 개수

        queue.add(new int[] { x, y });
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = (cur[1] + dy[i] + m) % m;

                // 범위 체크 => 가로의 경우, 양 옆이 이어짐
                if (nx < 0 || nx >= n) {
                    continue;
                }

                // 방문 체크
                if (visited[nx][ny]) {
                    continue;
                }

                // 다를 경우, 스킵
                if (map[x][y] != map[nx][ny]) {
                    continue;
                }

                queue.add(new int[] { nx, ny });
                visited[nx][ny] = true;
                cnt++;
            }
        }

        // 인접수가 없는 경우,
        if (cnt == 1) {
            return;
        }

        check = true; // 인접수가 있는 경우

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    continue;
                }

                map[i][j] = 0; // 0으로 변경
            }
        }
    }

    // 평균값 기준으로 값 변경
    static void avg() {
        double sum = 0, cnt = 0;

        // 평균값 구하기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    continue;
                }

                sum += map[i][j];
                cnt++;
            }
        }

        sum /= cnt;

        // 값 변경
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    continue;
                }

                if (map[i][j] > sum) { // 큰 경우 -1
                    map[i][j]--;
                } else if (map[i][j] < sum) { // 작은 경우 +1
                    map[i][j]++;
                }
            }
        }
    }
}
