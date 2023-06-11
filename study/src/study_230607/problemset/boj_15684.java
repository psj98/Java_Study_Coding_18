package study_230607.problemset;

import java.io.*;
import java.util.*;

public class boj_15684 {
    static int n, m, rowCnt;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        m = Integer.parseInt(stk.nextToken());
        rowCnt = Integer.parseInt(stk.nextToken());
        n = Integer.parseInt(stk.nextToken());

        // 사다리 정보
        map = new int[n][m];
        for (int i = 0; i < rowCnt; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken()) - 1;
            int y = Integer.parseInt(stk.nextToken()) - 1;

            map[x][y] = -1;
            map[x][y + 1] = 1;
        }

        // 사다리 설치 및 확인
        for (int i = 0; i <= 3; i++) {
            make(0, i);
        }

        // 조건에 맞지 않는 경우, -1
        sb.append(-1);
        System.out.println(sb);
    }

    // 사다리 설치
    static void make(int cnt, int maxCnt) {
        if (cnt == maxCnt) { // 사다리 설치한 개수가 같은 경우
            if (!check()) // i번에서 시작해서 i번으로 끝나지 않는 경우, 다시 설치
                return;

            // 조건에 맞는 경우, 정답 출력 후 종료
            System.out.println(cnt);
            System.exit(0);
        }

        // 사다리 설치
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m - 1; j++) {
                // 이미 사다리가 설치되어 있는 경우, 스킵
                if (map[i][j] != 0 || map[i][j + 1] != 0)
                    continue;

                map[i][j] = -1;
                map[i][j + 1] = 1;
                make(cnt + 1, maxCnt);
                map[i][j] = 0;
                map[i][j + 1] = 0;
            }
        }
    }

    // i번에서 시작해서 i번으로 끝나는지 체크
    static boolean check() {
        for (int i = 0; i < m; i++) {
            int x = 0, y = i;

            while (x < n) {
                if (map[x][y] == -1) { // 왼쪽에서 만난 경우, 오른쪽으로 이동
                    y++;
                } else if (map[x][y] == 1) { // 오른쪽에서 만난 경우, 왼쪽으로 이동
                    y--;
                }

                x++;
            }

            // 같지 않은 경우, false
            if (y != i) {
                return false;
            }
        }

        // 모두 같은 경우, true
        return true;
    }
}