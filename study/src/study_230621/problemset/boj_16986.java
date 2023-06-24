package study_230621.problemset;

import java.io.*;
import java.util.*;

public class boj_16986 {
    static int n, k;
    static int[][] map; // 가위바위보 승패 정보
    static int[][] info; // 플레이어 정보
    static boolean[] visited;
    static boolean check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        map = new int[n][n];
        info = new int[3][20];
        visited = new boolean[n];

        // 가위바위보 승패 정보 입력
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        // 경희, 민호 가위바위보 정보
        for (int i = 1; i <= 2; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < 20; j++)
                info[i][j] = Integer.parseInt(stk.nextToken()) - 1;
        }

        permutation(0); // 지우 가위바위보 순서 정하기

        // 정답 출력
        if (check)
            sb.append(1);
        else
            sb.append(0);
        System.out.println(sb);
    }

    // 지우 가위바위보 순서 정하기 (모두 다르게)
    static void permutation(int cnt) {
        if (check) { // 모두 다른 것을 내서 이긴 경우
            return;
        }

        if (cnt == n) { // 순서가 정해진 경우
            play(); // 게임 시작
        }

        for (int i = 0; i < n; i++) {
            if (visited[i])
                continue;

            visited[i] = true;
            info[0][cnt] = i;

            permutation(cnt + 1);

            visited[i] = false;
        }
    }

    // 게임 시작
    static void play() {
        int[] win = new int[3]; // 플레이어 별 이긴 횟수
        int[] playerIdx = new int[3]; // 각 플레이어가 낼 것을 찾기 위한 index

        int player1 = 0;
        int player2 = 1;
        int nextPlayer = 2;

        while (true) {
            nextPlayer = 3 - player1 - player2; // 다음 플레이어 설정

            // 지우가 k번 이긴 경우
            if (win[0] == k) {
                check = true;
                return;
            }

            // 다른 플레이어가 k번 이긴 경우
            if (win[1] == k || win[2] == k) {
                return;
            }

            // 지우가 정한 가위바위보 개수를 넘억나 경우
            // 다른 플레이어가 20번 이상을 내는 경우
            if (playerIdx[0] == n || playerIdx[1] == 20 || playerIdx[2] == 20) {
                return;
            }

            // 승패 체크
            int playerInfo1 = info[player1][playerIdx[player1]];
            int playerInfo2 = info[player2][playerIdx[player2]];

            int winner = checkWinner(player1, player2, playerInfo1, playerInfo2); // 승자 체크
            win[winner]++; // 이긴 횟수 증가
            playerIdx[player1]++; // 다음 라운드로 이동 
            playerIdx[player2]++;

            // 플레이어 변경
            player1 = winner;
            player2 = nextPlayer;
        }
    }

    // 승자 체크
    static int checkWinner(int player1, int player2, int playerInfo1, int playerInfo2) {
        int cur = map[playerInfo1][playerInfo2];
        if (cur == 2) { // 2인 경우, player1 승
            return player1;
        } else if (cur == 1) { // 비긴 경우, 뒷 사람 승
            return Math.max(player1, player2);
        } else { // 0인 경우, player2 승
            return player2;
        }
    }
}
