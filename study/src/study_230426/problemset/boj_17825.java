package study_230426.problemset;

import java.io.*;
import java.util.*;

public class boj_17825 {
    static Point[] points;
    static int[] dice;
    static int[] pos;
    static int ans = Integer.MIN_VALUE;

    static class Point {
        int roundWay, shortWay, score;

        Point(int roundWay, int shortWay, int score) {
            this.roundWay = roundWay;
            this.shortWay = shortWay;
            this.score = score;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        points = new Point[40];
        dice = new int[10];
        pos = new int[4];

        for (int i = 0; i < 10; i++)
            dice[i] = Integer.parseInt(stk.nextToken());

        // 맵 정보
        for (int i = 1; i <= 19; i++)
            points[i] = new Point(i + 1, 0, i * 2);

        // 원래 길 정보 (지름길 아닌 곳)
        points[0] = new Point(1, 0, 0);
        points[20] = new Point(32, 0, 40);
        points[21] = new Point(22, 0, 13);
        points[22] = new Point(23, 0, 16);
        points[23] = new Point(24, 0, 19);
        points[24] = new Point(30, 0, 25);
        points[25] = new Point(24, 0, 26);
        points[26] = new Point(25, 0, 27);
        points[27] = new Point(26, 0, 28);
        points[28] = new Point(29, 0, 22);
        points[29] = new Point(24, 0, 24);
        points[30] = new Point(31, 0, 30);
        points[31] = new Point(20, 0, 35);
        points[32] = new Point(32, 0, 0);

        // 지름길 정보
        points[5].shortWay = 21;
        points[10].shortWay = 28;
        points[15].shortWay = 27;

        permutation(0, 0); // 중복 순열 - 백트래킹

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }

    // 중복 순열 - 백트래킹
    static void permutation(int cnt, int sum) {
        if (cnt == 10) { // 모든 주사위를 던진 경우
            ans = Math.max(ans, sum); // 최댓값 갱신
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (pos[i] == 32) // 도착한 말은 고를 수 없음
                continue;

            int next = pos[i]; // 다음에 갈 위치 구하기
            for (int j = 0; j < dice[cnt]; j++) {
                if (j == 0) { // 말의 첫 번째 위치가 지름길로 갈 수 있는 경우 체크
                    if (points[next].shortWay != 0)
                        next = points[next].shortWay;
                    else if (points[next].shortWay == 0)
                        next = points[next].roundWay;

                    continue;
                }

                next = points[next].roundWay; // 그 외의 경우, 원래 경로로 이동
            }

            boolean check = false;
            for (int j = 0; j < 4; j++) {
                if (next != 32 && pos[j] == next) { // 갈 곳에 말이 있는지 체크
                    check = true;
                    break;
                }
            }

            if (check) // 말이 있는 경우, 불가능
                continue;

            // 백트래킹
            int cur = pos[i];
            pos[i] = next;
            permutation(cnt + 1, sum + points[next].score);
            pos[i] = cur;
        }
    }
}
