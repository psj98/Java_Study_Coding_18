package study_240321.problemset;

import java.io.*;
import java.util.*;

public class boj_9944 {
    static int n, m, ans;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int tc = 1; // 테스트케이스 번호
        String input = "";

        while ((input = br.readLine()) != null) {
            stk = new StringTokenizer(input);

            n = Integer.parseInt(stk.nextToken());
            m = Integer.parseInt(stk.nextToken());

            int empty = 0;
            ans = Integer.MAX_VALUE;
            map = new char[n][m];
            visited = new boolean[n][m];

            // 맵 정보
            for (int i = 0; i < n; i++) {
                String str = br.readLine();
                for (int j = 0; j < m; j++) {
                    map[i][j] = str.charAt(j);

                    // 빈 공간 개수 세기
                    if (map[i][j] == '.') {
                        empty++;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    // 장애물인 경우, 스킵
                    if (map[i][j] == '*') {
                        continue;
                    }

                    // 백트래킹
                    visited[i][j] = true;
                    backtracking(i, j, 0, 1, empty);
                    visited[i][j] = false;
                }
            }

            // 모두 방문하지 못하는 경우, -1로 변경
            if (ans == Integer.MAX_VALUE) {
                ans = -1;
            }

            // 정답 저장
            sb.append("Case ").append(tc++).append(": ").append(ans).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    // 백트래킹 - 현재 좌표, 움직인 횟수, 방문한 빈 공간 개수, 빈 공간 개수
    static void backtracking(int x, int y, int moveCnt, int check, int empty) {
        // 빈 공간과 방문한 곳이 같은 경우
        if (check == empty) {
            ans = Math.min(ans, moveCnt); // 최솟값 갱신
            return;
        }

        for (int i = 0; i < 4; i++) {
            int cnt = 0;
            int curX = x;
            int curY = y;

            // 이동
            while (true) {
                curX += dx[i];
                curY += dy[i];

                // 범위 체크
                if (curX < 0 || curX >= n || curY < 0 || curY >= m) {
                    break;
                }

                // 장애물 체크
                if (map[curX][curY] == '*') {
                    break;
                }

                // 방문 체크
                if (visited[curX][curY]) {
                    break;
                }

                cnt++; // 방문 개수 증가
                visited[curX][curY] = true; // 방문 체크
            }

            // 빈 공간으로 이동
            curX -= dx[i];
            curY -= dy[i];

            // 원래 위치인 경우, 불가능
            if (curX == x && curY == y) {
                continue;
            }

            backtracking(curX, curY, moveCnt + 1, check + cnt, empty); // 백트래킹

            // 원래대로 되돌림
            for (int j = 0; j < cnt; j++) {
                visited[curX][curY] = false;
                curX -= dx[i];
                curY -= dy[i];
            }
        }
    }
}
