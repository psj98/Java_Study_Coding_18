package study_240208.problemset;

import java.io.*;
import java.util.*;

public class boj_14891 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int[][] gear = new int[4][8]; // 톱니바퀴 배열

        // 톱니바퀴 정보
        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = str.charAt(j) - '0';
            }
        }

        int K = Integer.parseInt(br.readLine()); // 회전 횟수

        for (int i = 0; i < K; i++) {
            stk = new StringTokenizer(br.readLine());

            int[] dir = new int[4]; // 톱니바퀴 회전 방향 배열

            int gearNum = Integer.parseInt(stk.nextToken()) - 1; // 톱니바퀴 번호
            int gearDir = Integer.parseInt(stk.nextToken()); // 톱니바퀴 회전 방향

            dir[gearNum] = gearDir;
            dir = checkDir(gear, dir, gearNum); // 방향 체크
            gear = rotate(gear, dir, gearNum); // 회전
        }

        // 정답 계산
        int sum = 0, score = 1;
        for (int i = 0; i < 4; i++) {
            // S극일 경우, 점수 추가
            if (gear[i][0] == 1) {
                sum += score;
            }

            score *= 2;
        }

        // 정답 출력
        sb.append(sum);
        System.out.println(sb);
    }

    // 기어 방향 체크 => 3번째, 7번째 체크 (배열에서 2, 6)
    static int[] checkDir(int[][] gear, int[] dir, int gearNum) {
        // 왼쪽
        for (int i = gearNum - 1; i >= 0; i--) {
            if (gear[i][2] == gear[i + 1][6]) {
                break;
            }

            dir[i] = -dir[i + 1];
        }

        // 오른쪽
        for (int i = gearNum + 1; i < 4; i++) {
            if (gear[i][6] == gear[i - 1][2]) {
                break;
            }

            dir[i] = -dir[i - 1];
        }

        return dir;
    }

    // 톱니바퀴 회전
    static int[][] rotate(int[][] gear, int[] dir, int gearNum) {
        for (int i = 0; i < 4; i++) {
            if (dir[i] == 1) { // 시계 방향
                int temp = gear[i][7];
                for (int j = 6; j >= 0; j--) {
                    gear[i][j + 1] = gear[i][j];
                }

                gear[i][0] = temp;
            } else if (dir[i] == -1) { // 반시계 방향
                int temp = gear[i][0];
                for (int j = 0; j <= 6; j++) {
                    gear[i][j] = gear[i][j + 1];
                }

                gear[i][7] = temp;
            }
        }

        return gear;
    }
}
