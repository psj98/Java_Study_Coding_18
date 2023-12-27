package study_231221.problemset;

import java.io.*;
import java.util.*;

public class boj_27958 {
    static int n, k, answer;
    static int[] bullets;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine()); // 보드판 크기
        k = Integer.parseInt(br.readLine()); // 총알 개수

        // 초기화
        int[][] map = new int[n][n];
        int[][] hp = new int[n][n];
        bullets = new int[k];

        // 초기 체력, 남은 체력 정보
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                map[i][j] = hp[i][j] = Integer.parseInt(stk.nextToken());
        }

        // 총알 공격력 정보
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            bullets[i] = Integer.parseInt(stk.nextToken());
        }

        backtracking(0, 0, map, hp); // 백트래킹

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    // 백트래킹
    static void backtracking(int cnt, int score, int[][] map, int[][] hp) {
        // 전체 총알 개수 = 현재 사용한 총알 개수
        if (k == cnt) {
            answer = Math.max(answer, score);
            return;
        }

        for (int i = 0; i < n; i++) {
            // 가장 왼쪽에 있는 표적 발사
            for (int j = 0; j < n; j++) {
                // 표적이 없는 경우, 스킵
                if (hp[i][j] == 0) {
                    continue;
                }

                // 보드판, 체력 맵 복사
                int[][] copyMap = new int[n][n];
                int[][] copyHP = new int[n][n];

                for (int k = 0; k < n; k++) {
                    copyMap[k] = Arrays.copyOfRange(map[k], 0, n);
                    copyHP[k] = Arrays.copyOfRange(hp[k], 0, n);
                }

                // 보너스 점수인 경우
                if (copyHP[i][j] >= 10) {
                    copyHP[i][j] = copyMap[i][j] = 0; // 현재 체력, 초기 체력 0
                    backtracking(cnt + 1, score + map[i][j], copyMap, copyHP);
                    break;
                }

                // 보너스 점수가 아닌 경우
                copyHP[i][j] = Math.max(copyHP[i][j] - bullets[cnt], 0); // 남은 체력

                // 표적 사라지는지 체크
                if (copyHP[i][j] == 0) { // 표적이 사라진 경우
                    copyMap[i][j] = 0; // 초기 체력 0

                    // 상하좌우에 새로운 표적 생성
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        // 범위 체크
                        if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                            continue;
                        }

                        // 표적 여부 체크
                        if (copyHP[nx][ny] != 0) {
                            continue;
                        }

                        copyMap[nx][ny] = copyHP[nx][ny] = map[i][j] / 4; // 새로운 표적 생성
                    }

                    backtracking(cnt + 1, score + map[i][j], copyMap, copyHP);
                } else { // 표적이 사라지지 않은 경우
                    backtracking(cnt + 1, score, copyMap, copyHP);
                }

                break;
            }
        }
    }
}
