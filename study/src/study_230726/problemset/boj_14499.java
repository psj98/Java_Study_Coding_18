package study_230726.problemset;

import java.io.*;
import java.util.*;

public class boj_14499 {
    static int n, m, x, y;
    static int[][] map;
    static int dx[] = { 0, 0, -1, 1 }; // 동, 서, 북, 남
    static int dy[] = { 1, -1, 0, 0 };
    static int[] dice = new int[7];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        // 입력
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        x = Integer.parseInt(stk.nextToken());
        y = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());

        // 맵 정보
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        // 명령
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            move(Integer.parseInt(stk.nextToken()));
        }
    }

    static void move(int dir) {
        int nx = x + dx[dir - 1];
        int ny = y + dy[dir - 1];

        // 범위 체크
        if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
            return;
        }

        x = nx;
        y = ny;

        int temp = dice[1];
        if (dir == 1) { // 동
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = temp;
        } else if (dir == 2) { // 서
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = temp;
        } else if (dir == 3) { // 북
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = temp;
        } else { // 남
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = temp;
        }

        if (map[x][y] == 0) { // 칸이 0인 경우, 주사위의 값이 칸에 복사
            map[x][y] = dice[6];
        } else { // 칸이 0이 아닌 경우, 칸의 값이 주사위에 복사 & 칸 값 0
            dice[6] = map[x][y];
            map[x][y] = 0;
        }

        // 정답 출력
        System.out.println(dice[1]);
    }
}
