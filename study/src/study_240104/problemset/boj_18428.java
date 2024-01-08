package study_240104.problemset;

import java.io.*;
import java.util.*;

public class boj_18428 {
    static boolean answer; // 정답
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 맵 범위

        // 맵 정보 초기 및 저장
        char[][] map = new char[n][n];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = stk.nextToken().charAt(0);
            }
        }

        find(n, map, 0, 0); // 백트래킹

        // 정답 출력
        sb.append("NO"); // 실패 시
        System.out.println(sb);
    }

    // 백트래킹
    static void find(int n, char[][] map, int cnt, int idx) {
        // 장애물이 3개가 설치된 경우
        if (cnt == 3) {
            boolean check = canAvoid(n, map); // 선생님이 볼 수 있는지 체크

            // 선생님이 볼 수 없는 경우, 성공
            if (check) {
                System.out.println("YES");
                System.exit(0);
            }

            return;
        }

        for (int i = idx; i < n * n; i++) {
            char cur = map[i / n][i % n]; // 현재 값

            // 학생 or 선생이면 장애물 설치 불가
            if (cur == 'S' || cur == 'T') {
                continue;
            }

            map[i / n][i % n] = 'O'; // 장애물 설치
            find(n, map, cnt + 1, i + 1); // 백트래킹
            map[i / n][i % n] = 'X'; // 장애물 회수
        }
    }

    // 학생들이 감시를 피할 수 있는지 체크
    static boolean canAvoid(int n, char[][] map) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char cur = map[i][j]; // 현재 값

                // 학생 or 장애물 or 아무것도 설치 X이면 스킵
                if (cur == 'S' || cur == 'O' || cur == 'X') {
                    continue;
                }

                // 선생인 경우, 실행
                for (int k = 0; k < 4; k++) {
                    // 다음에 검사해야 할 곳
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    while (true) {
                        // 범위 체크
                        if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                            break;
                        }

                        // 장애물인 경우, 종료
                        if (map[nx][ny] == 'O') {
                            break;
                        }

                        // 학생인 경우, 감시 못피함 => 실패
                        if (map[nx][ny] == 'S') {
                            return false;
                        }

                        // 다음 체크할 곳
                        nx += dx[k];
                        ny += dy[k];
                    }
                }
            }
        }

        return true; // 성공
    }
}
