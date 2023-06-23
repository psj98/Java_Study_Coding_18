package study_230621.problemset;

import java.io.*;
import java.util.*;

public class boj_3190 {
    static int n, ans = 0;
    static int[][] map;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    static Queue<int[]> info = new ArrayDeque<>(); // 방향 전환 정보
    static Deque<int[]> snake = new ArrayDeque<>(); // 좌표 저장

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        // 시작 (0, 0), 길이 1, 방향 오른쪽
        // 1. 몸 길이 늘려 머리를 다음칸에 위치시킴
        // 2. 벽이나 자기 자신의 몸과 부딪히면 게임 종료
        // 3. 사과가 있으면, 사과가 없어지고 꼬리는 그대로
        // 4. 사과가 없으면, 몸길이를 줄여서 꼬리가 위치한 칸 비움

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        // 사과 위치 정보
        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken()) - 1;
            int y = Integer.parseInt(stk.nextToken()) - 1;
            map[x][y] = 1; // 사과 위치
        }

        // 회전 정보
        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            stk = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(stk.nextToken());
            int dir = stk.nextToken().equals("L") ? 1 : -1;
            info.add(new int[] { time, dir }); // 시간, 방향
        }

        map[0][0] = 2; // 뱀이 있는 위치 2로 저장
        snake.add(new int[] { 0, 0 }); // 뱀의 현재 위치
        play(); // 뱀 게임 시작

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }

    // 뱀 게임 시작
    static void play() {
        int dir = 0; // 현재 방향 정보

        while (true) {
            int[] cur = snake.peekLast(); // 현재 머리 위치
            int nx = cur[0] + dx[dir];
            int ny = cur[1] + dy[dir];

            ans++; // 시간 증가

            // 벽 체크
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                break;
            }

            // 뱀 충돌 체크
            if (map[nx][ny] == 2) {
                break;
            }

            // 사과가 없는 경우
            if (map[nx][ny] == 0) {
                int[] tail = snake.pollFirst(); // 현재 꼬리 위치
                map[tail[0]][tail[1]] = 0;
            }

            // 머리 움직이기
            snake.add(new int[] { nx, ny });
            map[nx][ny] = 2;

            // 현재 시간과 비교 후, 회전
            if (!info.isEmpty()) {
                int time = info.peek()[0];
                if (time == ans) { // 현재 시간과 같은지 체크
                    int nextDir = info.poll()[1];
                    if (nextDir == 1) { // L
                        dir = (dir + 3) % 4;
                    } else { // D
                        dir = (dir + 1) % 4;
                    }
                }
            }
        }
    }
}
