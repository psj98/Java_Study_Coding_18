package study_240411.problemset;

import java.io.*;
import java.util.*;

public class boj_17780 {
    static int n, k; // 맵 크기, 말 개수
    static int[][] map; // 체스판
    static ArrayList<Integer>[][] orders; // 말의 순
    static Horse[] horses; // 말 정보
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { 1, -1, 0, 0 };
    static int[] changeDir = { 1, 0, 3, 2 }; // 방향 변경 배열

    // 말 정보
    static class Horse {
        int x, y, dir; // 위치, 방향

        Horse(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken()); // 맵 크기
        k = Integer.parseInt(stk.nextToken()); // 말 개수

        // 초기화
        map = new int[n][n];
        orders = new ArrayList[n][n];
        horses = new Horse[k];

        // 맵 저장 및 초기화
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
                orders[i][j] = new ArrayList<>();
            }
        }

        // 말 정보 저장
        for (int i = 0; i < k; i++) {
            stk = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(stk.nextToken()) - 1;
            int y = Integer.parseInt(stk.nextToken()) - 1;
            int dir = Integer.parseInt(stk.nextToken()) - 1;

            horses[i] = new Horse(x, y, dir);
            orders[x][y].add(i);
        }

        // 정답 출력
        sb.append(move());
        System.out.println(sb);
    }

    // 말 움직이기
    static int move() {
        int turn = 0; // 턴

        while (true) {
            turn++; // 턴 증가

            // 1000번 턴이 넘으면 종료
            if (turn > 1000) {
                turn = -1;
                break;
            }

            // 말 이동
            for (int i = 0; i < k; i++) {
                Horse cur = horses[i]; // 현재 말

                // 현 위치
                int curX = cur.x;
                int curY = cur.y;

                // 말이 없는 경우, 스킵
                if (orders[curX][curY].isEmpty()) {
                    continue;
                }

                // 가장 아랫쪽 말이 아닌 경우, 스킵
                if (orders[curX][curY].get(0) != i) {
                    continue;
                }

                // 다음에 갈 위치
                int nx = curX + dx[cur.dir];
                int ny = curY + dy[cur.dir];

                // 이동하려는 칸이 파란색 or 범위를 벗어나는 경우
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] == 2) {
                    cur.dir = changeDir[cur.dir]; // 방향 반대로

                    // 이동
                    nx = curX + dx[cur.dir];
                    ny = curY + dy[cur.dir];
                }

                // 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if (map[nx][ny] == 0) { // 흰색
                    // 모든 말 이동
                    for (int j = 0; j < orders[curX][curY].size(); j++) {
                        int idx = orders[curX][curY].get(j); // 현 위치 말 번호

                        orders[nx][ny].add(idx); // 다음 위치에 말 추가

                        // 말 위치 변경
                        horses[idx].x = nx;
                        horses[idx].y = ny;
                    }
                } else if (map[nx][ny] == 1) { // 빨간색
                    for (int j = orders[curX][curY].size() - 1; j >= 0; j--) {
                        int idx = orders[curX][curY].get(j); // 현 위치 말 번호

                        orders[nx][ny].add(idx); // 다음 위치에 말 추가

                        // 말 위치 변경
                        horses[idx].x = nx;
                        horses[idx].y = ny;
                    }
                } else { // 파란색
                    continue;
                }

                orders[curX][curY].clear(); // 현위치에서 모든 말 제거

                // 4개 이상의 말이 모인 경우, 종료
                if (orders[nx][ny].size() >= 4) {
                    return turn;
                }
            }

        }

        return turn;
    }
}
