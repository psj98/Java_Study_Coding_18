package study_230802.problemset;

import java.io.*;
import java.util.*;

public class boj_2615 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        int[][] map = new int[21][21];
        for (int i = 1; i <= 19; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 19; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        for (int i = 1; i <= 19; i++) {
            for (int j = 1; j <= 19; j++) {
                boolean check = false;

                if (map[i][j] == 0)
                    continue;

                // 가로
                if (j + 4 <= 19) {
                    for (int k = 1; k < 5; k++) {
                        if (map[i][j] != map[i][j + k]) {
                            check = true;
                            break;
                        }
                    }

                    if (!check && map[i][j] != map[i][j + 5] && map[i][j] != map[i][j - 1]) {
                        System.out.println(map[i][j]);
                        System.out.println(i + " " + j);
                        return;
                    }
                }

                // 세로
                check = false;
                if (i + 4 <= 19) {
                    for (int k = 1; k < 5; k++) {
                        if (map[i][j] != map[i + k][j]) {
                            check = true;
                            break;
                        }
                    }

                    if (!check && map[i][j] != map[i + 5][j] && map[i][j] != map[i - 1][j]) {
                        System.out.println(map[i][j]);
                        System.out.println(i + " " + j);
                        return;
                    }
                }

                // 대각선
                check = false;
                if (i + 4 <= 19 && j + 4 <= 19) {
                    for (int k = 1; k < 5; k++) {
                        if (map[i][j] != map[i + k][j + k]) {
                            check = true;
                            break;
                        }
                    }

                    if (!check && map[i][j] != map[i + 5][j + 5] && map[i][j] != map[i - 1][j - 1]) {
                        System.out.println(map[i][j]);
                        System.out.println(i + " " + j);
                        return;
                    }
                }

                check = false;
                if (i + 4 <= 19 && j - 4 > 0) {
                    for (int k = 1; k < 5; k++) {
                        if (map[i][j] != map[i + k][j - k]) {
                            check = true;
                            break;
                        }
                    }

                    if (!check && map[i][j] != map[i + 5][j - 5] && map[i][j] != map[i - 1][j + 1]) {
                        System.out.println(map[i][j]);
                        System.out.println((i + 4) + " " + (j - 4));
                        return;
                    }
                }
            }
        }

        System.out.println(0);
    }
}
