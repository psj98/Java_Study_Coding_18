package study_230531.problemset;

import java.io.*;
import java.util.*;

public class boj_13459 {
    static int n, m;
    static char[][] map;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 10번 이하로 가능 1 / 아니면 0
        // 빨간 구슬만 빠져야 함
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        // 빨간공, 파란공 좌표
        int rx = 0, ry = 0, bx = 0, by = 0;

        // 맵 정보
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);

                if (map[i][j] == 'R') { // 빨간공 좌표 저장
                    rx = i;
                    ry = j;
                } else if (map[i][j] == 'B') { // 파란공 좌표 저장
                    bx = i;
                    by = j;
                }
            }
        }

        // 정답 출력
        sb.append(bfs(rx, ry, bx, by));
        System.out.println(sb);
    }

    // BFS 탐색
    static int bfs(int rx, int ry, int bx, int by) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[n][m][n][m];

        queue.add(new int[] { rx, ry, bx, by });
        visited[rx][ry][bx][by] = true; // 방문 체크

        int cnt = 0; // 이동한 횟수
        while (!queue.isEmpty()) {
            // 10번 이하로 못 뺄 경우
            if (cnt == 10) {
                return 0;
            }

            int size = queue.size();

            // Queue의 크기만큼 반복 -> 횟수 체크
            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int[] red = { cur[0], cur[1] }; // 빨간공 좌표
                    int[] blue = { cur[2], cur[3] }; // 파란공 좌표

                    int redDist = move(red, i); // 빨간공 거리
                    int blueDist = move(blue, i); // 파란공 거리

                    boolean redFlag = false, blueFlag = false;
                    if (map[red[0]][red[1]] == 'O') // 빨간공이 구멍에 들어간 경우
                        redFlag = true;

                    if (map[blue[0]][blue[1]] == 'O') // 파란공이 구멍에 들어간 경우
                        blueFlag = true;

                    // 둘이 같은 좌표에 있는 경우, 먼 거리를 이동한 구슬이 한 칸 거꾸로 이동
                    if (red[0] == blue[0] && red[1] == blue[1]) {
                        if (redDist > blueDist) {
                            red[0] -= dx[i];
                            red[1] -= dy[i];
                        } else {
                            blue[0] -= dx[i];
                            blue[1] -= dy[i];
                        }
                    }

                    if (redFlag) { // 빨간 구슬이 빠진 경우
                        if (blueFlag) // 파란 구슬이 빠진 경우, 스킵
                            continue;
                        return 1; // 빨간 구슬만 빠진 경우, 1
                    } else {// 빨간 구슬이 안빠진 경우
                        if (blueFlag) // 파란 구슬이 빠진 경우, 스킵
                            continue;

                        // 이미 방문한 좌표인 경우, 스킵
                        if (visited[red[0]][red[1]][blue[0]][blue[1]])
                            continue;

                        visited[red[0]][red[1]][blue[0]][blue[1]] = true;
                        queue.add(new int[] { red[0], red[1], blue[0], blue[1] });
                    }
                }
            }

            cnt++; // 횟수 증가
        }

        return 0;
    }

    // 구슬 이동
    static int move(int[] marble, int dir) {
        int dist = 0;

        while (true) {
            marble[0] += dx[dir];
            marble[1] += dy[dir];

            if (map[marble[0]][marble[1]] == '#') {
                marble[0] -= dx[dir];
                marble[1] -= dy[dir];
                break;
            } else if (map[marble[0]][marble[1]] == 'O') {
                break;
            }

            dist++;
        }

        return dist;
    }
}
