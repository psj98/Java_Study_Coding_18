package study_230802.problemset;

import java.io.*;
import java.util.*;

public class boj_1986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int[] qx = { 1, -1, 0, 0, 1, 1, -1, -1 };
        int[] qy = { 0, 0, 1, -1, 1, -1, 1, -1 };
        int[] kx = { 1, 1, -1, -1, 2, 2, -2, -2 };
        int[] ky = { 2, -2, 2, -2, 1, -1, 1, -1 };

        // n * m
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        // 초기화
        int[][] map = new int[n][m]; // 0 빈 공간, 1 말 위치, 2 움직일 수 있는 위치
        ArrayList<int[]> queen = new ArrayList<>(); // 퀸 좌표 리스트

        // 퀸 좌표
        stk = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(stk.nextToken());
        for (int i = 0; i < num; i++) {
            int x = Integer.parseInt(stk.nextToken()) - 1;
            int y = Integer.parseInt(stk.nextToken()) - 1;

            queen.add(new int[] { x, y });
            map[x][y] = 1; // 퀸 좌표
        }

        // 나이트 좌표
        stk = new StringTokenizer(br.readLine());
        num = Integer.parseInt(stk.nextToken());
        for (int i = 0; i < num; i++) {
            int x = Integer.parseInt(stk.nextToken()) - 1;
            int y = Integer.parseInt(stk.nextToken()) - 1;

            map[x][y] = 1; // 나이트 좌표

            // 나이트가 갈 수 있는 곳 2로 표시
            for (int j = 0; j < 8; j++) {
                int nx = x + kx[j];
                int ny = y + ky[j];

                // 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                // 말 있는 곳 못감
                if (map[nx][ny] == 1)
                    continue;

                map[nx][ny] = 2;
            }
        }

        // 폰 좌표
        stk = new StringTokenizer(br.readLine());
        num = Integer.parseInt(stk.nextToken());
        for (int i = 0; i < num; i++) {
            int x = Integer.parseInt(stk.nextToken()) - 1;
            int y = Integer.parseInt(stk.nextToken()) - 1;

            map[x][y] = 1;
        }

        // 퀸 갈 수 있는 곳 체크
        for (int[] cur : queen) {
            int x = cur[0];
            int y = cur[1];

            for (int j = 0; j < 8; j++) {
                int nx = x;
                int ny = y;

                while (true) {
                    nx += qx[j];
                    ny += qy[j];

                    // 범위 체크
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        continue;
                    }

                    // 말 있는 곳 못감
                    if (map[nx][ny] == 1) {
                        break;
                    }

                    map[nx][ny] = 2;
                }
            }
        }

        // 안전한 칸 세기
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] >= 1)
                    continue;
                ans++;
            }
        }

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }
}
