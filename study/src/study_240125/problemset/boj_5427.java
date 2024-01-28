package study_240125.problemset;

import java.io.*;
import java.util.*;

public class boj_5427 {
    static int n, m;
    static char[][] map;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static ArrayDeque<int[]> fire; // 불 위치

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            stk = new StringTokenizer(br.readLine());

            m = Integer.parseInt(stk.nextToken());
            n = Integer.parseInt(stk.nextToken());

            // 맵 정보 초기화
            map = new char[n][m];
            fire = new ArrayDeque<>();

            int x = 0, y = 0; // 상근 초기 위치

            for (int i = 0; i < n; i++) {
                String str = br.readLine();
                for (int j = 0; j < m; j++) {
                    map[i][j] = str.charAt(j);

                    if (map[i][j] == '*') { // 불인 경우
                        fire.add(new int[] { i, j });
                    } else if (map[i][j] == '@') { // 상근이인 경우
                        x = i;
                        y = j;
                    }
                }
            }

            int ans = bfs(x, y); // BFS

            if (ans == -1) { // 불가능한 경우
                sb.append("IMPOSSIBLE\n");
            } else { // 가능한 경우
                sb.append(ans).append("\n");
            }
        }

        // 정답 출력
        System.out.println(sb);
    }

    // BFS
    static int bfs(int x, int y) {
        int time = 0; // 시간
        ArrayDeque<int[]> sg = new ArrayDeque<>(); // 상근이 위치

        sg.add(new int[] { x, y }); // 초기 위치 저장

        while (!sg.isEmpty()) {
            // 불 이동
            int size = fire.size();
            for (int s = 0; s < size; s++) {
                int[] cur = fire.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];

                    // 범위 체크
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        continue;
                    }

                    // 빈 공간 or 상근이 위치인 경우, 이동 가능
                    if (map[nx][ny] == '.' || map[nx][ny] == '@') {
                        map[nx][ny] = '*';
                        fire.add(new int[] { nx, ny });
                    }
                }
            }

            // 상근이 이동
            size = sg.size();
            for (int s = 0; s < size; s++) {
                int[] cur = sg.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];

                    // 범위 체크
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        return time + 1; // 정답
                    }

                    // 빈 공간인 경우, 이동 가능
                    if (map[nx][ny] == '.') {
                        map[nx][ny] = '@';
                        sg.add(new int[] { nx, ny });
                    }
                }
            }

            time++; // 시간 증가
        }

        return -1;
    }
}
