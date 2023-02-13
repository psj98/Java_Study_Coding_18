package study_230208.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_2580_2 {
    static StringBuilder sb = new StringBuilder();
    static int[][] map = new int[9][9];
    static ArrayList<int[]> pos = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        for (int i = 0; i < 9; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
                if (map[i][j] == 0)
                    pos.add(new int[] { i, j });
            }
        }

        sudoku(0);
    }

    public static void sudoku(int idx) {
        // 0인 좌표를 모두 확인했으므로 출력 후, 종료
        if (idx == pos.size()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }

            System.out.println(sb);
            System.exit(0);
        }

        // list의 각 x, y 좌표
        int x = pos.get(idx)[0];
        int y = pos.get(idx)[1];

        // 1~9를 넣어보면서 값 확인
        for (int i = 1; i <= 9; i++) {
            if (check(x, y, i)) {
                map[x][y] = i;
                sudoku(idx + 1);
                map[x][y] = 0;
            }
        }
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