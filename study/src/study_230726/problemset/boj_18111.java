package study_230726.problemset;

import java.io.*;
import java.util.*;

public class boj_18111 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 1*1*1
        // 높이 모두 동일하게
        // 세로 N, 가로 M, 인벤토리에 있는 블록 B개 / 맨 왼쪽 위 (0, 0)
        //

        /**
         * 1. 좌표 (i, j)의 가장 위에 있는 블록 제거 => 인벤토리에 저장 (2초)
         * 1-1. 인벤토리에는 B개의 블록이 들어있음
         * 2. 인벤토리에서 블록 한 개를 꺼내 좌표(i, j)의 가장 위에 블록 놓음 (1초)
         * 3. 최소 시간 + 땅 높이
         */

        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int B = Integer.parseInt(stk.nextToken());

        int[][] map = new int[N][M];
        int time = Integer.MAX_VALUE, height = 0;
        int max = 0;
        for (int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }

        for (int i = 0; i <= max; i++) {
            int add = 0, remove = 0;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    int diff = map[j][k] - i;

                    if (diff > 0) {
                        remove += diff;
                    } else {
                        add += (-diff);
                    }
                }
            }

            if (remove + B >= add) {
                if (time >= add + remove * 2) {
                    time = add + remove * 2;
                    height = i;
                }
            }
        }

        // 정답 출력
        sb.append(time).append(" ").append(height);
        System.out.println(sb);
    }
}
