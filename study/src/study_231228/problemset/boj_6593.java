package study_231228.problemset;

import java.io.*;
import java.util.*;

public class boj_6593 {
    static int l, r, c;
    static int startR, startC, startL, endR, endC, endL;
    static int[][][] map;
    static int[] dr = { 1, -1, 0, 0, 0, 0 };
    static int[] dc = { 0, 0, 1, -1, 0, 0 };
    static int[] dl = { 0, 0, 0, 0, 1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        while (true) {
            stk = new StringTokenizer(br.readLine());

            l = Integer.parseInt(stk.nextToken()); // 층
            r = Integer.parseInt(stk.nextToken()); // 행
            c = Integer.parseInt(stk.nextToken()); // 열

            // 종료 조건
            if (l == 0 && r == 0 && c == 0) {
                break;
            }

            // 맵 정보 초기화
            map = new int[r][c][l];
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < r; j++) {
                    String str = br.readLine();
                    for (int k = 0; k < c; k++) {
                        char ch = str.charAt(k);

                        if (ch == 'S') { // 시작 지점 체크
                            startR = j;
                            startC = k;
                            startL = i;
                        } else if (ch == 'E') { // 종료 지점 체크
                            endR = j;
                            endC = k;
                            endL = i;
                        }

                        map[j][k][i] = ch;
                    }
                }

                br.readLine(); // 공백 입력
            }

            sb.append(bfs()); // BFS
        }

        // 정답 출력
        System.out.println(sb);
    }

    // BFS
    static String bfs() {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[r][c][l];
        int cnt = 0; // 이동 횟수

        queue.add(new int[] { startR, startC, startL }); // 시작 위치 저장
        visited[startR][startC][startL] = true;

        while (!queue.isEmpty()) {
            int size = queue.size(); // 현재 이동할 수 있는 개수

            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();

                // 출구에 도달한 경우
                if (cur[0] == endR && cur[1] == endC && cur[2] == endL) {
                    return new StringBuilder("Escaped in ").append(cnt).append(" minute(s).\n").toString();
                }

                for (int i = 0; i < 6; i++) {
                    int nr = cur[0] + dr[i];
                    int nc = cur[1] + dc[i];
                    int nl = cur[2] + dl[i];

                    // 범위 체크
                    if (nr < 0 || nr >= r || nc < 0 || nc >= c || nl < 0 || nl >= l) {
                        continue;
                    }

                    // 방문 체크
                    if (visited[nr][nc][nl]) {
                        continue;
                    }

                    // 금으로 막혀있는 부분
                    if (map[nr][nc][nl] == '#') {
                        continue;
                    }

                    queue.add(new int[] { nr, nc, nl }); // 다음에 갈 곳 저장
                    visited[nr][nc][nl] = true; // 방문
                }
            }

            cnt++; // 이동 횟수 증가
        }

        return new StringBuilder("Trapped!\n").toString(); // 실패한 경우
    }
}
