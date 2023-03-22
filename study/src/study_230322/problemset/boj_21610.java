package study_230322.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_21610 {
    static int n, m;
    static int[][] map;
    static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
    static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static boolean[][] visited;
    static Queue<int[]> cloud = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        // 구름 초기 좌표 저장
        for (int i = 0; i <= 1; i++) {
            cloud.add(new int[] { n - 1, i });
            cloud.add(new int[] { n - 2, i });
        }

        // 구름 움직이면서 비 양 구하기
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(stk.nextToken()) - 1;
            int cnt = Integer.parseInt(stk.nextToken());
            moveCloud(dir, cnt);
            copyWater();
            makeCloud();
        }

        // 물 합 구하기
        int sum = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                sum += map[i][j];

        sb.append(sum);
        System.out.println(sb);
    }

    // 구름 이동 및 바구니 물 양 증가
    static void moveCloud(int dir, int cnt) {
        for (int[] cur : cloud) {
            // 음수 변환 한꺼번에
            cur[0] = (cur[0] + n + dx[dir] * (cnt % n)) % n;
            cur[1] = (cur[1] + n + dy[dir] * (cnt % n)) % n;
            map[cur[0]][cur[1]]++;
        }
    }

    // 물 복사
    static void copyWater() {
        visited = new boolean[n][n]; // 구름이 사라지는 위치

        while (!cloud.isEmpty()) {
            int[] pos = cloud.poll();
            int cnt = 0; // 대각선에 위치한 비가 있는 바구니 개수

            for (int i = 1; i < 8; i += 2) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] > 0)
                    cnt++; // 개수 증가
            }

            map[pos[0]][pos[1]] += cnt; // 해당 바구니 개수만큼 증가
            visited[pos[0]][pos[1]] = true; // 구름이 사라지는 위치 체크
        }
    }

    // 구름 만들기
    static void makeCloud() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 구름이 사라진 위치가 아니고 물의 양이 2 이상이면 구름 생성
                if (!visited[i][j] && map[i][j] >= 2) {
                    cloud.add(new int[] { i, j });
                    map[i][j] -= 2; // 물 양 - 2
                }
            }
        }
    }
}