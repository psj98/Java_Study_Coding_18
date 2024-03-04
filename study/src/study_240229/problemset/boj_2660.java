package study_240229.problemset;

import java.io.*;
import java.util.*;

public class boj_2660 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 사람 수

        int[][] arr = new int[n + 1][n + 1]; // 사람 간 점수 배열

        // 초기화
        for (int i = 1; i <= n; i++) {
            Arrays.fill(arr[i], 987654321);
        }

        // 서로 관계가 있는 경우, 체크
        while (true) {
            stk = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());

            // -1, -1 이면 입력 종료
            if (x == -1 && y == -1) {
                break;
            }

            arr[x][y] = arr[y][x] = 1;
        }

        // 플로이드 - 와샬
        for (int k = 1; k <= n; k++) {
            arr[k][k] = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }

        int leader = Integer.MAX_VALUE; // 회장이 될 수 있는 점수 => 최솟값
        int leaderCnt = 0; // 회장 수
        int[] score = new int[n + 1]; // 각 사람 별 점수 => 최댓값

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                score[i] = Math.max(score[i], arr[i][j]);
            }

            leader = Math.min(leader, score[i]);
        }

        ArrayList<Integer> leaderList = new ArrayList<>(); // 회장 리스트

        for (int i = 1; i <= n; i++) {
            // 점수가 다를 경우, 스킵
            if (score[i] != leader) {
                continue;
            }

            leaderCnt++; // 회장 수 증가
            leaderList.add(i); // 회장이 될 수 있는 사람 idx 저장
        }

        // 정답 저장
        sb.append(leader).append(" ").append(leaderCnt).append("\n");

        for (int i : leaderList) {
            sb.append(i).append(" ");
        }

        // 정답 출력
        System.out.println(sb);
    }
}
