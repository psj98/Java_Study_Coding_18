package study_240404.problemset;

import java.io.*;
import java.util.*;

public class boj_17386 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        long[][] pos = new long[2][4];

        // 각 점 저장
        for (int i = 0; i < 2; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                pos[i][j] = Long.parseLong(stk.nextToken());
            }
        }

        // CCW
        int c1 = ccw(pos[0][0], pos[0][1], pos[0][2], pos[0][3], pos[1][0], pos[1][1]);
        int c2 = ccw(pos[0][0], pos[0][1], pos[0][2], pos[0][3], pos[1][2], pos[1][3]);
        int c3 = ccw(pos[1][0], pos[1][1], pos[1][2], pos[1][3], pos[0][0], pos[0][1]);
        int c4 = ccw(pos[1][0], pos[1][1], pos[1][2], pos[1][3], pos[0][2], pos[0][3]);

        if (c1 * c2 < 0 && c3 * c4 < 0) { // 둘 다 음수인 경우, 교차
            sb.append(1);
        } else {
            sb.append(0);
        }

        // 정답 출력
        System.out.println(sb);
    }

    // CCW
    static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long num1 = x1 * y2 + x2 * y3 + x3 * y1;
        long num2 = x2 * y1 + x3 * y2 + x1 * y3;

        return num1 - num2 < 0 ? 1 : -1;
    }
}
