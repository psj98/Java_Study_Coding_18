package study_230208.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2580_1 {
    static StringBuilder sb = new StringBuilder();
    static int[][] map = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        for (int i = 0; i < 9; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        sudoku(0, 0);
    }

    public static void sudoku(int x, int y) {
        // 9*9를 모두 조회했으므로 종료
        if (x == 9) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }

            System.out.println(sb);
            System.exit(0);
        }

        // map의 끝이므로 x+1하고 y는 0으로 초기화
        if (y == 9) {
            sudoku(x + 1, 0);
            return;
        }

        // 숫자 찾기
        if (map[x][y] == 0) {
            // 각 값을 넣어보고 맞는 값 찾음
            for (int i = 1; i <= 9; i++) {
                // 맞으면 다음 좌표 탐색
                if (check(x, y, i)) {
                    map[x][y] = i;
                    sudoku(x, y + 1);
                    map[x][y] = 0;
                }
            }

            return;
        }

        sudoku(x, y + 1);
    }

    // 숫자 찾기 (가로, 세로, 3*3 안에 모두 다른 값)
    public static boolean check(int x, int y, int num) {
        // 가로, 세로
        for (int i = 0; i < 9; i++) {
            if (map[x][i] == num)
                return false;
            if (map[i][y] == num)
                return false;
        }

        // 3*3
        for (int i = x / 3 * 3; i < x / 3 * 3 + 3; i++) {
            for (int j = y / 3 * 3; j < y / 3 * 3 + 3; j++) {
                if (map[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
